package com.example.b1w1calculatorg1mba;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btna,btns,btnm,btnd;
    private EditText edt1,edt2;
    private TextView txtR;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt1=findViewById(R.id.edtNum1);
        edt2=findViewById(R.id.edtNum2);
        btna=findViewById(R.id.btnAdd);
        btns=findViewById(R.id.btnSubtract);
        btnm=findViewById(R.id.btnMultiply);
        btnd=findViewById(R.id.btnDivide);
        txtR=findViewById(R.id.txtResult);

        btna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btns.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               calculate('*');
           }
        });

        btnd.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               calculate('/');
           }
        });
    }

    private void calculate(char c) {

        String num1str=edt1.getText().toString().trim();
        String num2str=edt2.getText().toString().trim();

        if(num1str.isEmpty()||num2str.isEmpty()){
            txtR.setText("Please enter a number");
            return;
        }

        try {
            double num1=Double.parseDouble(num1str);
            double num2=Double.parseDouble(num2str);
            double result = 0;

            switch (c){
                case '+':
                    result=num1 + num2;
                    break;
                case '-':
                    result=num1 - num2;
                    break;
                case '*':
                    result=num1 * num2;
                    break;
                case '/':
                    if (num2==0){
                        txtR.setText("Division by 0 is not allowed");
                        return;
                    }
                    result=num1/num2;
                    break;
            }
            txtR.setText(String.format("%.2f", result));

        }catch(NumberFormatException e) {
            txtR.setText("Please enter a number");
        }
    }
}