package modele;

import java.util.LinkedList;

public class Banque {

    private LinkedList<Compte> lesComptes;
    public LinkedList<Compte> getLesComptes() {
        return lesComptes;
    }
    public void setLesComptes(LinkedList<Compte> lesComptes) {
        this.lesComptes = lesComptes;
    }
    public Compte addCompte(String numCompte) {
        Compte c = new Compte(numCompte, null);
        lesComptes.add(c);
        return c;
    }
}
