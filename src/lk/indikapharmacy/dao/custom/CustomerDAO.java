package lk.indikapharmacy.dao.custom;

import lk.indikapharmacy.dao.CrudDAO;
import lk.indikapharmacy.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    public String genarateCustomerID() throws Exception;

    public String customerCount() throws Exception;

}
