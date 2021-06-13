package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.Suppler;

public interface SupplierDAO extends CrudDAO<Suppler, String> {
    public String genarateSupplierID() throws Exception;
}
