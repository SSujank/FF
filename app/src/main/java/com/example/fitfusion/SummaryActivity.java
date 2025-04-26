package com.example.fitfusion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView tvSummary;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);
        btnRestart = findViewById(R.id.btnRestart);

        Bundle data = getIntent().getExtras();

        String name = data.getString("name");
        String age = data.getString("age");
        double weight = Double.parseDouble(data.getString("weight"));
        double height = Double.parseDouble(data.getString("height")) / 100;

        double bmi = weight / (height * height);

        double totalDietCalories = data.getDouble("dietCalories");
        double totalExerciseCalories = data.getDouble("exerciseCalories");

        double netCalories = totalDietCalories - totalExerciseCalories;

        // Fitness Tip based on BMI
        String tip;
        if (bmi < 18.5) {
            tip = "You are underweight. Focus on nutritious foods and strength training!";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            tip = "Great! You have a healthy weight. Maintain a balanced diet and regular exercise.";
        } else if (bmi >= 25 && bmi <= 29.9) {
            tip = "Overweight. Try cardio exercises and a calorie deficit meal plan!";
        } else {
            tip = "Obesity risk. Consult a fitness expert and focus on cardio and healthy meals.";
        }

        String summary = "👤 Name: " + name +
                "\n🎂 Age: " + age +
                "\n⚖️ Weight: " + weight + " kg" +
                "\n📏 Height: " + (height * 100) + " cm" +
                "\n\n🍴 Total Diet Calories: " + String.format("%.1f", totalDietCalories) +
                "\n🏃 Total Exercise Calories Burned: " + String.format("%.1f", totalExerciseCalories) +
                "\n🔥 Net Calories (Intake - Burned): " + String.format("%.1f", netCalories) +
                "\n\n📊 BMI: " + String.format("%.1f", bmi) +
                "\n💡 Fitness Tip: " + tip;

        tvSummary.setText(summary);

        btnRestart.setOnClickListener(v -> {
            finishAffinity();
            startActivity(new Intent(SummaryActivity.this, UserProfileActivity.class));
        });
    }
}
