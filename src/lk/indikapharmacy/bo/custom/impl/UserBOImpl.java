package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.UserBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.UserDAO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.UsersDTO;
import lk.indikapharmacy.entity.Users;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO usDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addUser(UsersDTO usersDTO) throws ClassNotFoundException, SQLException, Exception {
        return usDAO.add(new Users(usersDTO.getUserID(),usersDTO.getName(),usersDTO.getUserName(),usersDTO.getPassword()));
    }

    @Override
    public boolean validateUser(String userName, String pass) throws ClassNotFoundException, SQLException, Exception {
        return usDAO.validateUser(userName,pass);
    }
}
