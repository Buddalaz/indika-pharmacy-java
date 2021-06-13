package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineDTO;
import lk.indikapharmacy.dto.MedicineOrdersDTO;
import lk.indikapharmacy.dto.MedicineOrdersOnlyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurcharseOrderBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException, Exception;

    public ArrayList<MedicineDTO> getAllMedicine() throws Exception;

    public MedicineDTO searchMedicine(String medicinCode) throws Exception;

    public boolean placeOrder(MedicineOrdersDTO dto) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<MedicineOrdersOnlyDTO> getAllMedicineOrders() throws Exception;

    public String getLastOrderID() throws ClassNotFoundException, SQLException, Exception;

    public String orderCount() throws ClassNotFoundException, SQLException, Exception;
}
