package com.jefer_teste_01.loginemail.loginemail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //Objetos
    private Button CadastrarID;
    private Button preenche;
    private Button LogarID;
    private EditText Recebe_Email;
    private EditText Recebe_Senha;
    //Firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declarando objetos da tela

        CadastrarID = (Button) findViewById(R.id.CadastrarID);
        LogarID = (Button) findViewById(R.id.LogarID);
        preenche = (Button) findViewById(R.id.preenche);


        Recebe_Senha = (EditText) findViewById(R.id.Recebe_Senha);
        Recebe_Email = (EditText) findViewById(R.id.Recebe_Email);

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseAuth.createUserWithEmailAndPassword();

//*******************************************************************************//

        LogarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String LogaEmail = Recebe_Email.getText().toString();
                String LogaSenha = Recebe_Senha.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(LogaEmail,LogaSenha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //TEstando se a criação do login deu certo
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Efetuado.",Toast.LENGTH_SHORT).show();
                            Recebe_Email.setText("");
                            Recebe_Senha.setText("");
                            startActivity( new Intent(MainActivity.this,Pos_Login.class));

                        }else{
                            Toast.makeText(MainActivity.this,"ERRO AO LOGAR",Toast.LENGTH_SHORT).show();
                            Recebe_Email.setText("");
                            Recebe_Senha.setText("");
                        }

                    }
                });




            }
        });//fim do botão Logar
//*******************************************************************************//

        CadastrarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity( new Intent(MainActivity.this,Cadastro.class));

            }
        });//fim do botão Cadastrar

//*******************************************************************************//

        preenche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recebe_Email.setText("teste2@gmail.com");
                Recebe_Senha.setText("teste2");


            }
        });//fim do botão Cadastrar




    }//Fim do oncreat
}//Fim da main activity
