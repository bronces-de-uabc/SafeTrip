package com.example.erik.safetrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView nombreRegistro;
    TextView correoRegistro;
    TextView passwordRegistro;
    Button enviarRegistro;
    TextView yaCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarRegistro = (Button)findViewById(R.id.botonEnviar);
        yaCuenta =(TextView)findViewById(R.id.yaCuenta);
    }

    public void onEnviaRegistro (View view){
        String[] contacto;

        nombreRegistro = (TextView)findViewById(R.id.nombreUsuario);
        correoRegistro = (TextView)findViewById(R.id.correoUsuario);
        passwordRegistro = (TextView)findViewById(R.id.password0);

        Intent intent = new Intent(this, ContactSelect.class);
        startActivity(intent);

        CacheDatabaseHelper databaseCache = new CacheDatabaseHelper(this);
        /*databaseCache.insert(nombreRegistro,correoRegistro,passwordRegistro,contacto[0], contacto[1], contacto[2]);*/
    }


    public void onYaCuenta (View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}