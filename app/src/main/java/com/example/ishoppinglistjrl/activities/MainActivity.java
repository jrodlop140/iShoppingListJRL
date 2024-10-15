package com.example.ishoppinglistjrl.activities;

import static com.example.ishoppinglistjrl.database.Database.initializeList;
import static com.example.ishoppinglistjrl.database.Database.productList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.adapters.ProductAdapter;
import com.example.ishoppinglistjrl.models.Product;

public class MainActivity extends AppCompatActivity {

    private ListView lvProducts;
    private Button btnAdd;
    private Button btnPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Obtenemos los componentes de la vista
        lvProducts = findViewById(R.id.lvProducts);
        btnAdd = findViewById(R.id.btnAdd);
        btnPending = findViewById(R.id.btnPending);

        //Inicializamos la lista
        initializeList();

        //Creamos el adapter
        ProductAdapter adapter = new ProductAdapter(MainActivity.this, 0, productList);

        //Asignamos el adaptador al listView de prodcutos
        lvProducts.setAdapter(adapter);

        //Listener para cuando cliquemos un elemento de la lista de prodcutos
        lvProducts.setOnItemClickListener((adapterView, view, i, l) -> {
            Product p = productList.get(i);

            //Creamos el intent
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);

            //Añadimos el producto al intent
            detailIntent.putExtra("product", p);

            //Iniciamos el intent
            startActivity(detailIntent);
        });

        //Listener cuando cliquemos el botón de pending
        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pending = new Intent(MainActivity.this, AddPending.class);
                startActivity(pending);
            }
        });

        //Listener cuando cliquemos el boton de add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, AddSystem.class);
                startActivity(add);
            }
        });
    }


}