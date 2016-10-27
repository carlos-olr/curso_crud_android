package br.com.unisal.curso.horasComplementares.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.unisal.curso.horasComplementares.R;
import br.com.unisal.curso.horasComplementares.adapter.HoraComplementarAdapter;
import br.com.unisal.curso.horasComplementares.model.HoraComplementar;
import br.com.unisal.curso.horasComplementares.repository.HoraComplementarRepository;

public class ListaActivity extends AppCompatActivity {

    private HoraComplementarRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.repository = new HoraComplementarRepository();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        HoraComplementarAdapter adapter = new HoraComplementarAdapter(this,
            R.layout.item_lista, this.repository.listar());
        ListView listView = (ListView) this.findViewById(R.id.listaHorasComplementares);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoraComplementar hc = (HoraComplementar) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
                intent.putExtra("id", hc.getId().toString());
                ListaActivity.this.startActivity(intent);
            }
        };

        listView.setOnItemClickListener(listener);
    }

}
