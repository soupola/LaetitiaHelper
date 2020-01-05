package be.gib.helper.core.enums;

public enum EnumChaine {
    LA_UNE("La une", EnumOrigine.FBE, "La Une"),
    AB3("Ab3", EnumOrigine.FBE, "Ab3"),
    RTL("RTL-TVI", EnumOrigine.FBE, "Rtl-tvi"),
    VIER("Vier", EnumOrigine.NBE, "Vier"),
    VTM("Vtm", EnumOrigine.NBE, "Vtm"),
    EEN("één", EnumOrigine.NBE, "één");
    private String name;
    private EnumOrigine origine;
    private String code;

    EnumChaine(String name, EnumOrigine origine, String code) {
        this.name = name;
        this.origine = origine;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public EnumOrigine getOrigine() {
        return origine;
    }

    public String getCode() {
        return code;
    }

    public static EnumChaine match(String name) {
        for (EnumChaine e : EnumChaine.values()) {
            if (e.code.equalsIgnoreCase(name))
                return e;
        }
        return null;
    }
}
