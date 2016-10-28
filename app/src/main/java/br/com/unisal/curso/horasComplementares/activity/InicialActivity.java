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
        setContentView(R.layout.activity_inicial);

        View.OnClickListener onClick = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicialActivity.this, FormularioActivity.class);
                InicialActivity.this.startActivity(intent);
            }

        };
        this.findViewById(R.id.imgVwForm).setOnClickListener(onClick);

    }

    public void irParaLista(View view) {
        Intent intent = new Intent(this, ListaActivity.class);
        this.startActivity(intent);
    }

}
