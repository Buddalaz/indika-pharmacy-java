package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.Medicine;
import lk.indikapharmacy.entity.MedicineDetails;
import lk.indikapharmacy.entity.MedicineReturn;

public interface MedicineDAO extends CrudDAO<Medicine, String> {

    public boolean updateStockQuentity(MedicineDetails mediDetail)throws Exception;

    public String itemCount() throws Exception;

    public String genarateMediId() throws Exception;

    public boolean updateMediReturnStockQuentity(MedicineReturn medicineReturn)throws Exception;

}
