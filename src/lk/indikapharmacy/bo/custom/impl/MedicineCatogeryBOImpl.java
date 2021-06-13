package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.MedicineCatogeryBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.MedicineCatogeryDAO;
import lk.indikapharmacy.dao.custom.QueryDAO;
import lk.indikapharmacy.dto.CustomDTO;
import lk.indikapharmacy.dto.MedicineCatogoryDTO;
import lk.indikapharmacy.entity.CustomEntity;
import lk.indikapharmacy.entity.MedicineCatogory;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineCatogeryBOImpl implements MedicineCatogeryBO {

    MedicineCatogeryDAO mediCatogeryDAO = (MedicineCatogeryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEDICINECATOGERY);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean addMediCatogory(MedicineCatogoryDTO medicineCatogoryDTO) throws ClassNotFoundException, SQLException, Exception {
        return mediCatogeryDAO.add(new MedicineCatogory(medicineCatogoryDTO.getCatID(),medicineCatogoryDTO.getItemID(),medicineCatogoryDTO.getDescription(),
                medicineCatogoryDTO.getCatogryName()));
    }

    @Override
    public boolean deleteMediCatogory(String id) throws ClassNotFoundException, SQLException, Exception {
        return mediCatogeryDAO.delete(id);
    }

    @Override
    public MedicineCatogoryDTO searchMediCatogory(String id) throws ClassNotFoundException, SQLException, Exception {
        MedicineCatogory search = mediCatogeryDAO.search(id);
        return new MedicineCatogoryDTO(search.getCatID(),search.getItemID(),search.getDescription(),search.getCatogryName());
    }

    @Override
    public boolean updateMediCatogory(MedicineCatogoryDTO medicineCatogoryDTO) throws ClassNotFoundException, SQLException, Exception {
        return mediCatogeryDAO.update(new MedicineCatogory(medicineCatogoryDTO.getCatID(),medicineCatogoryDTO.getItemID(),medicineCatogoryDTO.getDescription(),
                medicineCatogoryDTO.getCatogryName()));
    }

    @Override
    public ArrayList<CustomDTO> searchOrderFromID(String id) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<CustomEntity> orderDetailsFromOID = queryDAO.getOrderDetailsFromOID(id);
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity customEntity : orderDetailsFromOID) {
            customDTOS.add(new CustomDTO(customEntity.getCatogoryID(),customEntity.getMediID(),customEntity.getMediName(),customEntity.getMediUseDescription(),
                    customEntity.getCatogoryName()));
        }
        return customDTOS;
    }
}
