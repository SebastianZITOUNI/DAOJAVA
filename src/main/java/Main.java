import dao.CompteDAOMySQL;
import dao.DAOGenerique;
import dao.OperationDAOMySQL;
import daoFactory.DAOFactory;
import datasourceManagement.MySQLManager;
import modele.Compte;
import modele.DAOType;
import modele.Operation;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Compte compte = new Compte("14", 1000f);
        DAOGenerique monDAO = DAOFactory.getDAOFactory(DAOType.MYSQL).getCompteDAO();
        monDAO.create(compte);
        System.out.println("Created Compte: " + compte.getNumCompte());
//
        Operation operation = new Operation("Deposit", "2022-03-01", 500f, compte.getCle());
        monDAO = DAOFactory.getDAOFactory(DAOType.MYSQL).getOperationDAO();
        monDAO.create(operation);
        System.out.println("Created Operation: " + operation.getIntitule());
//
        compte.setSolde(1500f);
        monDAO = DAOFactory.getDAOFactory(DAOType.MYSQL).getCompteDAO();
        monDAO.update(compte);
        System.out.println("Updated Compte: " + compte.getSolde());
////
//        operation.setIntitule("Withdraw");
//        operation = DAOFactory.getOperationDAO().update(operation);
//        System.out.println("Updated Operation: " + operation.getIntitule());
//

        LinkedList<Operation> operations = DAOFactory.getDAOFactory(DAOType.MYSQL).getOperationDAO().findAll();
        System.out.println("All Operations: " + operations.size());

        // Delete all operations
        for (Operation op : operations) {
            DAOFactory.getDAOFactory(DAOType.MYSQL).getOperationDAO().delete(op);
            System.out.println("Deleted Operation: " + op.getCle());
        }

        LinkedList<Compte> comptes = DAOFactory.getDAOFactory(DAOType.MYSQL).getCompteDAO().findAll();
        System.out.println("All Comptes: " + comptes.size());

        // Delete all comptes
        for (Compte cp : comptes) {
            DAOFactory.getDAOFactory(DAOType.MYSQL).getCompteDAO().delete(cp);
            System.out.println("Deleted Compte: " + cp.getCle());
        }
    }
}