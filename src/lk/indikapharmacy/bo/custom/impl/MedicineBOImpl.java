package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.MedicineBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.MedicineDAO;
import lk.indikapharmacy.dao.custom.OrderReturnDAO;
import lk.indikapharmacy.db.DBConnection;
import lk.indikapharmacy.dto.MedicineDTO;
import lk.indikapharmacy.dto.MedicineReturnDTO;
import lk.indikapharmacy.entity.Medicine;
import lk.indikapharmacy.entity.MedicineReturn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineBOImpl implements MedicineBO {


    MedicineDAO mediDTO = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINE);

    @Override
    public boolean addMedicine(MedicineDTO medi) throws ClassNotFoundException, SQLException, Exception {
        return mediDTO.add(new Medicine(
                medi.getItemID(),
                medi.getDescription(),
                medi.getProductDate(),
                medi.getExpireDate(),
                medi.getUnitPrice(),
                medi.getQtyOnHand()
        ));
    }

    @Override
    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException, Exception {
        return mediDTO.delete(code);
    }

    @Override
    public boolean updateItem(MedicineDTO medi) throws ClassNotFoundException, SQLException, Exception {
        return mediDTO.update(new Medicine(medi.getItemID(),
                medi.getDescription(),
                medi.getProductDate(),
                medi.getExpireDate(),
                medi.getUnitPrice(),
                medi.getQtyOnHand()
        ));
    }

    @Override
    public MedicineDTO searchItem(String code) throws ClassNotFoundException, SQLException, Exception {
        Medicine medi = mediDTO.search(code);
        return new MedicineDTO(medi.getItemID(),medi.getDescription(),medi.getProductDate(),medi.getExpireDate(),medi.getUnitPrice(),medi.getQtyOnHand());
    }

    @Override
    public ArrayList<MedicineDTO> getAllItems() throws ClassNotFoundException, SQLException, Exception {
        return null;
    }

    @Override
    public String itemCount() throws ClassNotFoundException, SQLException, Exception {
        return mediDTO.itemCount();
    }

    @Override
    public String genarateMediID() throws Exception {
        return mediDTO.genarateMediId();
    }




}
