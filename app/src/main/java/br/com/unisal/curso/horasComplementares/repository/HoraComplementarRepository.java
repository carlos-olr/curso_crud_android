package br.com.unisal.curso.horasComplementares.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unisal.curso.horasComplementares.modelo.HoraComplementar;
import br.com.unisal.curso.horasComplementares.util.SqliteHelper;

/**
 * Created by carlos on 18/10/16.
 */

public class HoraComplementarRepository {

    private static final int VERSION = 1;
    private static final String BD_NAME = "horasComplementares";
    private static final String BD_TABLE = "horaComplementar";
    private SQLiteDatabase db;
    private SqliteHelper helper;

    public HoraComplementarRepository(Context context) {
        helper = new SqliteHelper(context, BD_NAME, VERSION, getCreateSql(), getUpgradeSql());
    }

    private List<String> getCreateSql() {
        List<String> sqls = new ArrayList<>();
        sqls.add("CREATE TABLE 'horaComplementar' ('id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "'nome' TEXT, 'descricao' TEXT, 'dataEvento' TEXT, " +
            "'quantidadeHoras' INTEGER, 'comprovante' BLOB);");
        return sqls;
    }

    private List<String> getUpgradeSql() {
        List<String> sqlu = new ArrayList<>();
        return sqlu;
    }

    public HoraComplementar salvar(HoraComplementar hc) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", hc.getNome());
        cv.put("descricao", hc.getDescricao());
        cv.put("dataEvento", hc.getDataEvento());
        cv.put("comprovante", hc.getComprovante());
        cv.put("quantidadeHoras", hc.getQuantidadehoras());
        if (hc.getId() == null) {
            Long id = db.insert(BD_TABLE, null, cv);
            hc.setId(id);
        } else {
            String where = BD_TABLE + ".id = ?";
            String[] args = new String[]{hc.getId().toString()};
            cv.put("id", hc.getId());
            db.update(BD_TABLE, cv, where, args);
        }
        db.close();
        return hc;
    }

    public int deletar(Long id) {
        db = helper.getWritableDatabase();
        String where = BD_TABLE + ".id = ?";
        String[] args = new String[]{id.toString()};
        int qtdeReg = db.delete(BD_TABLE, where, args);
        db.close();
        return qtdeReg;
    }

    public List<HoraComplementar> listar() {
        List<HoraComplementar> horaComplementares = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from horaComplementar order by dataEvento desc",
            new String[0]);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                horaComplementares.add(this.build(cursor));
            } while (cursor.moveToNext());
        }
        return horaComplementares;
    }

    public HoraComplementar buscarPorId(String id) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from horaComplementar where id = ?",
            new String[] {id});
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            return this.build(cursor);
        }
        return null;
    }

    private HoraComplementar build(Cursor cursor) {
        HoraComplementar hc = new HoraComplementar();
        hc.setId(cursor.getLong(cursor.getColumnIndex("id")));
        hc.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        hc.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        hc.setDataEvento(cursor.getLong(cursor.getColumnIndex("dataEvento")));
        hc.setComprovante(cursor.getBlob(cursor.getColumnIndex("comprovante")));
        hc.setQuantidadehoras(cursor.getInt(cursor.getColumnIndex("quantidadeHoras")));
        return hc;
    }

}
