package com.example.estaodejogos;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class Alterar_nome extends AppCompatActivity {

    EditText txtnome;
    TextView txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    Button btalterardados;
    int indice;
    int id;
    Cursor c;

    DialogInterface.OnClickListener diAlternarInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nome);

        txtnome = (EditText) findViewById(R.id.txtnome);

        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        imgprimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imganterior = (ImageView) findViewById(R.id.imganterior);
        imgproximo = (ImageView) findViewById(R.id.imgproximo);
        imgultimo = (ImageView) findViewById(R.id.imgultimo);

        btalterardados = (Button) findViewById(R.id.btalterardados);

        db = openOrCreateDatabase("Arcade",Context.MODE_PRIVATE, null);

        c = db.query("dbtable2", new String[]
                        {"id","nome"},
                null,null,null,null,null);

        if(c.getCount() > 0){
            c.moveToFirst();
            indice = 1;
            id = c.getInt(0);
            txtnome.setText(c.getString(1));
            txtstatus_registro.setText(indice + " / " + c.getCount());
        }else {
            txtstatus_registro.setText("nennhum registro");
        }

        imgprimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    if (c.getCount() > 1 ){
                        c.moveToFirst();
                        indice = 1;
                        id = c.getInt(0);
                        txtnome.setText(c.getString(1));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            }});
        imganterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    indice--;
                    c.moveToPrevious();
                    id = c.getInt(0);
                    txtnome.setText(c.getString(1));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        imgproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0){
                if (c.getCount() > 0){
                    indice++;
                    c.moveToNext();
                    id = c.getInt(0);
                    txtnome.setText(c.getString(1));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        imgultimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    c.moveToLast();
                    id = c.getInt(0);
                    txtnome.setText(c.getString(1));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        diAlternarInformacoes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String nome = txtnome.getText().toString();

                try {
                    db.execSQL("update  set nome='"+ nome +"' where id = " + id);

                    mostrarmensagem("Dados alterados com sucesso");
                }catch(Exception e){
                    mostrarmensagem("erro" + e.toString());
                }
            }
        };
        btalterardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(Alterar_nome.this);
                dialogo.setTitle("confirma");
                dialogo.setMessage("deseja alterar as informaçoes");
                dialogo.setNegativeButton("não",null);
                dialogo.setPositiveButton("sim", diAlternarInformacoes);
                dialogo.show();
            }
        });
    }
    public void mostrarmensagem(String str){
        androidx.appcompat.app.AlertDialog.Builder dialogo = new
                androidx.appcompat.app.AlertDialog.Builder(Alterar_nome.this);

        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("ok", null);
        dialogo.show();
    }
}