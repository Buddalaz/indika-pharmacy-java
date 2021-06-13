package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.PurcharseOrderBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.CustomerDAO;
import lk.indikapharmacy.dao.custom.MedicineDAO;
import lk.indikapharmacy.dao.custom.MedicineDetailDAO;
import lk.indikapharmacy.dao.custom.MedicineOrderDAO;
import lk.indikapharmacy.db.DBConnection;
import lk.indikapharmacy.dto.*;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.Medicine;
import lk.indikapharmacy.entity.MedicineDetails;
import lk.indikapharmacy.entity.MedicineOrders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurcharseOrderBOImpl implements PurcharseOrderBO {

    CustomerDAO customer = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    MedicineDAO mediDAO = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINE);
    MedicineOrderDAO medicineOrderDAO = (MedicineOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINEORDER);
    MedicineDetailDAO medicineDetailDAO = (MedicineDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINEDETAIL);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException, Exception {
        ArrayList<Customer> all = customer.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO dto = new CustomerDTO(c.getCustID(), c.getCustName(), c.getPhoneNumber(), c.getCustAddress());
            allCustomers.add(dto);
        }
        return allCustomers;
    }

    @Override
    public ArrayList<MedicineDTO> getAllMedicine() throws Exception {
        ArrayList<Medicine> allMedicine = mediDAO.getAll();
        ArrayList<MedicineDTO> allMedi = new ArrayList<>();
        for (Medicine m : allMedicine) {
            allMedi.add(new MedicineDTO(m.getItemID(), m.getDescription(), m.getProductDate(), m.getExpireDate(), m.getUnitPrice(), m.getQtyOnHand()));
        }
        return allMedi;

    }

    @Override
    public MedicineDTO searchMedicine(String medicinCode) throws Exception {
        Medicine searchMedi = mediDAO.search(medicinCode);
        return new MedicineDTO(searchMedi.getItemID(), searchMedi.getDescription(), searchMedi.getProductDate(),
                searchMedi.getExpireDate(), searchMedi.getUnitPrice(), searchMedi.getQtyOnHand());

    }

    @Override
    public boolean placeOrder(MedicineOrdersDTO dto) throws ClassNotFoundException, SQLException, Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        MedicineOrders medicinOder = new MedicineOrders(dto.getOrderID(),dto.getOrderDate(), dto.getCustID(), dto.getUserID(),
                dto.getTotal(),dto.getCash(),dto.getBalance());
        boolean addmediOrder = medicineOrderDAO.add(medicinOder);
        try {
            if (addmediOrder) {
                for (MedicineDetailsDTO mdDto : dto.getMedicineDetailsDTOS()) {
                    MedicineDetails medicineDetails = new MedicineDetails(mdDto.getOrderID(),mdDto.getItemID(),mdDto.getDescription(),
                            mdDto.getUnitPrice(),mdDto.getQty(), mdDto.getTotal());
                    boolean addMediDetail = medicineDetailDAO.add(medicineDetails);
                    if (addMediDetail) {
                        boolean updateStock = mediDAO.updateStockQuentity(medicineDetails);
                        if (updateStock) {
                            conn.commit();
                        }
                    }
                }
                return true;
            }else {
                conn.rollback();
                return false;
            }
        } finally {
            conn.setAutoCommit(true);
        }
        //return true;
    }

    @Override
    public ArrayList<MedicineOrdersOnlyDTO> getAllMedicineOrders() throws Exception {
        ArrayList<MedicineOrders> all = medicineOrderDAO.getAll();
        ArrayList<MedicineOrdersOnlyDTO> medicineOrdersDTOS = new ArrayList<>();
        for (MedicineOrders m : all) {
            medicineOrdersDTOS.add(new MedicineOrdersOnlyDTO(m.getOrderID(),m.getOrderDate(),m.getCustID(),m.getUserID()));
        }
        return medicineOrdersDTOS;
    }

    @Override
    public String getLastOrderID() throws ClassNotFoundException, SQLException, Exception {
        return medicineOrderDAO.genarateOrderID();
    }

    @Override
    public String orderCount() throws ClassNotFoundException, SQLException, Exception {
        return medicineOrderDAO.orderCount();
    }
}




