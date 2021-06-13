package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.SupplerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplerBO extends SuperBO {

    public boolean addSupplier(SupplerDTO supp) throws ClassNotFoundException, SQLException, Exception;

    public boolean deleteSupplier(String code) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateSupplier(SupplerDTO supp) throws ClassNotFoundException, SQLException, Exception;

    public SupplerDTO searchSupplier(String code) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<SupplerDTO> getAllSupplier() throws ClassNotFoundException, SQLException, Exception;

    public String getLastSupplierID() throws ClassNotFoundException, SQLException, Exception;
}
