package bus;

import dao.UserDAO;
import vo.UserVO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBus {
    private UserDAO userDAO = new UserDAO();

    public  List<UserVO>  getUserById(int id) {
        return getUserFromResultSet(userDAO.searchById(id));
    }

    public  List<UserVO>  getUserEmailByName(String name) {
        return getUserFromResultSet(userDAO.searchByName(name));
    }

    public  List<UserVO>  getUserByUser(UserVO user) {
        return getUserFromResultSet(userDAO.searchByUser(user));
    }
    public boolean updateById(UserVO u){
        return userDAO.updateById(u);
    }
    private List<UserVO> getUserFromResultSet(ResultSet resultSet) {
        List<UserVO> result=new ArrayList<UserVO>();
        try {
            while (resultSet.next()) {
                UserVO userVO = new UserVO();
                userVO.setId(resultSet.getInt(1));
                userVO.setFirstName(resultSet.getString(2));
                userVO.setLastName(resultSet.getString(3));
                userVO.setEmail(resultSet.getString(4));
                result.add(userVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
