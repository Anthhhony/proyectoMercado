package com.example.originmercadotecnia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_notas extends BaseAdapter {

    private Context contexto;
    private ArrayList<Nota_ind> listItems;

    public Adaptador_notas(Context contexto, ArrayList<Nota_ind> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(contexto).inflate(R.layout.item_listview_note,null);
        TextView tvNombre2 = (TextView) view.findViewById(R.id.tvNombre2);
        Nota_ind n = (Nota_ind) getItem(i);
        tvNombre2.setText(n.getNombre());
        return view;
    }
}
