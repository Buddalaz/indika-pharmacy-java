package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.MedicineDAO;
import lk.indikapharmacy.entity.Medicine;
import lk.indikapharmacy.entity.MedicineCatogory;
import lk.indikapharmacy.entity.MedicineDetails;
import lk.indikapharmacy.entity.MedicineReturn;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {

    @Override
    public boolean add(Medicine medicine) throws Exception {
        String sql="INSERT INTO medicine VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,medicine.getItemID(),medicine.getDescription(),medicine.getProductDate(),medicine.getExpireDate(),
                medicine.getUnitPrice(),medicine.getQtyOnHand());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM medicine WHERE itemID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Medicine medi) throws Exception {
        String sql = "UPDATE medicine SET description=?, productDate=?, expireDate=?, unitPrice=?, qtyOnHand=? WHERE itemID=?";
        return CrudUtil.executeUpdate(sql, medi.getDescription(),
                medi.getProductDate(),
                medi.getExpireDate(),
                medi.getUnitPrice(),medi.getQtyOnHand(),medi.getItemID());
    }

    @Override
    public Medicine search(String s) throws Exception {
        String sql ="SELECT * FROM medicine WHERE itemID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new Medicine(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),
                    rst.getDouble(5),rst.getInt(6));
        }
        return null;
    }

    @Override
    public ArrayList<Medicine> getAll() throws Exception {
        String sql = "SELECT * FROM medicine";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        while (rst.next()){
            medicineArrayList.add(new Medicine(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),
                    rst.getDouble(5),rst.getInt(6)));
        }
        return medicineArrayList;
    }

    @Override
    public boolean updateStockQuentity(MedicineDetails mediDetail) throws Exception {
        String sql="UPDATE medicine SET qtyOnHand=qtyOnHand-? WHERE itemID=?";
        return CrudUtil.executeUpdate(sql, mediDetail.getQty(),mediDetail.getItemID());
    }

    @Override
    public String itemCount() throws Exception {
        String sql = "SELECT Count(itemID) FROM medicine";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            int itemCount = rst.getInt(1);
            return String.valueOf(itemCount);
        }
        return null;
    }

    @Override
    public String genarateMediId() throws Exception {
        String sql = "SELECT itemID FROM medicine ORDER BY itemID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            return rst.getString("itemID");
        }
        return null;
    }

    @Override
    public boolean updateMediReturnStockQuentity(MedicineReturn medicineReturn) throws Exception {
        String sql="UPDATE medicine SET qtyOnHand=qtyOnHand+? WHERE itemID=?";
        return CrudUtil.executeUpdate(sql, medicineReturn.getQty(),medicineReturn.getItemID());
    }


}
