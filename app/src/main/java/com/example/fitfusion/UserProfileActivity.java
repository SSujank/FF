package com.example.fitfusion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    EditText editName, editAge, editWeight, editHeight;
    CheckBox checkboxAgree;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        checkboxAgree = findViewById(R.id.checkboxAgree);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            if (!checkboxAgree.isChecked()) {
                Toast.makeText(this, "Please agree to input your details", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, DietActivity.class);
                intent.putExtra("name", editName.getText().toString());
                intent.putExtra("age", editAge.getText().toString());
                intent.putExtra("weight", editWeight.getText().toString());
                intent.putExtra("height", editHeight.getText().toString());
                startActivity(intent);
            }
        });
    }
}
