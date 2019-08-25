package core.enums;

public enum EnumShowType {
    REALITY_SHOW("télé réalité"),
    SPORT("sport"),
    DIVERT("divertissement");
    private String name;

    EnumShowType(String name) {
        this.name = name;
    }

    public static EnumShowType match(String name) {
        for (EnumShowType e : EnumShowType.values()) {
            if (e.name.matches(name))
                return e;
        }
        return null;
    }
}
