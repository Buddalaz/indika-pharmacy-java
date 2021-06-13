package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.MedicineOrders;

import java.sql.SQLException;

public interface MedicineOrderDAO extends CrudDAO<MedicineOrders, String> {

    public String genarateOrderID() throws Exception;

    public String orderCount() throws Exception;

}
