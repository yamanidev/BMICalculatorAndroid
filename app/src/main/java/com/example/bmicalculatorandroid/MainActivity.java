package com.example.bmicalculatorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView, BMIResultTextView, descriptionTextView;
    EditText weightEditText, heightEditText;
    Button calculateBMIButton;
    ImageView BMICategoryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateBMIButton = findViewById(R.id.calculateBMIButton);
        resultTextView = findViewById(R.id.resultTextView);
        BMIResultTextView = findViewById(R.id.BMIResultTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        BMICategoryImageView = findViewById(R.id.BMICategoryImageView);

    }

    private double calculateBMI(double weight, double height) {
        return weight / Math.pow(height, 2);
    }

    public void onCalculateBMI(View view) {
//      Hides keyboard
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        double weightValue = Double.parseDouble(weightEditText.getText().toString());
//      In meters
        double heightValue = Double.parseDouble(heightEditText.getText().toString()) / 100;
        double resultValue = calculateBMI(weightValue, heightValue);
        String resultText = String.valueOf(resultValue);

        if (resultTextView.getVisibility() == View.INVISIBLE) resultTextView.setVisibility(View.VISIBLE);

        if (resultValue < 18.5) {
            descriptionTextView.setText("Underweight");
            BMICategoryImageView.setImageResource(R.drawable.underweight);
        } else if (resultValue >= 18.5 && resultValue < 24.9) {
            descriptionTextView.setText("Normal");
            BMICategoryImageView.setImageResource(R.drawable.normal);
        } else if (resultValue >= 25 && resultValue < 29.9) {
            descriptionTextView.setText("Overweight");
            BMICategoryImageView.setImageResource(R.drawable.overweight);
        } else if (resultValue >= 30 && resultValue < 34.9) {
            descriptionTextView.setText("Obese");
            BMICategoryImageView.setImageResource(R.drawable.obese);
        } else if (resultValue >= 35) {
            descriptionTextView.setText("Extremely Obese");
            BMICategoryImageView.setImageResource(R.drawable.extremely_obese);
        }

        BMIResultTextView.setText(resultText.substring(0, resultText.indexOf(".") + 2));

    }


}