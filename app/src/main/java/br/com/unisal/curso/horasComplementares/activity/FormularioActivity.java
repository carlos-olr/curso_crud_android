package br.com.unisal.curso.horasComplementares.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import br.com.unisal.curso.horasComplementares.R;
import br.com.unisal.curso.horasComplementares.model.HoraComplementar;
import br.com.unisal.curso.horasComplementares.repository.HoraComplementarRepository;
import br.com.unisal.curso.horasComplementares.util.BitmapUtils;

public class FormularioActivity extends AppCompatActivity {

    private HoraComplementarRepository repository;
    private byte[] bytesImagem = null;
    private Long id;
    private Long dataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.repository = new HoraComplementarRepository();
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_formulario);

        if (this.getIntent().getExtras() != null) {
            HoraComplementar hc = (HoraComplementar) this.getIntent().getExtras().get("hc");
            if (hc != null) {
                this.id = hc.getId();

                EditText editNome = (EditText) this.findViewById(R.id.editNome);
                EditText editDescricao = (EditText) this.findViewById(R.id.editDescricao);
                EditText editQtdHoras = (EditText) this.findViewById(R.id.editQtdHoras);

                editNome.setText(hc.getNome());
                editDescricao.setText(hc.getDescricao());
                editQtdHoras.setText(hc.getQuantidadeHoras().toString());

                this.id = hc.getId();
                this.bytesImagem = hc.getComprovante();
                this.dataEvento = hc.getDataEvento();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(this.dataEvento);
                int ano = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                String data = dia + "/" + mes + "/" + ano;
                TextView txtDataOut = (TextView) this.findViewById(R.id.txtDataOut);
                txtDataOut.setText(data);

                this.findViewById(R.id.btnDeletar).setVisibility(View.VISIBLE);
                int imgCheckVisivel = this.bytesImagem != null ? View.VISIBLE : View.INVISIBLE;
                this.findViewById(R.id.imgCheck).setVisibility(imgCheckVisivel);
            }
        }
    }

    public void salvar(View view) {
        HoraComplementar hc = new HoraComplementar();
        hc.setId(this.id);
        hc.setComprovante(this.bytesImagem);
        hc.setDataEvento(this.dataEvento);

        EditText editNome = (EditText) this.findViewById(R.id.editNome);
        EditText editDescricao = (EditText) this.findViewById(R.id.editDescricao);
        EditText editQtdHoras = (EditText) this.findViewById(R.id.editQtdHoras);

        hc.setNome(editNome.getText().toString());
        hc.setDescricao(editDescricao.getText().toString());
        hc.setQuantidadeHoras(new Integer(editQtdHoras.getText().toString()));

        this.repository.salvar(hc);
        this.startActivity(new Intent(this, ListaActivity.class));
    }

    public void deletar(View view) {
        this.repository.deletar(this.id);
        this.startActivity(new Intent(this, ListaActivity.class));
    }

    public void abrirModalData(View view) {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH);
        int dia = hoje.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                String data = dia + "/" + mes + "/" + ano;
                TextView dataOut = (TextView) FormularioActivity.this.findViewById(R.id.txtDataOut);
                dataOut.setText(data);
                Calendar dataDefinida = Calendar.getInstance();
                dataDefinida.set(Calendar.YEAR, ano);
                dataDefinida.set(Calendar.MONTH, mes);
                dataDefinida.set(Calendar.DAY_OF_MONTH, dia);
                FormularioActivity.this.dataEvento = dataDefinida.getTimeInMillis();
            }
        };

        DatePickerDialog datePicker = new DatePickerDialog(this, listener, ano, mes, dia);
        datePicker.setTitle("Selecione a data:");
        datePicker.show();
    }

    public void abrirCamera(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getExtras() != null && data.getExtras().get("data") != null) {
            this.bytesImagem = BitmapUtils.getBytes((Bitmap) data.getExtras().get("data"));
            this.findViewById(R.id.imgCheck).setVisibility(View.VISIBLE);
        }
    }

    public void exibirImagem(View view) {
        Intent intent = new Intent(this, ImagemDetalhesActivity.class);
        intent.putExtra("imagem", this.bytesImagem);
        startActivity(intent);
    }
}
