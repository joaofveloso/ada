package br.com.ibis.controllers.models;

public class Hotel {

    private String id;
    private String nome;
    private String pais;
    private String estado;
    private String cidade;
        private Integer quantidadeDeQuartos;

    public Hotel() {
    }

    public Hotel(String id, String nome, String pais, String estado, String cidade, Integer quantidadeDeQuartos) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.quantidadeDeQuartos = quantidadeDeQuartos;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public Integer getQuantidadeDeQuartos() {
        return quantidadeDeQuartos;
    }
}
