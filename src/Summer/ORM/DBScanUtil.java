package Summer.ORM;

import java.io.File;
import java.util.Map;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.io.FileOutputStream;
import Summer.AppContext.Config;

public class DBScanUtil {

    public static void scanBiaoTable() throws Exception {
        Map<String, String> fieldMap = new LinkedHashMap<>();
        ResultSet biaoSet = DBUtils.quuery("SELECT * FROM biaotable");

        while (biaoSet.next()){
            ResultSet fieldSet = DBUtils.quuery("select COLUMN_NAME,DATA_TYPE from information_schema.COLUMNS where table_name='"+biaoSet.getString("name")+"' and table_schema = '"+Config.DBName+"';");
            while (fieldSet.next())
            fieldMap.put(fieldSet.getString(1), fieldSet.getString(2));

            String pac = DBScanUtil.class.getPackageName() + ".model";
            String path = new File("").getAbsolutePath()+"/src/"+pac.replaceAll("\\.","/")+"/"+upperOne(biaoSet.getString("name"))+".java";
            File f = new File(path);
            File parent = f.getParentFile();          // 获取父目录
            if( !parent.exists() )  parent.mkdirs();  //创建所有父文件夹
            if(!f.exists())      f.createNewFile();
            System.out.println(path);
            writeStringToFile(path, generateCodeString(pac, biaoSet.getString("name"), fieldMap) );
            fieldMap.clear();
        }
    }

    //把code字符串写入硬盘
    private static void writeStringToFile(String filePath, String code) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(code.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成set get的目标类的代码字符串. 下面有好几个支持方法
    public static String generateCodeString(String packageName, String className, Map<String,String> fieldMap){
        StringBuilder builder = new StringBuilder();
        builder.append("package "+packageName+";\n\n");
        builder.append("public class "+upperOne(className)+" {\n");

        for (String key : fieldMap.keySet())
            builder.append("private "+typeTran(fieldMap.get(key))+" "+key+";\n");

        for (String key : fieldMap.keySet())
            builder.append("public static String "+key.toUpperCase()+" = \""+key+"\";\n");

        builder.append("\n");           //get方法
        for (String key : fieldMap.keySet()){
            builder.append(duiqi("public "+typeTran(fieldMap.get(key))+" get"+upperOne(key)+"()",50)+"{");
            builder.append(yduiqi("return "+key+";      }\n",32));
        }

        builder.append("\n");           //set方法
        for (String key : fieldMap.keySet()){
            builder.append(duiqi("public void set"+upperOne(key)+"("+typeTran(fieldMap.get(key))+" "+key+")",50)+"{");
            builder.append(yduiqi("this."+key+" = "+key+";      }\n",32));
        }

        builder.append("\n}");
        return builder.toString();
    }
    //首字母大写
    private static String upperOne(String name){
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    private static String duiqi(String name, int num){         //生成代码对齐
        return String.format("%-"+num+"s", name);   //表示a右对齐占用5个字符，不足的用空格补位. 左%-5s
    }
    private static String yduiqi(String name, int num){         //生成代码对齐
        return String.format("%"+num+"s", name);   //表示a右对齐占用5个字符，不足的用空格补位. 左%-5s
    }
    private static String typeTran(String type){             //类型转换，例如Vchar  -->  String
        if ("int".equals(type) || "double".equals(type) || "float".equals(type) )  return type;
        if ("bit".equals(type))     return "boolean";
        return "String";
    }

}
