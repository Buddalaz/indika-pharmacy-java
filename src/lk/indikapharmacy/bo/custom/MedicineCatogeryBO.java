package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomDTO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineCatogoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineCatogeryBO extends SuperBO {

    public boolean addMediCatogory(MedicineCatogoryDTO medicineCatogoryDTO) throws ClassNotFoundException, SQLException, Exception;

    public boolean deleteMediCatogory(String id) throws ClassNotFoundException, SQLException, Exception;

    public MedicineCatogoryDTO searchMediCatogory(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateMediCatogory(MedicineCatogoryDTO medicineCatogoryDTO) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<CustomDTO> searchOrderFromID(String id)throws ClassNotFoundException, SQLException, Exception;
}
