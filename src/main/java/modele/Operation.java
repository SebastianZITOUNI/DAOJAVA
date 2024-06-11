package modele;


public class Operation {
    private int cle;
    private String intitule;
    private Float montant;
    private String dateOperation;
    private int cleCompte;

    public Operation(String intitule, String dateOperation, Float montant, int cleCompte) {
        this.intitule = intitule;
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.cleCompte = cleCompte;
    }
    public Operation(int cle ,String intitule, String dateOperation, Float montant, int cleCompte) {
        this.cle = cle;
        this.intitule = intitule;
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.cleCompte = cleCompte;
    }
    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public int getCleCompte() {
        return cleCompte;
    }

    public void setCleCompte(int cleCompte) {
        this.cleCompte = cleCompte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(intitule);
        sb.append(" ");
        sb.append(dateOperation);
        sb.append(" ");
        sb.append(montant);
        return sb.toString();
    }
}