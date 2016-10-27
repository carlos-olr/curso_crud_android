package br.com.unisal.curso.horasComplementares.repository;

import com.google.common.collect.Lists;
import com.orm.SugarRecord;

import java.util.List;

import br.com.unisal.curso.horasComplementares.model.HoraComplementar;

/**
 * Created by carlos on 18/10/16.
 */
public class HoraComplementarRepository {

    public Long salvar(HoraComplementar hc) {
        return SugarRecord.save(hc);
    }

    public boolean deletar(Long id) {
        return SugarRecord.delete(SugarRecord.findById(HoraComplementar.class, id));
    }

    public List<HoraComplementar> listar() {
        return Lists.newArrayList(SugarRecord.findAll(HoraComplementar.class));
    }

    public HoraComplementar buscarPorId(Long id) {
        return SugarRecord.findById(HoraComplementar.class, id);
    }

}
