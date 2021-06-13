package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.CustomerDAO;
import lk.indikapharmacy.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        String sql="INSERT INTO customer VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,customer.getCustID(),customer.getCustName(),customer.getPhoneNumber(),customer.getCustAddress());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM customer WHERE custID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql = "UPDATE customer SET name=?, phoneNumber=?, address=? WHERE custID=?";
        return CrudUtil.executeUpdate(sql, customer.getCustName(),customer.getPhoneNumber(),customer.getCustAddress(),customer.getCustID());
    }

    @Override
    public Customer search(String s) throws Exception {
        String sql ="SELECT * FROM customer WHERE custID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while (rst.next()){
            customerArrayList.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return customerArrayList;
    }

    @Override
    public String genarateCustomerID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT custID FROM customer ORDER BY custID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            return rst.getString("custID");
        }
        return null;
    }

    @Override
    public String customerCount() throws Exception {
        String sql = "SELECT Count(custID) FROM customer";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            int custCount = rst.getInt(1);
            return String.valueOf(custCount);
        }
        return null;
    }
}
