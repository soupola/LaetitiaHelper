package be.gib.helper.core.enums;

public enum EnumOrigine {
    AS("", "AS"),
    AT("", "AT"),
    AU("Australien", "AU"),
    CA("Canadien", "CA"),
    CA_FBE("Canadien francophone", "CA/FBE"),
    DE("Allemand", "DE"),
    DE_AT("", "DE/AT"),
    ES("Epagnol", "ES"),
    FBE("Belge francophone", "FBE"),
    FBE_FR("Franco-Belge francophone", "FBE/FR"),
    FR("Francais", "FR"),
    FR_CA("Franco-Canadien", "FR/CA"),
    FR_FBE("Franco-Belge francophone", "FR/FBE"),
    FR_UK("Franco-Anglais", "FR/UK"),
    FR_US("Franco-Americain", "FR/US"),
    GE("", "GE"),
    IT("Italien", "IT"),
    NBE("Belge néerlandophone", "NBE"),
    NBE_NL("", "NBE/NL"),
    NL("Hollendais", "NL"),
    NZ("", "NZ"),
    ONBEKEND("", "Onbekend"),
    SZ("", "SZ"),
    UK("Anglais", "UK"),
    UK_FR("", "UK/FR"),
    UK_US("", "UK/US"),
    US("Americain", "US"),
    US_CZ_UK_RO("", "US, CZ, UK, RO"),
    US_CA("", "US/CA"),
    US_CA_GE("", "US/CA/GE"),
    US_CL("", "US/CL"),
    US_KE_IN("", "US/KE/IN"),
    US_UK("", "US/UK"),
    US_UK_FR("", "US/UK/FR"),
    US_UK_GE("", "US/UK/GE"),
    RESTE("Autres", "");
    private String name;
    private String code;

    EnumOrigine(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static EnumOrigine match(String name) {
        for (EnumOrigine e : EnumOrigine.values()) {
            if (e.code.equalsIgnoreCase(name))
                return e;
        }
        return null;
    }
}
