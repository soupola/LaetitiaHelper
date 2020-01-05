package be.gib.helper.core.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumShowCategory {
    INFOPOL("Info/Politiek",Arrays.asList(EnumShowType.INFO, EnumShowType.SPORT, EnumShowType.DEBAT, EnumShowType.POLMAG, EnumShowType.VERKIEZINGEN, EnumShowType.RADPOL)),
    ENTERTAINMENT("Entertainment",Arrays.asList(EnumShowType.ENTERTAINMENT, EnumShowType.SPELPROGRAMMA, EnumShowType.GALA, EnumShowType.KEUKEN, EnumShowType.TALKSHOW, EnumShowType.REALITY, EnumShowType.HUMINTEREST)),
    FICTIE("Fictie",Arrays.asList(EnumShowType.REEKS, EnumShowType.FILM, EnumShowType.TVFILM)),
    VULLING("Vulling",Arrays.asList(EnumShowType.RADVUL, EnumShowType.BEELDEN, EnumShowType.MUZIEK, EnumShowType.SHOPPING)),
    ANDER("Ander",Arrays.asList(EnumShowType.ONBEKEND, EnumShowType.KINDEREN, EnumShowType.SPEL, EnumShowType.GODSDIENST)),
    MAGDOC("Magazine/Documentaire",Arrays.asList(EnumShowType.DOCUMENTAIRE,EnumShowType.MAGAZINE))
    ;
    private List<EnumShowType> types;
    private String name;

    EnumShowCategory(String name,List<EnumShowType> types) {
        this.name = name;
        this.types = types;
    }

    public List<EnumShowType> getTypes() {
    return types;
    }

    public String getName() {
        return name;
    }

    public static EnumShowCategory getCategory(EnumShowType type) {
        for (EnumShowCategory category : EnumShowCategory.values()) {
            if (category.types.contains(type)) {
                return category;
            }
        }
        return null;
    }
}
