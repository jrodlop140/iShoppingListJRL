package com.example.ishoppinglistjrl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.models.Product;

public class DetailActivity extends AppCompatActivity {

    //Creamos los elementos de la vista
    private TextView tvName;
    private TextView tvNote;
    private Switch swPending;
    private Button btnEdit;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Obtenemos los componentes de la vista
        tvName = findViewById(R.id.tvName);
        tvNote = findViewById(R.id.tvNote);
        swPending = findViewById(R.id.swPending);
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancelDetail);

        //Obtengo el intent que ha iniciado la activity
        Intent intent = getIntent();

        //Recupero el valor de la clave "product"
        Product product = (Product) intent.getSerializableExtra("product");

        tvName.setText(product.getName());
        tvNote.setText(product.getNote());
        swPending.setChecked(product.isState());

        //Listener que maneja el comportamiento del boton de edit al clicarlo
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos el intent
                Intent detailIntentEdit = new Intent(DetailActivity.this, Edit.class);

                // Añadimos el productId al intent
                detailIntentEdit.putExtra("productId", product.getId());

                //Iniciamos la actividad
                startActivity(detailIntentEdit);
            }
        });

        //Listener que maneja el comportamiento del boton de cancel al clicarlo
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos el intent
                Intent detailIntentCancel = new Intent(DetailActivity.this, MainActivity.class);

                //Iniciamos la actividad
                startActivity(detailIntentCancel);
            }
        });

    }
}