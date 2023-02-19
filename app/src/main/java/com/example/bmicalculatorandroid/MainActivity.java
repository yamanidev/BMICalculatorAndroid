package com.example.bmicalculatorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView, BMIResultTextView;
    EditText weightEditText, heightEditText;
    Button calculateBMIButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateBMIButton = findViewById(R.id.calculateBMIButton);
        resultTextView = findViewById(R.id.resultTextView);
        BMIResultTextView = findViewById(R.id.BMIResultTextView);

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
        String resultText = String.valueOf(calculateBMI(weightValue, heightValue));

        if (resultTextView.getVisibility() == View.INVISIBLE) resultTextView.setVisibility(View.VISIBLE);

        BMIResultTextView.setText(resultText.substring(0, resultText.indexOf(".") + 2));
    }


}