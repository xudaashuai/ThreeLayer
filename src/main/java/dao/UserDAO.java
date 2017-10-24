package dao;

import vo.UserVO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserDAO {
    DBConnection dbConnection;
    String sql = "select id,first_name,last_name,email from public.user_tbl ";

    public UserDAO() {
        dbConnection = new DBConnection();
    }

    public ResultSet searchById(int id) {
        CallableStatement statement = dbConnection.getStatement("select id,first_name,last_name,email from public.user_tbl where id = ?");
        UserVO userVO = new UserVO();
        try {
            statement.setInt(1, id);
            return dbConnection.executeSelectQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet searchByUser(UserVO user) {
        String sql = "select id,first_name,last_name,email from public.user_tbl ";

        int i = 1;
        if (user.getEmail().length()!=0) {

            if (i!=1){
                sql+="add ";
            }else{

                sql+=" where ";
            }
            i++;
            sql += " email like ?";
        }
        if (user.getFirstName().length()!=0) {
            if (i!=1){
                sql+="add ";
            }else{

                sql+="where ";
            }
            i++;
            sql += " first_name like ?";
        }
        if (user.getLastName().length()!=0) {
            if (i!=1){
                sql+="add ";
            }else{
                sql+="where ";
            }
            i++;
            sql += " last_name like ?";
        }
        i = 1;
        CallableStatement statement = dbConnection.getStatement(sql);
        try {
            if (user.getEmail().length()!=0) {
                statement.setString(i++, user.getEmail());
            }
            if (user.getFirstName().length()!=0) {
                statement.setString(i++, user.getFirstName());
            }
            if (user.getLastName().length()!=0) {
                statement.setString(i, user.getLastName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection.executeSelectQuery(statement);
    }
    public boolean updateById(UserVO u){
        String sql = "update public.user_tbl set email = ? ,first_name = ?,last_name = ? where id = ? ";

        CallableStatement statement = dbConnection.getStatement(sql);
        try {
            statement.setString(1,u.getEmail());
            statement.setString(2,u.getFirstName());
            statement.setString(3,u.getLastName());
            statement.setInt(4,u.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection.executeUpdateQuery(statement)!=-1;
    }
    public ResultSet searchByName(String name) {
        CallableStatement statement = dbConnection.getStatement("select id,first_name,last_name,email from public.user_tbl where first_name like ? or last_name like ?");
        UserVO userVO = new UserVO();
        try {
            statement.setString(1, name);
            statement.setString(2, name);
            return dbConnection.executeSelectQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
