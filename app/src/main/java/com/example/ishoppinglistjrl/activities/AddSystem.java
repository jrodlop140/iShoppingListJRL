package com.example.ishoppinglistjrl.activities;

import android.content.Intent;
import android.os.Bundle;
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

public class AddSystem extends AppCompatActivity {

    private EditText etName;
    private EditText etNote;
    private Switch swState;
    private Switch swLactosa;
    private Switch swGluten;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_system);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recuperar componentes de la vista
        etName = findViewById(R.id.etNameAdd);
        etNote = findViewById(R.id.etNoteAdd);
        swState = findViewById(R.id.switchPendienteAdd);
        swLactosa = findViewById(R.id.switchLactosaAdd);
        swGluten = findViewById(R.id.switchGlutenAdd);
        btnSave = findViewById(R.id.btnSaveEdit);
        btnCancel = findViewById(R.id.btnCancelEdit);

        // Listener para guardar el nuevo producto
        btnSave.setOnClickListener(view -> {
            // Verificar si los campos están vacíos
            if (etName.getText().toString().isEmpty() || etNote.getText().toString().isEmpty()) {
                Toast.makeText(AddSystem.this, "Empty fields are not permitted", Toast.LENGTH_SHORT).show();
            } else {
                String name = etName.getText().toString();
                String note = etNote.getText().toString();
                boolean state = swState.isChecked();
                boolean lactosa = swLactosa.isChecked();
                boolean gluten = swGluten.isChecked();

                // Verificar si el producto ya está en la lista
                boolean productExists = false;
                for (Product product : Database.productList) {
                    if (product.getName().equalsIgnoreCase(name)) {
                        productExists = true;
                        break;
                    }
                }

                if (productExists) {
                    // Mostrar mensaje si el producto ya existe
                    Toast.makeText(AddSystem.this, "Product already in the list, yo must add a new product", Toast.LENGTH_SHORT).show();
                } else {
                    // Asignar un nuevo ID
                    int newId = Database.productList.size() + 1;
                    Product newProduct = new Product(newId, name, note, state, lactosa, gluten);

                    // Añadir el producto a la lista
                    Database.productList.add(newProduct);

                    // Mostrar mensaje de éxito
                    Toast.makeText(AddSystem.this, "Product added correctly", Toast.LENGTH_SHORT).show();

                    // Volver a la pantalla principal
                    Intent addProductIntent = new Intent(AddSystem.this, MainActivity.class);
                    startActivity(addProductIntent);
                }
            }
        });


        // Listener para cancelar la operación
        btnCancel.setOnClickListener(view -> {
            Intent cancelIntent = new Intent(AddSystem.this, MainActivity.class);
            startActivity(cancelIntent);
        });

    }
}