package com.example.registration.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.registration.HelpFirebase;
import com.example.registration.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpFragment extends Fragment {
    private Spinner spinnerPreguntas;
    private ListView ListViewRespuestas;
    EditText duda;
    DatabaseReference reference,reference2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        spinnerPreguntas = (Spinner) view.findViewById(R.id.spinnerPreguntas);
        ListViewRespuestas = (ListView) view.findViewById(R.id.ListViewRespuestas);

        ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Preguntas, android.R.layout.simple_spinner_item);
        spinnerPreguntas.setAdapter(spinAdapter);

        duda = (EditText) view.findViewById(R.id.textInputEditTextPregunta);
        Button submit = (Button) view.findViewById(R.id.buttonRealizaPregunta);
        reference = FirebaseDatabase.getInstance().getReference().child("HelpFirebase");

        spinnerPreguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> lista, View view, int pos, long id) {
                if(lista.getItemAtPosition(pos).equals("¿Cómo usar la app en una situación de emergencia?")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Respuesta1, android.R.layout.simple_list_item_1);
                    ListViewRespuestas.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("¿Para qué funciona el Código QR?")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Respuesta2, android.R.layout.simple_list_item_1);
                    ListViewRespuestas.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("¿Cómo realizo mi expediente médico?")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Respuesta3, android.R.layout.simple_list_item_1);
                    ListViewRespuestas.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("¿Cómo se relaciona la app con mi centro de salud?")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Respuesta4, android.R.layout.simple_list_item_1);
                    ListViewRespuestas.setAdapter(listaAdapter);
                }
                if(lista.getItemAtPosition(pos).equals("¿Puedo consultar información con algún médico?")) {
                    ArrayAdapter listaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.Respuesta5, android.R.layout.simple_list_item_1);
                    ListViewRespuestas.setAdapter(listaAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userxd = user.getUid().trim();
                HelpFirebase hf = new HelpFirebase();
                hf.setPregunta(duda.getText().toString());
                try{
                    reference.child(userxd).setValue(hf);
                    Toast.makeText(getActivity(), "Gracias por evaluar nuestra app!", Toast.LENGTH_SHORT).show();
                }catch (DatabaseException e){
                    Toast.makeText(getActivity(), ("Error: "+e.getMessage()), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
