package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.MedicineOrderDAO;
import lk.indikapharmacy.entity.MedicineOrders;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineOrderDAOImpl implements MedicineOrderDAO {
    @Override
    public boolean add(MedicineOrders medicineOrders) throws Exception {
        String sql="INSERT INTO medicineorders VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, medicineOrders.getOrderID(),medicineOrders.getOrderDate(),medicineOrders.getCustID(),
                medicineOrders.getUserID(),medicineOrders.getTotal(),medicineOrders.getCash(),medicineOrders.getBalance());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE medicineorders WHERE orderID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(MedicineOrders medicineOrders) throws Exception {
        String sql = "UPDATE medicineorders SET date=?, custID=?, userID=?, total=?, cash=?, balance=? WHERE orderID=?";
        return CrudUtil.executeUpdate(sql, medicineOrders.getOrderDate(),medicineOrders.getCustID(),medicineOrders.getUserID(),
                medicineOrders.getTotal(), medicineOrders.getCash(), medicineOrders.getBalance(), medicineOrders.getOrderID());
    }

    @Override
    public MedicineOrders search(String s) throws Exception {
        String sql="SELECT * FROM medicineorders WHERE orderID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new MedicineOrders(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(4),
                    rst.getDouble(6),rst.getDouble(7));
        }
        return null;
    }

    @Override
    public ArrayList<MedicineOrders> getAll() throws Exception {
        String sql="SELECT * FROM medicineorders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<MedicineOrders> mediOrderList = new ArrayList<>();
        while (rst.next()){
            mediOrderList.add(new MedicineOrders(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(4),
                    rst.getDouble(6),rst.getDouble(7)));
        }
        return mediOrderList;
    }

    @Override
    public String genarateOrderID() throws Exception {
        String sql = "SELECT orderID FROM medicineorders ORDER BY orderID DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            return rst.getString("orderID");
        }
        return null;
    }

    @Override
    public String orderCount() throws Exception {
        String sql = "SELECT Count(orderID) FROM medicineorders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            int orderCount = rst.getInt(1);
            return String.valueOf(orderCount);
        }
        return null;
    }
}
