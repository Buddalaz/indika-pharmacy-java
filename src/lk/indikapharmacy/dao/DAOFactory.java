package lk.indikapharmacy.dao;

import lk.indikapharmacy.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, MEDICINECATOGERY, MEDICINE, MEDICINEDETAIL, MEDICINEORDER, ORDERRETURN, STOCK, SUPPLIER, SUPPLIERRETURN, USER, QUERY;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case MEDICINECATOGERY:
                return new MedicineCatogeryDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case MEDICINEDETAIL:
                return new MedicineDetailDAOImpl();
            case MEDICINEORDER:
                return new MedicineOrderDAOImpl();
            case ORDERRETURN:
                return new OrderReturnDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIER:
                return new SuppilerDAOImpl();
            case SUPPLIERRETURN:
                return new SupplierReturnDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;

        }
    }

}
