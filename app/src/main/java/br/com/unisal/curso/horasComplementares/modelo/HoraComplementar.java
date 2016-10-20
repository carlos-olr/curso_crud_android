package br.com.unisal.curso.horasComplementares.modelo;

/**
 * Created by carlos on 18/10/16.
 */

public class HoraComplementar {

    private Long id;
    private String nome;
    private String descricao;
    private Long dataEvento;
    private Integer quantidadehoras;
    private byte[] comprovante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Long dataEvento) {
        this.dataEvento = dataEvento;
    }

    public byte[] getComprovante() {
        return comprovante;
    }

    public void setComprovante(byte[] comprovante) {
        this.comprovante = comprovante;
    }

    public Integer getQuantidadehoras() {
        return quantidadehoras;
    }

    public void setQuantidadehoras(Integer quantidadehoras) {
        this.quantidadehoras = quantidadehoras;
    }
}
