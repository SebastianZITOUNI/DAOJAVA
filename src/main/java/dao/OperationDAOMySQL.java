package dao;

import datasourceManagement.MySQLManager;
import modele.Operation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OperationDAOMySQL implements DAOGenerique<Operation> {
    public MySQLManager mySQLManager = MySQLManager.getInstance();
    @Override
    public Operation create(Operation operation) {
        String sql = "INSERT INTO operation (intitule, date, montant, cle_compte) VALUES ('" +
                operation.getIntitule() + "', '" +
                operation.getDateOperation() + "', " +
                operation.getMontant() + ", '" +
                operation.getCleCompte() + "')";
        int id = mySQLManager.setData(sql);
        operation.setCle(id);
        return operation;
    }

    @Override
    public Operation update(Operation operation) {
        String sql = "UPDATE operation SET intitule = '" + operation.getIntitule() +
                "', date = '" + operation.getDateOperation() +
                "', montant = " + operation.getMontant() +
                ", cle_compte = '" + operation.getCleCompte() +
                "' WHERE cle = '" + operation.getCle() + "'";
        mySQLManager.setData(sql);
        return operation;
    }

    @Override
    public void delete(Operation operation) {
        String sql = "DELETE FROM operation WHERE cle = '" +
                operation.getCle() + "'";
        mySQLManager.setData(sql);
    }

    @Override
    public void saveAll(LinkedList<Operation> operations) {
        for (Operation operation : operations) {
            update(operation);
        }
    }

    @Override
    public Operation findById(int cle) {
        ResultSet rs = mySQLManager.getData("SELECT * FROM operation WHERE cle = '" + cle + "'");
        try {
            if (rs.next()) {
                return new Operation(
                        rs.getString("intitule"),
                        rs.getString("date"),
                        rs.getFloat("montant"), rs.getInt("cle_compte"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Operation> findAll() {
        ResultSet rs = mySQLManager.getData("SELECT * FROM operation");
        LinkedList<Operation> operations = new LinkedList<>();
        try {
            while (rs.next()) {
                operations.add(new Operation(
                        rs.getInt("cle"),
                        rs.getString("intitule"),
                        rs.getString("date"),
                        rs.getFloat("montant"),
                        rs.getInt("cle_compte")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    @Override
    public LinkedList<Operation> findByName(String name) {
        ResultSet rs = mySQLManager.getData("SELECT * FROM operation WHERE intitule = '" + name + "'");
        LinkedList<Operation> operations = new LinkedList<>();
        try {
            while (rs.next()) {
                operations.add(new Operation(
                        rs.getString("intitule"),
                        rs.getString("date"),
                        rs.getFloat("montant"),
                        rs.getInt("cle_compte")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }
}