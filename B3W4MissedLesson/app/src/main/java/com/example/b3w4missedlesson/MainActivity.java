package com.example.b3w4missedlesson;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME="user_settings";
    private static final String KEY_USERNAME="username";
    private static final String KEY_AGE="age";
    private EditText edtUserName,edtAge;
    private Button btnSave,btnLoad,btnClear;

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

        edtUserName=findViewById(R.id.edtUsername);
        edtAge=findViewById(R.id.edtAge);
        btnSave=findViewById(R.id.btnSave);
        btnLoad=findViewById(R.id.btnLoad);
        btnClear=findViewById(R.id.btnClear);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPreferences();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPreferences();
            }
        });
    }

    private void clearPreferences() {
        SharedPreferences prefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.clear();
        editor.apply();
        edtUserName.setText("");
        edtAge.setText("");
        showToast(getString(R.string.data_cleared_successfully));
    }

    private void loadPreferences() {
        SharedPreferences prefs =getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(!prefs.contains(KEY_USERNAME)&&!prefs.contains(KEY_AGE)){
            showToast(getString(R.string.no_data_found));
            return;
        }
        String username=prefs.getString(KEY_USERNAME,"");
        int age=prefs.getInt(KEY_AGE,0);
        edtUserName.setText(username);
        edtAge.setText(String.valueOf(age));
        showToast(getString(R.string.data_loaded_successfully));
    }

    private void savePreferences() {
        String username=edtUserName.getText().toString().trim();
        String ageText=edtAge.getText().toString().trim();

        if(username.isEmpty()||ageText.isEmpty()){
            showToast(getString(R.string.please_enter_username_and_age));
            return;
        }
        int age;
        try{
            age=Integer.parseInt(ageText);
            if(age<0||age>150){
                showToast(getString(R.string.please_enter_valid_age));
                return;
            }
            SharedPreferences prefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor= prefs.edit();
            editor.putString(KEY_USERNAME,username);
            editor.putInt(KEY_AGE,age);
            editor.apply();
            showToast(getString(R.string.data_saved_successfully));

        }catch (NumberFormatException e){
            showToast(getString(R.string.please_enter_valid_age));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}