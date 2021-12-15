package com.example.registration.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.registration.MenuActivity;
import com.example.registration.R;
import com.example.registration.RateFirebase;
import com.example.registration.loginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RateFragment extends Fragment {
    EditText mensaje;
    Float estrellita;
    RatingBar rBar;
    DatabaseReference reference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        rBar = (RatingBar) view.findViewById(R.id.ratingBar);
        mensaje = (EditText) view.findViewById(R.id.textInputEditText);
        reference = FirebaseDatabase.getInstance().getReference().child("RateFirebase");


        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                estrellita=v;
            }
        });

        Button submit = (Button) view.findViewById(R.id.btnEnviar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xd= user.getUid().trim();
                RateFirebase valoracion = new RateFirebase();
                valoracion.setComentario(mensaje.getText().toString());
                valoracion.setEstrellas(estrellita);
                try{
                    reference.child(xd).setValue(valoracion);
                }catch (DatabaseException e){
                    Toast.makeText(getActivity(), ("Error: "+e.getMessage()), Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getActivity(), "Gracias por evaluar nuestra app!", Toast.LENGTH_SHORT).show();
            }
        });
        return view ;
    }
}
