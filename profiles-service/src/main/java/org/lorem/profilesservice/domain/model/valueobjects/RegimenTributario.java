package org.lorem.profilesservice.domain.model.valueobjects;

public enum RegimenTributario {
    NRUS("NRUS", "Nuevo Régimen Único Simplificado"),
    RER("RER", "Régimen Especial de Impuesto a la Renta"),
    RMT("RMT", "Régimen MYPE Tributario"),
    RG("RG", "Régimen General");

    private final String code;
    private final String description;

    RegimenTributario(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code;
    }

    public static RegimenTributario fromCode(String code) {
        if (code == null) return null;
        for (RegimenTributario r : values()) {
            if (r.code.equalsIgnoreCase(code) || r.name().equalsIgnoreCase(code)) {
                return r;
            }
        }
        throw new IllegalArgumentException("RegimenTributario no soportado: " + code);
    }
}
