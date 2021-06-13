package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.SupplerBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.SupplierDAO;
import lk.indikapharmacy.dto.SupplerDTO;
import lk.indikapharmacy.entity.Suppler;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplerBOImpl implements SupplerBO {

    SupplierDAO supDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean addSupplier(SupplerDTO supp) throws ClassNotFoundException, SQLException, Exception {
        return supDAO.add(new Suppler(supp.getSupplyID(),supp.getAddress(),supp.getSupplyName(),supp.getEmail(),supp.getContact()));
    }

    @Override
    public boolean deleteSupplier(String code) throws ClassNotFoundException, SQLException, Exception {
        return supDAO.delete(code);
    }

    @Override
    public boolean updateSupplier(SupplerDTO supp) throws ClassNotFoundException, SQLException, Exception {
        return supDAO.update(new Suppler(supp.getSupplyID(),supp.getAddress(),supp.getSupplyName(),supp.getEmail(),supp.getContact()));
    }

    @Override
    public SupplerDTO searchSupplier(String code) throws ClassNotFoundException, SQLException, Exception {
        Suppler search = supDAO.search(code);
        return new SupplerDTO(search.getSupplyID(),search.getAddress(),search.getName(),search.getEmail(),search.getContact());
    }

    @Override
    public ArrayList<SupplerDTO> getAllSupplier() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<Suppler> all = supDAO.getAll();
        ArrayList<SupplerDTO> suppDTO = new ArrayList<>();
        for (Suppler s: all) {
                suppDTO.add(new SupplerDTO(s.getSupplyID(),s.getAddress(),s.getName(),s.getEmail(),s.getContact()));
        }
        return suppDTO;
    }

    @Override
    public String getLastSupplierID() throws ClassNotFoundException, SQLException, Exception {
        return supDAO.genarateSupplierID();
    }
}
