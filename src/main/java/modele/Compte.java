package modele;
import java.util.LinkedList;
import java.util.Objects;

public class Compte {
    private int cle;
    private String numCompte;
    private Float solde;
    private LinkedList<Operation> lesOperations = new LinkedList<>();

    public Compte(String numCompte, Float solde) {
        this.numCompte = numCompte;
        this.solde = solde;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Compte compte = (Compte) obj;
        return Objects.equals(cle, compte.cle) &&
                Objects.equals(numCompte, compte.numCompte) &&
                Objects.equals(solde, compte.solde);
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public Float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public LinkedList<Operation> getLesOperations() {
        return lesOperations;
    }

    public void addOperation(String intitule, String date, Float montant) {
        Operation operation = new Operation(intitule, date, montant, this.cle);
        lesOperations.add(operation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numCompte);
        sb.append(", ");
        sb.append(solde);
        return sb.toString();
    }
}