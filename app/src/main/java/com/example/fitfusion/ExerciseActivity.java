package com.example.fitfusion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    Spinner spinnerExercise;
    EditText etMinutes;
    Button btnAddMore, btnNext, btnBack;

    String[] exercises = {"Running", "Cycling", "Swimming", "Yoga", "Pushups"};
    double[] metValues = {9.8, 7.5, 8.0, 3.0, 6.0}; // MET values
    ArrayList<Double> exerciseCalories = new ArrayList<>();

    double userWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        spinnerExercise = findViewById(R.id.spinnerExercise);
        etMinutes = findViewById(R.id.etMinutes);
        btnAddMore = findViewById(R.id.btnAddMore);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        userWeight = Double.parseDouble(getIntent().getStringExtra("weight"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exercises);
        spinnerExercise.setAdapter(adapter);

        btnAddMore.setOnClickListener(v -> {
            if (etMinutes.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter minutes", Toast.LENGTH_SHORT).show();
                return;
            }

            int position = spinnerExercise.getSelectedItemPosition();
            double met = metValues[position];
            double minutes = Double.parseDouble(etMinutes.getText().toString());
            double caloriesBurned = (met * 3.5 * userWeight / 200) * minutes;

            exerciseCalories.add(caloriesBurned);

            Toast.makeText(this, "Exercise added!", Toast.LENGTH_SHORT).show();

            etMinutes.setText("");
        });

        btnNext.setOnClickListener(v -> {
            if (exerciseCalories.isEmpty()) {
                Toast.makeText(this, "Add at least one exercise!", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalExerciseCalories = 0;
            for (double cal : exerciseCalories) {
                totalExerciseCalories += cal;
            }

            Intent intent = new Intent(this, SummaryActivity.class);
            intent.putExtras(getIntent());
            intent.putExtra("exerciseCalories", totalExerciseCalories);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
