package modele;

import dao.DAOGenerique;
import daoFactory.DAOFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.LinkedList;

public class BanqueFacade {
    private DAOFactory daoFactory;
    private DAOGenerique daoGenerique;
    private Banque banque;

    public BanqueFacade() {
        this.daoFactory = DAOFactory.getDAOFactory(DAOType.MYSQL);
        this.banque = new Banque();
    }
    public LinkedList<Compte> getLesComptes() {
        daoGenerique = daoFactory.getCompteDAO();
        return daoGenerique.findAll();
    }
    public Compte findCompte(int cle) {
        daoGenerique = daoFactory.getCompteDAO();
        return (Compte) daoGenerique.findById(cle);
    }
    public Compte ajouterCompte(String numCompte, float solde) {
        Compte compte = new Compte(numCompte, solde);
        daoGenerique = daoFactory.getCompteDAO();
        daoGenerique.create(compte);
        return compte;
    }
    public Compte enregistrerCompte(int cle) {
        daoGenerique = daoFactory.getCompteDAO();
        return (Compte) daoGenerique.findById(cle);
    }


    public Compte ajouterOperation(String intitule, String date, float montant, Compte compte) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dateObj = inputFormat.parse(date);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(dateObj);

            Operation operation = new Operation(intitule, formattedDate, montant, compte.getCle());
            compte.addOperation(intitule, formattedDate, montant);
            daoGenerique = daoFactory.getOperationDAO();
            daoGenerique.create(operation);
            return compte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void saveAll() {
        daoGenerique = daoFactory.getCompteDAO();
        daoGenerique.saveAll(banque.getLesComptes());
    }
}
