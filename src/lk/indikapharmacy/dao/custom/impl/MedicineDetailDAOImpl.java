package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.MedicineDetailDAO;
import lk.indikapharmacy.entity.MedicineDetails;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineDetailDAOImpl implements MedicineDetailDAO {
    @Override
    public boolean add(MedicineDetails medicineDetails) throws Exception {
        String sql = "INSERT INTO medicinedetails VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, medicineDetails.getOrderID(),medicineDetails.getItemID(),medicineDetails.getDescription(),
                medicineDetails.getUnitPrice(),medicineDetails.getQty(),medicineDetails.getTotal());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql = "DELETE medicinedetails WHERE orderID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(MedicineDetails medicineDetails) throws Exception {
        String sql="UPDATE medicinedetails SET itemID=?, description=?, unitPrice=?, quentity=?, total=? WHERE orderID=?";
        return CrudUtil.executeUpdate(sql, medicineDetails.getItemID(), medicineDetails.getDescription(), medicineDetails.getUnitPrice(),
                medicineDetails.getQty(),medicineDetails.getTotal(), medicineDetails.getOrderID());
    }

    @Override
    public MedicineDetails search(String s) throws Exception {
        String sql="SEARCH medicinedetails WHERE orderID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new MedicineDetails(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getDouble(4),rst.getInt(5),rst.getDouble(6));
        }
        return null;
    }

    @Override
    public ArrayList<MedicineDetails> getAll() throws Exception {
        String sql="SELECT * FROM medicinedetails";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<MedicineDetails> medicineDetailList = new ArrayList<>();
        while (rst.next()){
            medicineDetailList.add(new MedicineDetails(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getDouble(4),rst.getInt(5),rst.getDouble(6)));
        }
        return medicineDetailList;
    }
}
