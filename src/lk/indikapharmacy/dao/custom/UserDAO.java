package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.Users;

public interface UserDAO extends CrudDAO<Users, String> {
    public boolean validateUser(String u,String p) throws Exception;
}
