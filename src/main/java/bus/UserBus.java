package bus;

import dao.UserDAO;
import vo.UserVO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBus {
    UserDAO userDAO=new UserDAO();
    public UserVO getUserById(int id){
        UserVO userVO=new UserVO();
        try {
            ResultSet resultSet = userDAO.searchById(id);
            if (resultSet.next()){
                userVO.setId(resultSet.getInt(1));
                userVO.setFirstName(resultSet.getString(2));
                userVO.setLastName(resultSet.getString(3));
                userVO.setEmail(resultSet.getString(4));
                return userVO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public UserVO getUserEmailByName(String  name){
        UserVO userVO=new UserVO();
        try {
            ResultSet resultSet = userDAO.searchByName(name);
            if (resultSet.next()){
                userVO.setId(resultSet.getInt(1));
                userVO.setFirstName(resultSet.getString(2));
                userVO.setLastName(resultSet.getString(3));
                userVO.setEmail(resultSet.getString(4));
                return userVO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
