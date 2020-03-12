package com.jefer_teste_01.loginemail.loginemail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {


    //Objetos
    private Button GravaID;
    private Button VoltarID;
    private TextView Recebe_Cadastra_Email;
    private TextView Recebe_Cadastra_Senha;
    //Firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Objetos
        GravaID = (Button) findViewById(R.id.GravaID);
        VoltarID = (Button) findViewById(R.id.VoltarID);
        Recebe_Cadastra_Email = (EditText) findViewById(R.id.Recebe_Cadastra_Email);
        Recebe_Cadastra_Senha = (EditText) findViewById(R.id.Recebe_Cadastra_Senha);
        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseAuth.createUserWithEmailAndPassword();

//*******************************************************************************//

        GravaID.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        String GravaEmail = Recebe_Cadastra_Email.getText().toString();
        String GravaSenha = Recebe_Cadastra_Senha.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(GravaEmail,GravaSenha)
                        .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            //TEstando se a criação do login deu certo
                            if (task.isSuccessful()){
                                Toast.makeText(Cadastro.this,"Cadastro Efetuado com sucesso.",Toast.LENGTH_SHORT).show();
                                Recebe_Cadastra_Email.setText("");
                                Recebe_Cadastra_Senha.setText("");

                            }else{
                                Toast.makeText(Cadastro.this,"ERRO AO CADASTRAR",Toast.LENGTH_SHORT).show();
                                Recebe_Cadastra_Email.setText("");
                                Recebe_Cadastra_Senha.setText("");
                            }

                            }
                        });



        }
    });//fim do botão grava
//*******************************************************************************//
    VoltarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Recebe_Cadastra_Email.setText("");
            Recebe_Cadastra_Senha.setText("");
            startActivity( new Intent(Cadastro.this,MainActivity.class));

            }
    });//fim do botão Voltar



    }//fim do on create
}//fim da classe cadastro
