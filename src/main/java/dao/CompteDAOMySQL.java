package dao;

import datasourceManagement.MySQLManager;
import modele.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CompteDAOMySQL implements DAOGenerique<Compte> {
    public MySQLManager mySQLManager = MySQLManager.getInstance();
    @Override
    public Compte create(Compte compte) {
        String sql = "INSERT INTO compte (num_compte, solde) VALUES ('" +
                compte.getNumCompte() + "', " +
                compte.getSolde() + ")";
        int id = mySQLManager.setData(sql);
        compte.setCle(id);
        return compte;
    }


    @Override
    public Compte update(Compte compte) {
        String sql = "UPDATE compte SET num_compte = '"+ compte.getNumCompte() +
                "', solde = " + compte.getSolde() +
                " WHERE cle = '" + compte.getCle() + "'";
        mySQLManager.setData(sql);
        return compte;
    }

    @Override
    public void delete(Compte compte) {
        String sql = "DELETE FROM compte WHERE cle = '" + compte.getCle() + "'";
        mySQLManager.setData(sql);
    }

    @Override
    public void saveAll(LinkedList<Compte> comptes) {
        for (Compte compte : comptes) {
            update(compte);
        }
    }

    @Override
    public Compte findById(int cle) {
        ResultSet rs = mySQLManager.getData("SELECT * FROM compte WHERE cle = '" + cle + "'");
        try {
            if (rs.next()) {
                Compte compte = new Compte(
                        rs.getString("num_compte"),
                        rs.getFloat("solde"));
                compte.setCle(rs.getInt("cle"));
                return compte;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Compte> findAll() {
        ResultSet rs = mySQLManager.getData("SELECT * FROM compte");
        LinkedList<Compte> comptes = new LinkedList<>();
        try {
            while (rs.next()) {
                Compte compte = new Compte(
                        rs.getString("num_compte"),
                        rs.getFloat("solde"));
                compte.setCle(
                        rs.getInt("cle"));
                comptes.add(compte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comptes;
    }

    @Override
    public LinkedList<Compte> findByName(String name) {
        ResultSet rs = mySQLManager.getData("SELECT * FROM compte WHERE num_compte = '" + name + "'");
        LinkedList<Compte> comptes = new LinkedList<>();
        try {
            while (rs.next()) {
                Compte compte = new Compte(
                        rs.getString("num_compte"),
                        rs.getFloat("solde"));
                compte.setCle(rs.getInt("cle"));
                comptes.add(compte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comptes;
    }
}