package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.OrderReturnDAO;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.MedicineReturn;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderReturnDAOImpl implements OrderReturnDAO {
    @Override
    public boolean add(MedicineReturn medicineReturn) throws Exception {
        String sql="INSERT INTO medicinereturn VALUES(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,medicineReturn.getReturnID(),medicineReturn.getDate(),medicineReturn.getOrderID(),
                medicineReturn.getItemID(),medicineReturn.getQty());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE medicinereturn WHERE returnID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(MedicineReturn medicineReturn) throws Exception {
        String sql = "UPDATE medicinereturn SET date=?, orderID=?, itemID=?, Quentity=? WHERE returnID=?";
        return CrudUtil.executeUpdate(sql, medicineReturn.getDate(),medicineReturn.getOrderID(),medicineReturn.getItemID(),
                medicineReturn.getQty(),medicineReturn.getReturnID());
    }

    @Override
    public MedicineReturn search(String s) throws Exception {
        String sql ="SELECT * FROM medicinereturn WHERE returnID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new MedicineReturn(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5));
        }
        return null;
    }

    @Override
    public ArrayList<MedicineReturn> getAll() throws Exception {
        String sql = "SELECT * FROM medicinereturn";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<MedicineReturn> medicineReturnArrayList = new ArrayList<>();
        while (rst.next()){
            medicineReturnArrayList.add(new MedicineReturn(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getInt(5)));
        }
        return medicineReturnArrayList;
    }

    @Override
    public String genarateOrderReturnID() throws Exception {
        String sql = "SELECT returnID FROM medicinereturn ORDER BY returnID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            return rst.getString("returnID");
        }
        return null;
    }
}
