package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineReturnDTO;

import java.sql.SQLException;

public interface OrderReturnBO extends SuperBO {

    public boolean addOrderReturn(MedicineReturnDTO mediReturnDTO) throws ClassNotFoundException, SQLException, Exception;

    public String getLastOrderReturnID() throws ClassNotFoundException, SQLException, Exception;

    public boolean updateOrderReturn(MedicineReturnDTO mediReturnDTO) throws ClassNotFoundException, SQLException, Exception;
}
