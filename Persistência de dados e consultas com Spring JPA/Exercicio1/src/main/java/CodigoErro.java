public enum CodigoErro {

    OK(200, "OK"),
    CREATED(201, "Creado"),
    NO_CONTENT(204,"Sem conteúdo"),
    BAD_REQUEST(400, "A 404 status code only indicates that the resource is missing"),
    NOT_FOUND(404, "Não encontrado"),
    ERROR(500, "Erro do servidor");

    private int codigo;
    private String descricao;

    CodigoErro(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
