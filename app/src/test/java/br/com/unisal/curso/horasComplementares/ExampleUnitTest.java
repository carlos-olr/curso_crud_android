package br.com.unisal.curso.horasComplementares;

import com.orm.SugarRecord;

import org.junit.Test;

import br.com.unisal.curso.horasComplementares.model.HoraComplementar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        HoraComplementar h = new HoraComplementar();

        Long a = h.save();

        assertNotNull(SugarRecord.findById(HoraComplementar.class, a));
    }
}