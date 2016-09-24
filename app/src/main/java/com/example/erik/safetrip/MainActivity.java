package com.example.erik.safetrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEnviaRegistro (View view){
       /* enviar datos al data base */

        Intent intent = new Intent(this, ContactSelect.class);
        startActivity(intent);
    }


    public void onYaCuenta (View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
