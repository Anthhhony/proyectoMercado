package com.example.originmercadotecnia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context contexto;
    private ArrayList<Producto> listItems;

    public Adaptador(Context contexto, ArrayList<Producto> listItems) {
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
        view = LayoutInflater.from(contexto).inflate(R.layout.item_listview,null);
        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView tvPrecio = (TextView) view.findViewById(R.id.tvPrecio);
        Producto p = (Producto) getItem(i);
        tvNombre.setText(p.getNombre());
        tvPrecio.setText("$"+p.getPrecio());
        return view;
    }
}
