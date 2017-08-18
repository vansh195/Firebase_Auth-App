package com.cloud.www.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText t1,t2;
    Button b1;
    TextView textview;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=(EditText)this.findViewById(R.id.editText2);
        t2=(EditText)this.findViewById(R.id.editText4);
        b1=(Button)this.findViewById(R.id.button3);
        textview=(TextView)this.findViewById(R.id.textView3);
        b1.setOnClickListener(this);
        textview.setOnClickListener(this);
        firebaseAuth= FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);

    }
    public void logincheck(){
        String user=t1.getText().toString();
        String pass=t2.getText().toString();
        if (TextUtils.isEmpty(user)) {
            return;
        }
        if (TextUtils.isEmpty(pass)){
            return;
        }
        dialog.setMessage("Login Check....Please Wait");
        dialog.show();
        firebaseAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    dialog.dismiss();;
                    finish();
                    startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Main2Activity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
       if (v==b1){
           logincheck();
       }
       if (v==textview){
           startActivity(new Intent(Main2Activity.this,MainActivity.class));
       }
    }
}
