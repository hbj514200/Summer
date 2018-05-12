package Summer.ORM;

import java.util.Map;
import java.util.List;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.lang.reflect.Modifier;

public class Statement {
    private StringBuilder sql = new StringBuilder();
    private Map<String,String> whereMap = new LinkedHashMap<>();           //保存where语句的条件
    private String groupString=" GROUP BY ";
    private String limitString=" LIMIT ";
    private Map<String,String> keyValueMap = new LinkedHashMap<>();        //属性名，属性要设为的值

    public Statement insertInto(String biaoName)       {   sql.append("INSERT INTO " +biaoName  + " ");    return this;    }
    public Statement deleteFrom(String biaoName)       {   sql.append("DELETE FROM " +biaoName  + " ");    return this;    }
    public Statement update(String biaoName)           {   sql.append("UPDATE " +biaoName  + " SET ");     return this;    }
    public Statement select(String biaoName)           {   sql.append("SELECT * FROM " +biaoName  + " ");  return this;    }

    public Statement set(String key, String value)     {   keyValueMap.put(key, value);                    return this;    }
    public Statement limit(int start, int end)         {   limitString += start+","+end;                   return this;    }
    public Statement where(String key, String tiaojian){   whereMap.put(key, tiaojian);                    return this;    }
    public Statement and(String key, String tiaojian)  {   whereMap.put(key, tiaojian);                    return this;    }

    private void getWhereString(){
        if (whereMap.size()!=0)                     sql.append("WHERE ");
        for (String key : whereMap.keySet())        sql.append(key+whereMap.get(key)+" AND ");
        sql = new StringBuilder(sql.toString().replaceAll(", WHERE", " WHERE"));
        sql = new StringBuilder(sql.toString().substring(0,sql.toString().length()-4));
    }

    public Statement groupBy(String key){
        if (groupString.length()==" GROUP BY ".length())    groupString += key;
        else                                                groupString += ","+key;
        return this;
    }

    public List<Object> fetchInto(Class cls){
        List<Object> list = new LinkedList<>();
        getWhereString();
        System.out.println("ORM执行sql语句:"+sql);
        ResultSet set = DBUtils.quuery(sql.toString());
        try {
            while (set.next()) {
                Object object = cls.newInstance();
                Field[] declaredFields = object.getClass().getDeclaredFields();
                for (Field field : declaredFields)
                    if ( !Modifier.isStatic(field.getModifiers()) ){
                        field.setAccessible(true);
                        if ( field.getType().getName().equals("int") )
                            field.set(object, set.getInt(field.getName()));
                        else if ( field.getType().getName().equals("boolean") )
                            field.set(object, set.getBoolean(field.getName()));
                        else if ( field.getType().getName().equals("double") )
                            field.set(object, set.getDouble(field.getName()));
                        else if ( field.getType().getName().equals("float") )
                            field.set(object, set.getFloat(field.getName()));
                        else
                            field.set(object, set.getString(field.getName()));
                    }
                list.add(object);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean excute(){
        if (sql.toString().startsWith("INSERT")){
            sql.append("(");
            for (String key : keyValueMap.keySet())     sql.append(key).append(", ");
            sql.append(") VALUES ");
            sql.append("(");
            for (String key : keyValueMap.keySet())     sql.append("'"+keyValueMap.get(key)+"', ");
            sql.append(");");
            sql = new StringBuilder( sql.toString().replaceAll(", \\)", "\\)") );
        }
        else if (sql.toString().startsWith("UPDATE")) {
            for (String key : keyValueMap.keySet())     sql.append(key+"="+keyValueMap.get(key)+", ");
            getWhereString();
        }
        else if (sql.toString().startsWith("DELETE")) {
            for (String key : keyValueMap.keySet())     sql.append(key+"="+keyValueMap.get(key)+", ");
            getWhereString();
        }

        if (groupString.length()>" GROUP BY ".length())      sql.append(groupString);
        if (limitString.length()>" LIMIT ".length())         sql.append(limitString);
        System.out.println(sql.toString());
        System.out.println("ORM执行sql语句:"+sql);
        return DBUtils.cao(sql.toString());
    }

}
