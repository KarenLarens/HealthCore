package com.example.registration.ui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.registration.PerfilFirebase;
import com.example.registration.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment2 extends Fragment {

    DatabaseReference reference,reference2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    EditText NombreET,ApePatET,ApeMatET,AgeET, EstaturaET,PesoET,AlergiaET,SangreET, SexoET;
    Button submit;

    //Datos de sesión activa
    String UID = user.getUid();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile2, container, false);

        SexoET = (EditText) view.findViewById(R.id.SexoET);

        NombreET= (EditText) view.findViewById(R.id.NombreET);
        ApePatET= (EditText) view.findViewById(R.id.ApePatET);
        ApeMatET= (EditText) view.findViewById(R.id.ApeMatET);
        AgeET= (EditText) view.findViewById(R.id.AgeET);

        EstaturaET= (EditText) view.findViewById(R.id.EstaturaET);
        PesoET= (EditText) view.findViewById(R.id.PesoET);
        AlergiaET= (EditText) view.findViewById(R.id.AlergiasET);
        SangreET= (EditText) view.findViewById(R.id.TSangreET);

        submit = (Button) view.findViewById(R.id.BtnActualizar);

        reference= FirebaseDatabase.getInstance().getReference().child("ProfileFirebase");

        reference.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    try{
                        NombreET.setText(" "+ snapshot.child("nombre").getValue().toString());
                        ApePatET.setText(" "+ snapshot.child("apePat").getValue().toString());
                        ApeMatET.setText(" "+ snapshot.child("apeMat").getValue().toString());
                        AgeET.setText(" "+snapshot.child("edad").getValue().toString());
                        SexoET.setText(" "+snapshot.child("sexo").getValue().toString());

                        EstaturaET.setText(" "+snapshot.child("estatura").getValue().toString());
                        PesoET.setText(" "+snapshot.child("peso").getValue().toString());
                        AlergiaET.setText(" "+snapshot.child("alergias").getValue().toString());
                        SangreET.setText(" "+snapshot.child("tsangre").getValue().toString());
                    }catch (DatabaseException e){
                        Toast.makeText(getActivity(), ("Error: "+e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilFirebase PF=new PerfilFirebase();
                PF.setNombre(NombreET.getText().toString().trim());
                PF.setApePat(ApePatET.getText().toString().trim());
                PF.setApeMat(ApeMatET.getText().toString().trim());
                PF.setEdad(AgeET.getText().toString().trim());
                PF.setSexo(SexoET.getText().toString().trim());

                PF.setEstatura(EstaturaET.getText().toString().trim());
                PF.setPeso(PesoET.getText().toString().trim());
                PF.setAlergias(AlergiaET.getText().toString().trim());
                PF.setTSangre(SangreET.getText().toString().trim());

                try{
                    reference2= FirebaseDatabase.getInstance().getReference().child("ProfileFirebase");
                    reference2.child(UID).setValue(PF);
                    Toast.makeText(getActivity(),"Tus datos han sido actualizados.",Toast.LENGTH_SHORT).show();
                }catch (DatabaseException e){
                    Toast.makeText(getActivity(), ("Error: "+e.getMessage()), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
