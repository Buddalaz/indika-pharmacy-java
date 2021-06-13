package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.SupplierDAO;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.Suppler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SuppilerDAOImpl implements SupplierDAO {
    @Override
    public boolean add(Suppler suppler) throws Exception {
        String sql="INSERT INTO suppler VALUES(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, suppler.getSupplyID(),suppler.getAddress(),suppler.getName(),
                 suppler.getEmail(),suppler.getContact());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM suppler WHERE supplyID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Suppler suppler) throws Exception {
        String sql = "UPDATE suppler SET address=?, name=?, email=?, contact=? WHERE supplyID=?";
        return CrudUtil.executeUpdate(sql, suppler.getAddress(),suppler.getName(),suppler.getEmail(),
                suppler.getContact(),suppler.getSupplyID());

    }

    @Override
    public Suppler search(String s) throws Exception {
        String sql ="SELECT * FROM suppler WHERE supplyID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new Suppler(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<Suppler> getAll() throws Exception {
        String sql = "SELECT * FROM suppler";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Suppler> supplerArrayList = new ArrayList<>();
        while (rst.next()){
            supplerArrayList.add(new Suppler(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return supplerArrayList;
    }

    @Override
    public String genarateSupplierID() throws Exception {
        String sql ="SELECT supplyID FROM suppler ORDER BY supplyID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            return rst.getString("supplyID");
        }
        return null;
    }
}
