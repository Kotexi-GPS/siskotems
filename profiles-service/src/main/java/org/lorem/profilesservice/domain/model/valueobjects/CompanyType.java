package org.lorem.profilesservice.domain.model.valueobjects;

public enum CompanyType {
    SACS("S.A.C.S.", "Sociedad por Acciones Cerrada Simplificada"),
    SA("S.A.", "Sociedad Anónima"),
    SAC("S.A.C.", "Sociedad Anónima Cerrada"),
    SRL("S.R.L.", "Sociedad Comercial de Responsabilidad Limitada"),
    EIRL("E.I.R.L.", "Empresario Individual de Responsabilidad Limitada"),
    SAA("S.A.A.", "Sociedad Anónima Abierta");

    private final String code;
    private final String description;

    CompanyType(String code, String description) {
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

    public static CompanyType fromCode(String code) {
        if (code == null) return null;
        for (CompanyType t : values()) {
            if (t.code.equalsIgnoreCase(code) || t.name().equalsIgnoreCase(code)) {
                return t;
            }
        }
        throw new IllegalArgumentException("TipoEmpresa no soportado: " + code);
    }
}
