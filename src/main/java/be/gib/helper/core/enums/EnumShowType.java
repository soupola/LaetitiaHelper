package be.gib.helper.core.enums;

public enum EnumShowType {
    INFO("Info"),
    SPORT("Sport"),
    DEBAT("Debat"),
    POLMAG("Politiek magazine"),
    VERKIEZINGEN("Verkiezingen"),
    RADPOL("Radio (politiek)"),
    ENTERTAINMENT("Entertainment"),
    SPELPROGRAMMA("Spelprogramma"),
    GALA("Gala"),
    KEUKEN("Keuken"),
    TALKSHOW("Talkshow"),
    REALITY("Reality"),
    HUMINTEREST("Human interest"),
    REEKS("Reeks"),
    FILM("Film"),
    TVFILM("Tv-film"),
    RADVUL("Radio"),
    BEELDEN("Beelden"),
    MUZIEK("Muziek"),
    SHOPPING("Shopping"),
    ONBEKEND("Onbekend"),
    KINDEREN("Kinderen"),
    SPEL("Spel"),
    GODSDIENST("Godsdienst"),
    DOCUMENTAIRE("Documentaire"),
    MAGAZINE("Magazine");

    private String name;

    EnumShowType(String name) {
        this.name = name;
    }

    public static EnumShowType match(String name) {
        for (EnumShowType e : EnumShowType.values()) {
            if (e.name.equalsIgnoreCase(name))
                return e;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
