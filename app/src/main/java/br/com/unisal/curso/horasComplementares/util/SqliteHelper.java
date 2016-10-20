package br.com.unisal.curso.horasComplementares.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by carlos on 18/10/16.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    private List<String> createSql;
    private List<String> upgradeSql;

    public SqliteHelper(Context context, String nomeBanco, int version,
                        List<String> createSql, List<String> upgradeSql) {
        super(context, nomeBanco, null, version);
        this.createSql = createSql;
        this.upgradeSql = upgradeSql;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String insSql : createSql) {
            db.execSQL(insSql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(String insSql : upgradeSql) {
            db.execSQL(insSql);
        }
    }
}
