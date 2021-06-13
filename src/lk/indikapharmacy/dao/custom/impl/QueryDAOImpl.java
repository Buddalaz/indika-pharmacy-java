package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.QueryDAO;
import lk.indikapharmacy.entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> getOrderDetailsFromOID(String oid) throws ClassNotFoundException, SQLException {
        String sql = "select medicinecatogory.catagoryID, medicine.itemID, medicine.description, medicinecatogory.description, " +
                "medicinecatogory.catogoryName from medicine inner join medicinecatogory on medicine.itemID = medicinecatogory.itemID where " +
                "medicine.description=?";
        ResultSet rst = CrudUtil.executeQuery(sql, oid);
        ArrayList<CustomEntity> allDetails = new ArrayList();
        while (rst.next()){
            String catoID = rst.getString(1);
            String itemID = rst.getString(2);
            String mediName = rst.getString(3);
            String mediDescription = rst.getString(4);
            String catoName = rst.getString(5);

            CustomEntity customEntity = new CustomEntity(catoID,itemID,mediName,mediDescription,catoName);
            allDetails.add(customEntity);
        }
        return allDetails;
    }
}
