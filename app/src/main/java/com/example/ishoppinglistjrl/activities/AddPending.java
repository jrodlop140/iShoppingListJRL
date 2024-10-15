package com.example.ishoppinglistjrl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.database.Database;
import com.example.ishoppinglistjrl.models.Product;

import java.util.ArrayList;
import java.util.List;

public class AddPending extends AppCompatActivity {

    private Spinner spPending;
    private Button btnsave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acitivity_add_pending);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Recuperamos los elementos de la vista
        spPending = findViewById(R.id.spinnerPending);
        btnsave = findViewById(R.id.btnSaveEditPending);
        btnCancel = findViewById(R.id.btnCancelEditPending);

        // Inicializar la vista
        spPending = findViewById(R.id.spinnerPending);
        btnsave = findViewById(R.id.btnSaveEditPending);
        btnCancel = findViewById(R.id.btnCancelEditPending);

        // Filtrar productos no pendientes
        List<Product> nonPendingProducts = new ArrayList<>();
        for (Product product : Database.productList) {
            if (!product.isState()) {
                nonPendingProducts.add(product);
            }
        }

        // Adaptador para el Spinner
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nonPendingProducts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPending.setAdapter(adapter);

        // Asiganamos el listener click al boton save
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperamos el producto seleccionado en el Spinner y actualizamos como pendiente
                Product selectedProduct = (Product) spPending.getSelectedItem();
                selectedProduct.setState(true); // Marcar como pendiente

                // Volvemos a la pantalla principal
                Intent addPendingSave = new Intent(AddPending.this, MainActivity.class);
                startActivity(addPendingSave);

                Toast.makeText(AddPending.this, "Product added to pending correctly", Toast.LENGTH_SHORT).show();
            }
        });

        // Asiganamos el listener click al boton cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creamos el intent para volver sin cambios
                Intent addPendingCancel = new Intent(AddPending.this, MainActivity.class);
                startActivity(addPendingCancel);
            }
        });

    }
}