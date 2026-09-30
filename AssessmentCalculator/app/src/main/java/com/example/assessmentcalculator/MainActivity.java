package com.example.assessmentcalculator;

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

    private Button btncal,btnavrg;
    private EditText edt1,edt2,edt3;
    private TextView txtR;

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
        edt2=findViewById(R.id.edtnum2);
        edt3=findViewById(R.id.edtNum3);
        btncal=findViewById(R.id.btnCal);
        btnavrg=findViewById(R.id.btnAvrg);
        txtR=findViewById(R.id.txtResult);

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('%');
            }
        });

        btnavrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });
    }

    private void calculate(char c) {
        String num1Str=edt1.getText().toString().trim();
        String num2Str=edt2.getText().toString().trim();
        String num3Str=edt3.getText().toString().trim();

        if (num1Str.isEmpty()||num2Str.isEmpty()||num3Str.isEmpty()){
            txtR.setText("Please provide appropriate marks!");
            return;
        }
        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double num3 = Double.parseDouble(num3Str);
            double result = 0;

            switch (c) {
                case '%':
                    result = (num1 *0.10) + (num2 *0.40) + (num3 *0.50);
                    txtR.setText("Final Average: " + String.format("%.2f", result));
                    break;

                case '+':
                    result = (num1 *0.10) + (num2 *0.40);
                    txtR.setText("CAS Average: " + String.format("%.2f", result));
                    break;
            }

        } catch (NumberFormatException e) {
            txtR.setText("Please provide marks");
        }

    }
}