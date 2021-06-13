package lk.indikapharmacy.bo;

import lk.indikapharmacy.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
            CUSTOMER, MEDICINE, MEDICINECATOGERY, ORDERRETURN, PURCHARSEORDER, STOCK, SUPPLIER, SUPPLIERRETURN, USER;
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case MEDICINE:
                return new MedicineBOImpl();
            case MEDICINECATOGERY:
                return new MedicineCatogeryBOImpl();
            case ORDERRETURN:
                return new OrderReturnBOImpl();
            case PURCHARSEORDER:
                return new PurcharseOrderBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new SupplerBOImpl();
            case SUPPLIERRETURN:
                return new SupplierReturnBOImpl();
            case USER:
                return new UserBOImpl() ;
            default:
                return null;
        }
    }

}
