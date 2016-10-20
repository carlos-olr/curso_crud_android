package br.com.unisal.curso.horasComplementares.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.unisal.curso.horasComplementares.R;
import br.com.unisal.curso.horasComplementares.util.BitmapUtils;

public class ImagemDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_detalhes);

        byte[] imagemBytes = (byte[]) getIntent().getExtras().get("imagem");
        Bitmap bitmap = BitmapUtils.getImage(imagemBytes);

        ImageView imageView = (ImageView) findViewById(R.id.imagem);
        imageView.setImageBitmap(bitmap);
    }
}
