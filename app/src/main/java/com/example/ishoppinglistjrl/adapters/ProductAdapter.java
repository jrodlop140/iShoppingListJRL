package com.example.ishoppinglistjrl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.models.Product;

import java.util.List;


public class ProductAdapter extends ArrayAdapter<Product> {
    //Declaramos como privada la lista de los prodcutos
    private List<Product> products;

    //Llamada al cosntructor del padre (contexto, idResource, lsita de productoss que queremos recorrer
    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product producto = this.products.get(position);

        //Si todavia no se ha creado la vista la creamos
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        //Recuperams los componentes de la vista
        TextView tvIDProduct = convertView.findViewById(R.id.tvIdProduct);
        TextView tvNameProduct = convertView.findViewById(R.id.tvNameProduct);


        //Modificamos los atributos de los componentes
        tvIDProduct.setText(String.valueOf(producto.getId()));
        tvNameProduct.setText(producto.getName());


        //Devolvemos la vista modificada
        return convertView;
    }


}
