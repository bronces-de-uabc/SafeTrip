package com.example.erik.safetrip;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionFragment extends Fragment {
    private Spinner spinnerSiniestro;
    private EditText fecha;
    private EditText hora;
    private EditText comentarios;
    private Button btnEditarHora;
    private Button btnEditarFecha;
    private Button btnMandarReporte;

    public InformacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_informacion, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        spinnerSiniestro = (Spinner) view.findViewById(R.id.spinnerSiniestro);
        fecha = (EditText) view.findViewById(R.id.etFecha);
        hora = (EditText) view.findViewById(R.id.etHora);
        comentarios = (EditText) view.findViewById(R.id.etComentarios);
        btnEditarHora = (Button) view.findViewById(R.id.btn_edit_fecha);
        btnEditarFecha = (Button) view.findViewById(R.id.btn_edit_fecha);
        btnMandarReporte = (Button) view.findViewById(R.id.btnMandarReporte);

        btnEditarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
