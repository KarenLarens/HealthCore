package com.example.registration.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.registration.PerfilFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.registration.MenuActivity;
import com.example.registration.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CodeFragment extends Fragment {
    ImageView ivOutput;

    DatabaseReference reference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String UID= user.getUid();
    Button btn;

    String Nombre, ApePat,ApeMat,Age,Sexo,Estatura,Peso,Alergia,Sangre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);

        reference= FirebaseDatabase.getInstance().getReference().child("ProfileFirebase");
        reference.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    try{
                        Nombre=(" "+ snapshot.child("nombre").getValue().toString());
                        ApePat=(" "+ snapshot.child("apePat").getValue().toString());
                        ApeMat=(" "+ snapshot.child("apeMat").getValue().toString());
                        Age=(" "+snapshot.child("edad").getValue().toString());
                        Sexo=(" "+snapshot.child("sexo").getValue().toString());

                        Estatura=(" "+snapshot.child("estatura").getValue().toString());
                        Peso=(" "+snapshot.child("peso").getValue().toString());
                        Alergia=(" "+snapshot.child("alergias").getValue().toString());
                        Sangre=(" "+snapshot.child("tsangre").getValue().toString());

                        String sText="Nombre: "+Nombre+" | Apellido Paterno: "+ApePat+" | Apellido Materno: "+ApeMat+
                                " | Edad: "+Age+" | Sexo: "+Sexo+" | Estatura: "+Peso+" | Alergia: "+Alergia+" | Tipo de sangre: "+Sangre+"\n Recuerda descargar HealthCore ;) en: \n https://play.google.com/store/apps/details?id=com.dts.freefireth&hl=es_MX";
                        ivOutput = view.findViewById(R.id.iv_output);
                        MultiFormatWriter writer = new MultiFormatWriter();
                        try {
                            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                            BarcodeEncoder encoder = new BarcodeEncoder();
                            Bitmap bitmap = encoder.createBitmap(matrix);
                            ivOutput.setImageBitmap(bitmap);

                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }catch (DatabaseException e){
                        Toast.makeText(getActivity(), ("Error: "+e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}