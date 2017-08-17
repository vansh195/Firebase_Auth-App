package com.cloud.www.firebaseauth;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    EditText t1,t2;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)this.findViewById(R.id.button2);
        t1=(EditText)this.findViewById(R.id.editText);
        t2=(EditText)this.findViewById(R.id.editText3);
        b1.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
    }
    public void registerUser(){
        String user=t1.getText().toString();
        String pass=t2.getText().toString();
        if (TextUtils.isEmpty(user)){
            return;
        }
        if (TextUtils.isEmpty(pass)){
            return;
        }
        dialog.setMessage("Registering User....Please Wait");
        dialog.show();
        firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    t1.setText("");
                    t2.setText("");
                    t1.requestFocus();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
         registerUser();

    }
}
