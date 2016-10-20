package br.com.unisal.curso.horasComplementares.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.unisal.curso.horasComplementares.R;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inical);
    }

    public void irParaFormulario(View view) {
        Intent intent = new Intent(this, FormularioActivity.class);
        startActivity(intent);
    }

    public void irParaLista(View view) {
        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
    }

}
