package br.com.unisal.curso.horasComplementares.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.unisal.curso.horasComplementares.R;
import br.com.unisal.curso.horasComplementares.model.HoraComplementar;

/**
 * Created by carlos on 18/10/16.
 */
public class HoraComplementarAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private List<HoraComplementar> data = new ArrayList<>();

    public HoraComplementarAdapter(Context context, int layoutResourceId,
    List<HoraComplementar> data) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.nome = (TextView) row.findViewById(R.id.itemListaNome);
            holder.data = (TextView) row.findViewById(R.id.itemListaDataEvento);
            holder.qtdHoras = (TextView) row.findViewById(R.id.itemListaQtdHoras);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        HoraComplementar item = data.get(position);
        holder.nome.setText(item.getNome());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        holder.data.setText(sdf.format(new Date(item.getDataEvento())));
        holder.qtdHoras.setText(item.getQuantidadeHoras().toString());
        return row;
    }

    private class ViewHolder {
        TextView nome;
        TextView data;
        TextView qtdHoras;
    }
}
