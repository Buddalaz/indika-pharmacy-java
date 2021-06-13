package lk.indikapharmacy.bo.custom;

import lk.indikapharmacy.bo.SuperBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {

    public boolean addStock(StockDTO stockDTO) throws ClassNotFoundException, SQLException, Exception;

    public boolean deleteStock(String id) throws ClassNotFoundException, SQLException, Exception;

    public StockDTO searchStock(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException, Exception;

    public boolean updateStock(StockDTO stockDTO) throws ClassNotFoundException, SQLException, Exception;

}
