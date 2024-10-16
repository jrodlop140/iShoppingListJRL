package com.example.ishoppinglistjrl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglistjrl.R;
import com.example.ishoppinglistjrl.database.Database;
import com.example.ishoppinglistjrl.models.Product;

public class Edit extends AppCompatActivity {

    private EditText etName;
    private EditText etNote;
    private Switch swPending;
    private Switch swLactosa;
    private Switch swGluten;
    private Button btnSaveEdit;
    private Button btnCancelEdit;
    private int productId;  // Almacenamos el ID del producto que estamos editando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes de la interfaz de usuario
        etName = findViewById(R.id.etName);
        etNote = findViewById(R.id.etNote);
        swPending = findViewById(R.id.switchPendingEdit);
        swLactosa = findViewById(R.id.swLactosaEdit);
        swGluten = findViewById(R.id.swGlutenEdit);
        btnSaveEdit = findViewById(R.id.btnSaveEdit);
        btnCancelEdit = findViewById(R.id.btnCancelEdit);

        // Obtenemos el intent
        Intent intentDetailProduct = getIntent();
        productId = intentDetailProduct.getIntExtra("productId", -1);
        Product currentProduct = Database.getProductById(productId);

        // Verifica que el producto no sea null antes de asignar valores
        if (currentProduct != null) {
            // Asignamos los valores a los campos correspondientes
            etName.setText(currentProduct.getName());
            etNote.setText(currentProduct.getNote());
            swPending.setChecked(currentProduct.isState());
            swLactosa.setChecked(currentProduct.isLactosa());
            swGluten.setChecked(currentProduct.isGluten());
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
        }

        // Asignamos las acciones de los botones
        // Volvemos a la pantalla principal
        btnCancelEdit.setOnClickListener(view -> {
            Intent intent = new Intent(Edit.this, MainActivity.class);
            startActivity(intent);
        });

        // Guardar cambios
        btnSaveEdit.setOnClickListener(view -> {
            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(Edit.this, "Name can not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentProduct != null) {
                currentProduct.setName(etName.getText().toString());
                currentProduct.setNote(etNote.getText().toString());
                currentProduct.setState(swPending.isChecked());
                currentProduct.setLactosa(swLactosa.isChecked());
                currentProduct.setGluten(swGluten.isChecked());
                Log.d("Edit", "Producto actualizado: " + currentProduct.toString());
                Toast.makeText(Edit.this, "Product edited correctly", Toast.LENGTH_SHORT).show();
                // Regresar a la pantalla principal
                Intent saveIntent = new Intent(Edit.this, MainActivity.class);
                startActivity(saveIntent);
            } else {
                Log.d("Edit", "Producto no encontrado con ID: " + productId);
                Toast.makeText(Edit.this, "Product not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
