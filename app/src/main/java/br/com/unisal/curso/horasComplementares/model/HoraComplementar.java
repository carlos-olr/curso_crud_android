package br.com.unisal.curso.horasComplementares.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by carlos on 18/10/16.
 */

public class HoraComplementar extends SugarRecord implements Serializable {

    private String nome;
    private String descricao;
    private Long dataEvento;
    private Integer quantidadeHoras;
    private byte[] comprovante;

    public HoraComplementar() {
    }

    public HoraComplementar(String nome, String descricao, Long dataEvento,
        Integer quantidadeHoras, byte[] comprovante) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.quantidadeHoras = quantidadeHoras;
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

    public Integer getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(Integer quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }
}
