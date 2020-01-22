package com.example.pizzapartymvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the widgets to class variables
        mNumAttendEditText = findViewById(R.id.attendEditText);
        mNumPizzasTextView = findViewById(R.id.answerTextView);
        mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);
    }

    public void calculateClick(View view) {

        // Get how many are attending the party
        int numAttend;
        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        PizzaCalc.HungerLevel hungerLevel = PizzaCalc.HungerLevel.RAVENOUS;
        if (checkedId == R.id.lightRadioButton) {
            hungerLevel = PizzaCalc.HungerLevel.LIGHT;
        }
        else if (checkedId == R.id.mediumRadioButton) {
            hungerLevel = PizzaCalc.HungerLevel.MEDIUM;
        }

        // Show the number of pizzas needed
        PizzaCalc  calc = new PizzaCalc (numAttend, hungerLevel);
        int totalPizzas = calc.totalPizzas();
        mNumPizzasTextView.setText(" Total pizzas: " + totalPizzas);
    }
}
