package Summer.ORM;

import Summer.AppContext.Config;
import java.sql.*;

public class DBUtils {
    private static Connection connection = null;

    private static void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ Config.DBName+"?user=root&password=&characterEncoding=utf8");
        } catch (Exception e){
            System.out.println("致命错误: 数据库连接失败！！");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        if (connection==null)    init();
        return connection;
    }

    //在该框架中 可能比较没用的方法。如下几个
    public static boolean delete(String flag, String zhujian, String delNo){
        //删除表中数据, 传入表名，目标键，号码
        if (delNo!=null && delNo.length()>0){
            try {
                DBUtils.getConnection().createStatement().execute("DELETE FROM "+flag+" WHERE "+zhujian+" = '"+delNo+"'");
                return true;
            } catch (Exception e){ e.printStackTrace(); }
        }
        return false;
    }

    public static boolean insert(String flag, String[] arr){
        //在数据表中插入一行数据, arr为传入数据从0开始. flag为表名
        boolean result = false;
        try {
            StringBuilder insertSQL = new StringBuilder("INSERT INTO "+flag+" VALUES (?");        //构建PreparedStatement插入占位语句
            for (int i=1; i<arr.length; i++)    insertSQL.append(",?");
            insertSQL.append(")");
            PreparedStatement ps = DBUtils.getConnection().prepareStatement(insertSQL.toString());

            try {
                for (int i=1; i<=arr.length; i++)       ps.setString(i, arr[i-1]);
                ps.executeUpdate();
                result = true;
            } catch (Exception e){
                e.printStackTrace();
                result = false;
            }
        } catch (Exception e){e.printStackTrace();}
        return result;
    }

    public static ResultSet quuery(String sql) {
        //执行输入的SQL查询语句
        try {
            return getConnection().createStatement().executeQuery(sql);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static boolean cao(String sql) {
        //执行输入的SQL操作语句, 单句使用
        try {
            int resultNum = getConnection().createStatement().executeUpdate(sql);
            return (resultNum>0);
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static ResultSet search(String biao, String[] fields, String pei){
        //返回浏览模块的查找搜索结果, pei为搜索关键词, flag为表名, fields是要匹配的字段名字
        pei = "'%"+pei+"%'";
        StringBuilder tiao = new StringBuilder(biao+" WHERE ");                          //循环添加LIKE ~ or匹配语句
        for (String name:fields)    tiao.append(name).append(" LIKE ~ or ");
        tiao.delete(tiao.length()-4, tiao.length());

        try {
            return DBUtils.getConnection().createStatement().executeQuery("SELECT * FROM " + tiao.toString());
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    //输入id，获取数据库该条目信息
    public static ResultSet getDataById(String biao, String zhujian, String id){
        return DBUtils.quuery("SELECT * FROM "+biao+" WHERE "+zhujian+"='"+id+"'");
    }

}

