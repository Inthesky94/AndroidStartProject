package ru.synergy.androidstartproject;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Calculator extends AppCompatActivity {
    private static final String LogcatTag = "CALCULATOR_ACTIVITY";
    private static final String LifecycleTag = "LIFECYCLE";

    public Calculator() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LifecycleTag, "i'm onStart, and i'm started");
    }

    @Override
    protected void onStop() {
        Log.d(LifecycleTag,"i'm onStop, and i'm started");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LifecycleTag, "I'm onDestroy, and i'm started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LifecycleTag, "I'm onPause, and i'm started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LifecycleTag, "i'm onResume, and i'm started");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LifecycleTag, "I'm onCreate, and i'm started");
        setContentView(R.layout.activity_calculator);

        final Button calculate = (Button) findViewById(R.id.calc);

//        //Context training
//        TextView textView = new TextView(this);
//        ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext());
//
//        //Доступ из класса Activity -- наследник Context
//        getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        //Shared prefs доступ с использованием контекста приложения
//        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PREFS", MODE_PRIVATE);
//


        //// Intent - посылка


        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(LogcatTag, "Button have been pushed");
                try{
                    calculateAnswer();
                }
                catch (Exception e){

                    // прерывание
//                    e.printStackTrace();
//                    Toast.makeText(Calculator.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                    finish();
                }

                Intent i = new Intent(Calculator.this, MainActivity.class); // Написать письмо
                //startActivity(i); // отправить письмо

            }
        });
    }
    private void calculateAnswer() throws ArithmeticException, IOException {
        EditText numOne = (EditText) findViewById(R.id.editTextNumberDecimal);
        EditText numTwo = (EditText) findViewById(R.id.editTextNumberDecimal2);

        RadioButton add = (RadioButton) findViewById(R.id.add);
        RadioButton sub = (RadioButton) findViewById(R.id.subtract);
        RadioButton multiple = (RadioButton) findViewById(R.id.multiple);
        RadioButton divide = (RadioButton) findViewById(R.id.divide);

        numOne.setText("0");
        numTwo.setText("0");
        add.setChecked(true);

        TextView answer = (TextView) findViewById(R.id.result);

        Log.d(LogcatTag, "All views have been founded");



        float numtwo = 0;
        float numone = 0;

        String num1 = numOne.getText().toString();
        String num2 = numTwo.getText().toString();
        if(!num1.equals("") && num1 != null) {
            numone = Integer.parseInt(numOne.getText().toString());}

        if(!num2.equals("") && num2 != null) {
            numtwo = Integer.parseInt(numTwo.getText().toString());
        }

        Log.d(LogcatTag, "Successfully grabbed data from input fields");
        Log.d(LogcatTag, "numone is: " + numone + " ; "+" numtwo is: " + numtwo);


        float solution = 0;

        if(add.isChecked()){
            Log.d(LogcatTag, "Operation is add");
            solution = numone + numtwo;
        }

        if(sub.isChecked()){
            Log.d(LogcatTag, "Operation is sub");
            solution = numone - numtwo;
        }
        if(multiple.isChecked()){
            Log.d(LogcatTag, "Operation is multiple");
            solution = numone * numtwo;
        }
        if(divide.isChecked()){
            Log.d(LogcatTag, "Operation is divide");
            if(numtwo == 0 ){
                Toast.makeText(this,"Number two Cannot be zero",Toast.LENGTH_SHORT).show();
                return;
            }
            solution = numone / numtwo;
        }

        Log.d(LogcatTag, "The result of operation is: " + solution);

        // Log.wtf() // what a terrible failure == error

        answer.setText("The answer is "+ solution);


        switch( (int) Math.random()*2){
            case 0 : throw new ArithmeticException("I am generated arithmetical exception");
            case 1 : throw new IOException("i am generated ioexception");
        }
    }
}