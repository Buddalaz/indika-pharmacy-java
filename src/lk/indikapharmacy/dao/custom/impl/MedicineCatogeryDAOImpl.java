package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.MedicineCatogeryDAO;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.MedicineCatogory;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicineCatogeryDAOImpl implements MedicineCatogeryDAO {
    @Override
    public boolean add(MedicineCatogory mediCat) throws Exception {
        String sql="INSERT INTO medicinecatogory VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,mediCat.getCatID(),mediCat.getItemID(),mediCat.getDescription(),mediCat.getCatogryName());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM medicinecatogory WHERE catagoryID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(MedicineCatogory mediCat) throws Exception {
        String sql = "UPDATE medicinecatogory SET itemID=?, description=?, catogoryName=? WHERE catagoryID=?";
        return CrudUtil.executeUpdate(sql, mediCat.getItemID(),mediCat.getDescription(),mediCat.getCatogryName(), mediCat.getCatID());
    }

    @Override
    public MedicineCatogory search(String s) throws Exception {
        String sql ="SELECT * FROM medicinecatogory WHERE catagoryID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new MedicineCatogory(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public ArrayList<MedicineCatogory> getAll() throws Exception {
        String sql = "SELECT * FROM medicinecatogory";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<MedicineCatogory> medicineCatogoryArrayList = new ArrayList<>();
        while (rst.next()){
            medicineCatogoryArrayList.add(new MedicineCatogory(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return medicineCatogoryArrayList;
    }
}
