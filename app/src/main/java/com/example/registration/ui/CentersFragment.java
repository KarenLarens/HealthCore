package com.example.registration.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.MainActivity;
import com.example.registration.R;
import com.bumptech.glide.Glide;
import com.example.registration.loginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.registration.R;

public class CentersFragment extends Fragment {
    private TextView userNombre,userEmail,userID;
    private CircleImageView userImg;
    private Button btnCerrarSesion, btnEliminarCta;
    FirebaseAuth mAuth;
    public String password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_centers, container, false);

        userNombre = view.findViewById(R.id.userNombre);
        userEmail = view.findViewById(R.id.userEmail);
        userID = view.findViewById(R.id.userId);
        userImg = view.findViewById(R.id.userImagen);
        btnCerrarSesion = view.findViewById(R.id.btnLogout);
        btnEliminarCta= view.findViewById(R.id.btnEliminarCta);


        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        userEmail.setText(user.getEmail());
        userID.setText(user.getUid());
        ///obtener data
        Bundle data= getActivity().getIntent().getExtras();

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), loginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnEliminarCta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //////////// aqui me quede en el minuto 23:57
                AuthCredential credential= EmailAuthProvider.getCredential(user.getEmail(), password);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull  Task<Void> task) {
                                if (task.isSuccessful()){
                                    mAuth.signOut();
                                    Intent intent = new Intent(getActivity(), loginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    mAuth.signOut();

                                }else{
                                    Toast.makeText(getContext(), "No se puede eliminar:"+task.getException(),Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                });

            }
        });

        return view;
    }
}
