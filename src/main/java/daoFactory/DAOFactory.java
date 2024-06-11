package daoFactory;

import dao.*;
import modele.DAOType;

public abstract class DAOFactory {
    public DAOGenerique CompteDAO;
    public DAOGenerique OperationDAO;

    public static DAOFactory getDAOFactory(DAOType type) {
        switch (type) {
            case MYSQL:
                return new DAOMySQLFactory();/*
            case JSON:
                return new DAOJsonFactory();*/
            default:
                throw new IllegalArgumentException("Invalid DAOType: " + type);
        }
    }
    public abstract DAOGenerique getCompteDAO();
    public abstract DAOGenerique getOperationDAO();
}