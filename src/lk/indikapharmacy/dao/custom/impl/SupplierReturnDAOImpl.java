package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.SupplierDAO;
import lk.indikapharmacy.dao.custom.SupplierReturnDAO;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.Suppler;
import lk.indikapharmacy.entity.SupplierReturn;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierReturnDAOImpl implements SupplierReturnDAO {

    @Override
    public boolean add(SupplierReturn supplierReturn) throws Exception {
        String sql="INSERT INTO supplierreturn VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,supplierReturn.getSupplyID(),supplierReturn.getQty(),supplierReturn.getDescriptin(),
                supplierReturn.getReturnDate());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE supplierreturn WHERE supplyID=?";
        return CrudUtil.executeUpdate(sql,s);

    }

    @Override
    public boolean update(SupplierReturn supplierReturn) throws Exception {
        String sql = "UPDATE supplierreturn SET quentity=?, description=?, returnDate=? WHERE supplyID=?";
        return CrudUtil.executeUpdate(sql, supplierReturn.getQty(),supplierReturn.getDescriptin(),supplierReturn.getReturnDate(),
                supplierReturn.getSupplyID());
    }

    @Override
    public SupplierReturn search(String s) throws Exception {
        String sql ="SELECT * FROM supplierreturn WHERE supplyID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new SupplierReturn(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getString(4));
        }
        return null;

    }

    @Override
    public ArrayList<SupplierReturn> getAll() throws Exception {
        String sql = "SELECT * FROM supplierreturn";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<SupplierReturn> supplierReturnsArrayList = new ArrayList<>();
        while (rst.next()){
            supplierReturnsArrayList.add(new SupplierReturn(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getString(4)));
        }
        return supplierReturnsArrayList;
    }

}
