package med.voll.api.dto;

public enum Especialidade {

    ORTOPEDIA("Ortopedia"),
    CARDIOLOGIA("Cardiologia"),
    GINECOLOGIA("Ginecologia"),
    DERMATOLOGIA("Dermatologia");

    private String usuario;

    Especialidade(String usuario) {
        this.usuario = usuario;
    }

    public static Especialidade fromString(String text) {
        for (Especialidade especialidade : Especialidade.values()) {
            if (especialidade.usuario.equalsIgnoreCase(text)) {
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Especialidade não encontrada");
    }
}
