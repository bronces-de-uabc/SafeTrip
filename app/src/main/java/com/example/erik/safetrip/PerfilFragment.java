package com.example.erik.safetrip;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
    public void onStart(){
        super.onStart();
        View view = getView();
        Button btn = (Button)view.findViewById(R.id.bt);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent inte = new Intent(getActivity(),ContentsPro.class);
                startActivity(inte);
            }
        });
    }

}
