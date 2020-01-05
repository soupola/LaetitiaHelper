package be.gib.helper.core.enums;

import java.util.Arrays;
import java.util.List;

public enum EnumChaineCategory {
    CHAINE_FR(Arrays.asList(EnumChaine.LA_UNE, EnumChaine.RTL, EnumChaine.AB3), "Chaines francophones"),
    CHAINE_NL(Arrays.asList(EnumChaine.EEN, EnumChaine.VIER, EnumChaine.VTM), "Chaines néerlandophones");
    private List<EnumChaine> chaines;
    private String nom;

    EnumChaineCategory(List<EnumChaine> chaines, String nom) {
        this.chaines = chaines;
        this.nom = nom;
    }

    public List<EnumChaine> getChaines() {
        return chaines;
    }

    public String getNom() {
        return nom;
    }
}
