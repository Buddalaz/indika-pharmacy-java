package lk.indikapharmacy.bo.custom.impl;

import lk.indikapharmacy.bo.custom.StockBO;
import lk.indikapharmacy.dao.DAOFactory;
import lk.indikapharmacy.dao.custom.StockDAO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.StockDTO;
import lk.indikapharmacy.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDAO stDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);


    @Override
    public boolean addStock(StockDTO stockDTO) throws ClassNotFoundException, SQLException, Exception {
        return stDAO.add(new Stock(stockDTO.getStockID(),stockDTO.getBatchID(),stockDTO.getSupplyID(),stockDTO.getItemID(),
                stockDTO.getQty(),stockDTO.getPayment()));
    }

    @Override
    public boolean deleteStock(String id) throws ClassNotFoundException, SQLException, Exception {
        return stDAO.delete(id);
    }

    @Override
    public StockDTO searchStock(String id) throws ClassNotFoundException, SQLException, Exception {
        Stock search = stDAO.search(id);
        return new StockDTO(search.getStockID(),search.getBatchID(),search.getSupplyID(),search.getItemID(),search.getQty(),search.getPayment());
    }

    @Override
    public ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException, Exception {
        ArrayList<Stock> all = stDAO.getAll();
        ArrayList<StockDTO> stockList = new ArrayList<>();
        for (Stock stock : all) {
            StockDTO stockDTO = new StockDTO(stock.getStockID(),stock.getBatchID(),stock.getSupplyID(),stock.getItemID(),stock.getQty(),stock.getPayment());
            stockList.add(stockDTO);
        }
        return stockList;
    }

    @Override
    public boolean updateStock(StockDTO stockDTO) throws ClassNotFoundException, SQLException, Exception {
        return stDAO.update(new Stock(stockDTO.getStockID(),stockDTO.getBatchID(),stockDTO.getSupplyID(),stockDTO.getItemID(),stockDTO.getQty(),stockDTO.getPayment()));
    }
}
