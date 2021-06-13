package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.OrderReturnBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.MedicineDAO;
import lk.indikapharmacy.dao.custom.OrderReturnDAO;
import lk.indikapharmacy.db.DBConnection;
import lk.indikapharmacy.dto.MedicineReturnDTO;
import lk.indikapharmacy.entity.MedicineReturn;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderReturnBOImpl implements OrderReturnBO {

    OrderReturnDAO orderRetDAO = (OrderReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERRETURN);
    MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINE);

    @Override
    public boolean addOrderReturn(MedicineReturnDTO mediReturnDTO) throws ClassNotFoundException, SQLException, Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        MedicineReturn medicineReturn = new MedicineReturn(mediReturnDTO.getReturnID(), mediReturnDTO.getDate(), mediReturnDTO.getOrderID(), mediReturnDTO.getItemID()
                , mediReturnDTO.getQty());
        boolean addReturnOrder = orderRetDAO.add(medicineReturn);

        try {
            if (addReturnOrder){
                boolean mediUpdate = medicineDAO.updateMediReturnStockQuentity(medicineReturn);
                if (mediUpdate){
                    connection.commit();
                }
                return true;
            }else {
                connection.rollback();
                return false;
            }
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getLastOrderReturnID() throws ClassNotFoundException, SQLException, Exception {
        return orderRetDAO.genarateOrderReturnID();
    }

    @Override
    public boolean updateOrderReturn(MedicineReturnDTO mediReturnDTO) throws ClassNotFoundException, SQLException, Exception {
        return orderRetDAO.update(new MedicineReturn(mediReturnDTO.getReturnID(),mediReturnDTO.getDate(),mediReturnDTO.getOrderID(),mediReturnDTO.getItemID(),
                mediReturnDTO.getQty()));
    }


}
