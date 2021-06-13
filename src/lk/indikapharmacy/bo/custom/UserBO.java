package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.UsersDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    public boolean addUser(UsersDTO usersDTO) throws ClassNotFoundException, SQLException, Exception;

    public boolean validateUser(String userName,String pass) throws ClassNotFoundException, SQLException, Exception;
}
