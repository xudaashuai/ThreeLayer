package dao;

import java.sql.*;

public class DBConnection {
    final static String diver="org.postgresql.Driver";
    final static String url="jdbc:postgresql://localhost:5439";
    final static String username="postgres";
    final static String password="";

    public Connection connection=null;
    DBConnection(){
        openConnection();
    }
    public boolean openConnection(){
        if (connection!=null){
            return true;
        }
        try {
            connection = DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return connection!=null;
    }
    public CallableStatement getStatement(String sql){
        try {
            return connection.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet executeSelectQuery(CallableStatement statement){
        ResultSet set;
        try {
            set = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return set;
    }
    public boolean executeInsertQuery(CallableStatement statement){
        boolean result;
        try {
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }
    public int executeUpdateQuery(CallableStatement statement){
        int result;
        try {
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return result;
    }
}
