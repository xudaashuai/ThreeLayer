package dao;

import vo.UserVO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    DBConnection dbConnection;
    public UserDAO(){
        dbConnection=new DBConnection();
    }
    public ResultSet searchById(int id){
        CallableStatement statement = dbConnection.getStatement("select id,first_name,last_name,email from public.user_tbl where id = ?");
        UserVO userVO=new UserVO();
        try {
            statement.setInt(1,id);
            return dbConnection.executeSelectQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet searchByName(String  name){
        CallableStatement statement = dbConnection.getStatement("select id,first_name,last_name,email from public.user_tbl where first_name like ? or last_name like ?");
        UserVO userVO=new UserVO();
        try {
            statement.setString(1,name);
            statement.setString(2,name);
            return dbConnection.executeSelectQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
