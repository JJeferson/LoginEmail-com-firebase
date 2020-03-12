package com.jefer_teste_01.loginemail.loginemail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pos_Login extends AppCompatActivity {

    //abre conexao com o banco de dados voltando sempre pro nó raiz
    private DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference DuplicatasReferencia = databaseReferencia.child("Duplicatas");

    //Objetos
    private Button GravaServico;
    private EditText RecebeDescricao;
    private EditText RecebeCliente;
    private EditText RecebeValor;
    private static final String ARQUIVO_REFERENCIA = "ArquivoReferencia";
    //montando kistview
    private ListView listView_ID;
    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;


    final cadastraServico CadastraServicos = new cadastraServico();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos__login);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);*/
        /*setSupportActionBar(toolbar);*/
        //Objetos

        GravaServico = (Button) findViewById(R.id.GravaServico);
        RecebeDescricao = (EditText) findViewById(R.id.RecebeDescricao);
        RecebeCliente = (EditText) findViewById(R.id.RecebeCliente);
        RecebeValor = (EditText) findViewById(R.id.RecebeValor);
        listView_ID = (ListView) findViewById(R.id.listView_ID);



        //declarando o ouvinte, aquele que vai er o banco na nuvem
        DuplicatasReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /*    .child("Serviços")*/
                /*

                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    itens.add(value.getValue(String.class));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(Pos_Login.this, android.R.layout.simple_list_item_1,
                        itens);
                listView_ID.setAdapter(arrayAdapter);*/



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//fim do ouvinte




        //Gerar um shared preference para gerar um ID direitinho
        final SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_REFERENCIA, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit(); // crie um editor para poder mandar dados

        //editor.putInt("Valor", 0001);
        //editor.commit();


        GravaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*String  TestaCliente =  RecebeCliente.getText().toString();
                if (TestaCliente.equals("")) {
                    RecebeCliente.setText("");
                    RecebeDescricao.setText("");
                    RecebeValor.setText("");
                    Toast.makeText(Pos_Login.this,"Cliente em Branco, Verifica.",Toast.LENGTH_SHORT).show();

                }else {*/
                    CadastraServicos.setCliente(RecebeCliente.getText().toString());
                    CadastraServicos.setDescricao(RecebeDescricao.getText().toString());
                    CadastraServicos.setValor(RecebeValor.getText().toString());



                    ; //inicia o incrementador com zero

                    int Incrementador;
                    Incrementador = sharedPreferences.getInt("Valor",0);
                    Incrementador = Incrementador+1;
                    editor.putInt("Valor", Incrementador);
                    editor.commit();

                   sharedPreferences.getInt("Valor",0); //atualiza incrementador com valor do shared preferences
                    editor.putInt("Valor", Incrementador++); // incrementa com  +1 o valor do incrementador e salva no shared preferences
                    editor.commit(); //comita este caralho

                    DuplicatasReferencia.child("Serviços").child(""+Incrementador).setValue(CadastraServicos);

                    // usuarioReferencia.child("001").child("nome").setValue(""+Nome+"");
                    Toast.makeText(Pos_Login.this,"Dados salvos com sucesso",Toast.LENGTH_SHORT).show();
                    RecebeCliente.setText("");
                    RecebeDescricao.setText("");
                    RecebeValor.setText("");

                }//fim do else, validação se campos vazios   */


           /* }*/
        });//fim do botão Cadastrar







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }//fim do contrutor principal



}//Fim da classe principal
