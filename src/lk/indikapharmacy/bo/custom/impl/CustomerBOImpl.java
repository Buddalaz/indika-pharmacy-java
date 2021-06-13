package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.CustomerBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.CustomerDAO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customer = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCoustomer(CustomerDTO cust) throws ClassNotFoundException, SQLException, Exception {
        return customer.add(new Customer(cust.getCustID(),cust.getCustName(),cust.getPhoneNumber(),cust.getCustAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException, Exception {
        return customer.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws ClassNotFoundException, SQLException, Exception {
        Customer cust = customer.search(id);
        return new CustomerDTO(cust.getCustID(),cust.getCustName(),cust.getPhoneNumber(),cust.getCustAddress());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException, Exception {
        ArrayList<Customer> all = customer.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO dto = new CustomerDTO(c.getCustID(),c.getCustName(),c.getPhoneNumber(),c.getCustAddress());
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public boolean updateCustomer(CustomerDTO custDTO) throws ClassNotFoundException, SQLException, Exception {
        return customer.update(new Customer(custDTO.getCustID(),custDTO.getCustName(),custDTO.getPhoneNumber(),custDTO.getCustAddress()));
    }

    @Override
    public String getLastCustomerID() throws ClassNotFoundException, SQLException, Exception {
        return customer.genarateCustomerID();
    }

    @Override
    public String customerCount() throws ClassNotFoundException, SQLException, Exception {
        return customer.customerCount();
    }


}
