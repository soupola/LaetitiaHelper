package be.gib.helper.core.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumShowCategory {
    INFOPOL(Arrays.asList(EnumShowType.INFO, EnumShowType.SPORT, EnumShowType.DEBAT, EnumShowType.POLMAG, EnumShowType.VERKIEZINGEN, EnumShowType.RADPOL)),
    ENTERTAINMENT(Arrays.asList(EnumShowType.ENTERTAINMENT, EnumShowType.SPELPROGRAMMA, EnumShowType.GALA, EnumShowType.KEUKEN, EnumShowType.TALKSHOW, EnumShowType.REALITY, EnumShowType.HUMINTEREST)),
    FICTIE(Arrays.asList(EnumShowType.REEKS, EnumShowType.FILM, EnumShowType.TVFILM)),
    VULLING(Arrays.asList(EnumShowType.RADVUL, EnumShowType.BEELDEN, EnumShowType.MUZIEK, EnumShowType.SHOPPING)),
    ANDER(Arrays.asList(EnumShowType.ONBEKEND, EnumShowType.KINDEREN, EnumShowType.SPEL, EnumShowType.GODSDIENST)),
    MAGDOC(Arrays.asList(EnumShowType.DOCUMENTAIRE,EnumShowType.MAGAZINE))
    ;
    private List<EnumShowType> types;

    EnumShowCategory(List<EnumShowType> types) {
        this.types = types;
    }

    public List<EnumShowType> getTypes() {
    return types;
    }
}
