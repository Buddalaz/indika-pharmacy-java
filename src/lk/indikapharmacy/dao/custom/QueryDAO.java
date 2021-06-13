package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.SuperDAO;
import lk.indikapharmacy.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CustomEntity> getOrderDetailsFromOID(String oid) throws ClassNotFoundException, SQLException;
}
