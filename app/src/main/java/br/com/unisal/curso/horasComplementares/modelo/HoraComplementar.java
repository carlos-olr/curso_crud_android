package br.com.unisal.curso.horasComplementares.modelo;

import com.orm.SugarRecord;

/**
 * Created by carlos on 18/10/16.
 */

public class HoraComplementar extends SugarRecord {

    private String nome;
    private String descricao;
    private Long dataEvento;
    private Integer quantidadehoras;
    private byte[] comprovante;

    public HoraComplementar() {
    }

    public HoraComplementar(String nome, String descricao, Long dataEvento, Integer quantidadehoras, byte[] comprovante) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.quantidadehoras = quantidadehoras;
        this.comprovante = comprovante;
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
