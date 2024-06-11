package daoFactory;

import dao.CompteDAOMySQL;
import dao.OperationDAOMySQL;

public class DAOMySQLFactory extends DAOFactory {
    @Override
    public CompteDAOMySQL getCompteDAO() {
        return new CompteDAOMySQL();
    }

    @Override
    public OperationDAOMySQL getOperationDAO() {
        return new OperationDAOMySQL();
    }

}
