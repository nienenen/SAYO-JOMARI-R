package com.example.project_finals_appdev;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Button bt = findViewById(R.id.log);
        TextView user = findViewById(R.id.name);
        EditText pass = findViewById(R.id.pass);

        auth = FirebaseAuth.getInstance();



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user.getText().toString().trim())){
                    user.setError("Email is empty");
                }
                if(TextUtils.isEmpty(pass.getText().toString().trim())){
                    pass.setError("Email is empty");
                }
                auth.signInWithEmailAndPassword(user.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent in = new Intent(MainActivity.this, Register.class);
                            startActivity(in);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Unsuccesfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });

    }






}