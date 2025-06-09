package br.com.unemat.samuelpatricio.atividade;



public class ManifestacaoGerenciar {
    private String protocolo;
    private String tipo;
    private String descricao;
    private String nomeManifestante;
    private String dataRegistro;
    private String status;
    private String cidade;

    public ManifestacaoGerenciar(String protocolo, String tipo, String descricao, String nomeManifestante, String dataRegistro, String status, String cidade) {
        this.protocolo = protocolo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.nomeManifestante = nomeManifestante;
        this.dataRegistro = dataRegistro;
        this.status = status;
        this.cidade = cidade;
    }

    // Getters
    public String getProtocolo() { return protocolo; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public String getNomeManifestante() { return nomeManifestante; }
    public String getDataRegistro() { return dataRegistro; }
    public String getStatus() { return status; }
    public String getCidade() { return cidade; }

    // Setters
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setNomeManifestante(String nomeManifestante) { this.nomeManifestante = nomeManifestante; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }
    public void setStatus(String status) { this.status = status; }
    public void setCidade(String cidade) { this.cidade = cidade; }
}
