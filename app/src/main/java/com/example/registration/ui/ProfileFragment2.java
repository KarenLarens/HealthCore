package com.example.registration.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.registration.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileFragment2 extends Fragment {

    DatabaseReference reference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Switch Genre;
    EditText NombreET,ApePatET,ApeMatET,AgeET, UserET,EmailET,PassET;
    Button submit;

    //Datos de sesión activa
    String UID = user.getUid();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile2, container, false);

        Genre = (Switch) view.findViewById(R.id.GenreSwitch);

        NombreET= (EditText) view.findViewById(R.id.NombreET);
        ApePatET= (EditText) view.findViewById(R.id.ApePatET);
        ApeMatET= (EditText) view.findViewById(R.id.ApeMatET);
        AgeET= (EditText) view.findViewById(R.id.AgeET);

        submit = (Button) view.findViewById(R.id.BtnActualizar);


        return view;
    }

    public void MostrarDatos(){
        reference= FirebaseDatabase.getInstance().getReference().child("Perfil");


    }

}
