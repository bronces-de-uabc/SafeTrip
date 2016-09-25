package com.example.erik.safetrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReporteActivity extends AppCompatActivity {
    private Spinner spinnerSiniestro;
    private EditText fecha;
    private EditText hora;
    private EditText comentarios;
    private Button btnEditarHora;
    private Button btnEditarFecha;
    private Button btnMandarReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
    }

    @Override
    public void onStart() {
        super.onStart();

        spinnerSiniestro = (Spinner) findViewById(R.id.spinnerSiniestro);
        fecha = (EditText) findViewById(R.id.etFecha);
        hora = (EditText) findViewById(R.id.etHora);
        comentarios = (EditText) findViewById(R.id.etComentarios);
        btnEditarHora = (Button) findViewById(R.id.btn_edit_fecha);
        btnEditarFecha = (Button) findViewById(R.id.btn_edit_fecha);
        btnMandarReporte = (Button) findViewById(R.id.btnMandarReporte);

        btnEditarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
            }
        });
        btnEditarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            }
        });
        btnMandarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ReporteActivity","Click Mandar Reporte");
                HttpURLConnection connection;
                OutputStreamWriter request = null;
                URL url = null;
                String response = null;
                String siniestro = "Robooo";
                double log = 13;
                double lat = 13;
                int nivel = 13;
                String comment = " Comeentario de los sucecido";
                String parameters = "siniestro ="+siniestro+"&log="+log+"&lat="+lat+"&nivel="+nivel+"&comment="+comment;

                try
                {
                    url = new URL("safetrip.atwebpages.com/add.php");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
//                    connection.setRequestProperty("add", "identity");
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(10000 /* milliseconds */);
                    connection.setConnectTimeout(15000 /* milliseconds */);

                    request = new OutputStreamWriter(connection.getOutputStream());
                    request.write(parameters);
                    request.flush();
                    request.close();
                    String line = "";
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    // Response from server after login process will be stored in response variable.
                    response = sb.toString();
                    // You can perform UI operations here
                    Toast.makeText(ReporteActivity.this,"Message from Server: \n"+ response, Toast.LENGTH_SHORT).show();
                    isr.close();
                    reader.close();

                }
                catch(IOException e)
                {
                    // Error
                    Log.d("ReporteActivity","Error: "+e);
                }
            }
        });

    }
}
