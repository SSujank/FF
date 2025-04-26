package com.example.fitfusion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {

    EditText etProtein, etCarbs, etFats;
    Button btnAddMore, btnNext, btnBack;
    ArrayList<Double> proteinList = new ArrayList<>();
    ArrayList<Double> carbsList = new ArrayList<>();
    ArrayList<Double> fatsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        etProtein = findViewById(R.id.etProtein);
        etCarbs = findViewById(R.id.etCarbs);
        etFats = findViewById(R.id.etFats);
        btnAddMore = findViewById(R.id.btnAddMore);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        btnAddMore.setOnClickListener(v -> {
            if (etProtein.getText().toString().isEmpty() ||
                    etCarbs.getText().toString().isEmpty() ||
                    etFats.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            proteinList.add(Double.parseDouble(etProtein.getText().toString()));
            carbsList.add(Double.parseDouble(etCarbs.getText().toString()));
            fatsList.add(Double.parseDouble(etFats.getText().toString()));

            Toast.makeText(this, "Meal added!", Toast.LENGTH_SHORT).show();

            // Clear input fields after adding
            etProtein.setText("");
            etCarbs.setText("");
            etFats.setText("");
        });

        btnNext.setOnClickListener(v -> {
            if (proteinList.isEmpty()) {
                Toast.makeText(this, "Please add at least one meal!", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalCalories = 0;
            for (int i = 0; i < proteinList.size(); i++) {
                totalCalories += (proteinList.get(i) * 4) + (carbsList.get(i) * 4) + (fatsList.get(i) * 9);
            }

            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtras(getIntent());
            intent.putExtra("dietCalories", totalCalories);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
