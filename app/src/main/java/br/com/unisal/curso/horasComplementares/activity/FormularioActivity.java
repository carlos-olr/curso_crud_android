package br.com.unisal.curso.horasComplementares.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import br.com.unisal.curso.horasComplementares.R;
import br.com.unisal.curso.horasComplementares.modelo.HoraComplementar;
import br.com.unisal.curso.horasComplementares.repository.HoraComplementarRepository;
import br.com.unisal.curso.horasComplementares.util.BitmapUtils;

public class FormularioActivity extends AppCompatActivity {

    private HoraComplementarRepository repository;
    private byte[] bytesImagem = null;
    private Long id;
    private Long dataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.repository = new HoraComplementarRepository(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        String id = getIntent().getStringExtra("id");
        if (id != null) {
            HoraComplementar hc = this.repository.buscarPorId(id);

            EditText editNome = (EditText) this.findViewById(R.id.editNome);
            EditText editDescricao = (EditText) this.findViewById(R.id.editDescricao);
            EditText editQtdHoras = (EditText) this.findViewById(R.id.editQtdHoras);

            editNome.setText(hc.getNome());
            editDescricao.setText(hc.getDescricao());
            editQtdHoras.setText(hc.getQuantidadehoras().toString());

            this.id = hc.getId();
            this.bytesImagem = hc.getComprovante();
            this.dataEvento = hc.getDataEvento();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(this.dataEvento);
            int ano = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            String data = dia + "/" + mes + "/" + ano;
            TextView txtDataOut = (TextView) FormularioActivity.this.findViewById(R.id.txtDataOut);
            txtDataOut.setText(data);

            this.findViewById(R.id.btnDeletar).setVisibility(View.VISIBLE);
        }

        ImageView imgCheck = (ImageView) FormularioActivity.this.findViewById(R.id.imgCheck);
        imgCheck.setVisibility(this.bytesImagem != null ? View.VISIBLE : View.INVISIBLE);
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
        hc.setQuantidadehoras(new Integer(editQtdHoras.getText().toString()));

        this.repository.salvar(hc);
        startActivity(new Intent(this, ListaActivity.class));
    }

    public void remover(View view) {
        this.repository.deletar(Long.valueOf(this.id));
        startActivity(new Intent(this, ListaActivity.class));
    }

    public void exibirImagem(View view) {
        Intent intent = new Intent(this, ImagemDetalhesActivity.class);
        intent.putExtra("imagem", this.bytesImagem);
        startActivity(intent);
    }

    public void abrirModalData(View view) {
        Calendar mcurrentDate = Calendar.getInstance();
        int ano = mcurrentDate.get(Calendar.YEAR);
        int mes = mcurrentDate.get(Calendar.MONTH);
        int dia = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                String data = dia + "/" + mes + "/" + ano;
                TextView txtDataOut = (TextView) FormularioActivity.this.findViewById(R.id.txtDataOut);
                txtDataOut.setText(data);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, ano);
                calendar.set(Calendar.MONTH, mes);
                calendar.set(Calendar.DAY_OF_MONTH, dia);
                FormularioActivity.this.dataEvento = calendar.getTimeInMillis();
            }
        }, ano, mes, dia);
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

        this.bytesImagem = BitmapUtils.getBytes((Bitmap) data.getExtras().get("data"));
        if (this.bytesImagem != null) {
            this.findViewById(R.id.imgCheck).setVisibility(View.VISIBLE);
        }
    }
}
