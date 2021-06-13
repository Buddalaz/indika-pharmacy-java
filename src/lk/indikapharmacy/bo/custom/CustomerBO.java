package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean addCoustomer(CustomerDTO cust) throws ClassNotFoundException, SQLException, Exception;

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException, Exception;

    public CustomerDTO searchCustomer(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException, Exception;

    public boolean updateCustomer(CustomerDTO customer) throws ClassNotFoundException, SQLException, Exception;

    public String getLastCustomerID() throws ClassNotFoundException, SQLException, Exception;

    public String customerCount() throws ClassNotFoundException, SQLException, Exception;

}
