package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.StockDAO;
import lk.indikapharmacy.entity.Stock;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean add(Stock stock) throws Exception {
        String sql="INSERT INTO stock VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, stock.getStockID(),stock.getBatchID(),stock.getItemID(),stock.getSupplyID(),stock.getPayment(),stock.getQty());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE stock WHERE stockID=?";
        return CrudUtil.executeUpdate(sql, s);
    }

    @Override
    public boolean update(Stock stock) throws Exception {
       String sql="UPDATE stock BatchID=?,supplyID=?,itemID=?,quentity=?,payments=? WHERE stockID=?";
       return CrudUtil.executeUpdate(sql, stock.getBatchID(),stock.getSupplyID(),stock.getItemID(),stock.getQty(),stock.getPayment(),stock.getStockID());
    }

    @Override
    public Stock search(String s) throws Exception {
       String sql="SELECT * FROM stock WHERE stockID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new Stock(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getInt(5),rst.getDouble(6));
        }
        return null;
    }

    @Override
    public ArrayList<Stock> getAll() throws Exception {
        String sql="SELECT * FROM stock";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Stock> stockList = new ArrayList<>();
        while (rst.next()){
            stockList.add(new Stock(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getInt(5),rst.getDouble(6)));
        }
        return stockList;
    }
}
