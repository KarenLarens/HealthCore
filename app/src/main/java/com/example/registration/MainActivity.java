package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.registration.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private TextView userNombre,userEmail,userID;
    private CircleImageView userImg;
    private Button btnCerrarSesion, btnEliminarCta;
    FirebaseAuth mAuth;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNombre = findViewById(R.id.userNombre);
        userEmail = findViewById(R.id.userEmail);
        userID = findViewById(R.id.userId);
        userImg = findViewById(R.id.userImagen);
        btnCerrarSesion = findViewById(R.id.btnLogout);

        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        userEmail.setText(user.getEmail());
        userID.setText(user.getUid());
        ///obtener data
        Bundle data= this.getIntent().getExtras();
        password = data.getString(password);


        //cargar imágen con glide:
        //Glide.with(this).load(url).into(userImg);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
