package com.example.ishoppinglistjrl.adapters;

import static com.example.ishoppinglistjrl.database.Database.productList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    //Declaramos como privada la lista de los productos
    private List<Product> products;

    //Llamada al constructor del padre (contexto, idResource, lsita de productos que queremos recorrer)
    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product producto = productList.get(position);
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

        //  Lógica que dependiedo si el producto tiene lactosa o gluten cambia el color de fondo de los diferentes elementos de la lista
        if (producto.isLactosa() && producto.isGluten()) {
            tvIDProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color1));
            tvNameProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color1));
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color1));
        } else if (producto.isLactosa()) {
            tvIDProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color2));
            tvNameProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color2));
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color2));
        } else if (producto.isGluten()) {
            tvIDProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color3));
            tvNameProduct.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color3));
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color3));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = productList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);

            //Llamando al xml por defecto
            //convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        //Recogemos de la vista los componentes
        TextView tvIdProduct = convertView.findViewById(R.id.tvIdProduct);
        TextView tvNameProduct = convertView.findViewById(R.id.tvNameProduct);

        //Modificamos los atributos de los componentes
        tvIdProduct.setText(String.valueOf(product.getId()));
        tvNameProduct.setText(product.getName());

        //Devolvemos la vista ya modificada
        return convertView;
    }
}
