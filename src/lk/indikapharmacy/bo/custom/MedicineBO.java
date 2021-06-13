package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineDTO;
import lk.indikapharmacy.dto.MedicineReturnDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineBO extends SuperBO {

    public boolean addMedicine(MedicineDTO medi) throws ClassNotFoundException, SQLException, Exception;

    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateItem(MedicineDTO medi) throws ClassNotFoundException, SQLException, Exception;

    public MedicineDTO searchItem(String code) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<MedicineDTO> getAllItems() throws ClassNotFoundException, SQLException, Exception;

    public String itemCount() throws ClassNotFoundException, SQLException, Exception;

    public String genarateMediID() throws Exception;



}
