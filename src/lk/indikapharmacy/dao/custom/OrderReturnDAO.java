package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.MedicineReturn;

public interface OrderReturnDAO extends CrudDAO<MedicineReturn, String> {
    public String genarateOrderReturnID() throws Exception;
}
