package com.example.estaodejogos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.ResultSet;


public class dbConexao extends SQLiteOpenHelper {

    private static String dbnome = "Arcade";
    private static int dbversion = 1;
    private static String dbtable1 = "login";
    private static String dbtable2 = "record";

    private static String id_user = "id";
    private static String id_record = "id";
    private static String nome = "nome";
    private static String nome_arcade = "anome";
//    private static String user = "user";
    private static String senha = "senha";
    private static String ranking = "ranking";
    public dbConexao(@Nullable Context context) {
        super(context, dbnome, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + dbtable1 + "(" + id_user + " INTEGER PRIMARY KEY AUTOINCREMENT," + nome + " TEXT, "+ senha + " TEXT)";
        String query1 = "create table " + dbtable2 + "("+ id_record + "INTEGER PRIMARY KEY AUTOINCREMENT, " +nome_arcade + " TEXT, " + ranking + " INTEGER)";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if Exists " + dbtable1);
        sqLiteDatabase.execSQL("drop table if Exists " + dbtable2);
        onCreate(sqLiteDatabase);

    }
    public void addUsers(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(nome,users.getName());
        values.put(senha,users.getSenha());
        db.insert(dbtable1, null, values);
        db.close();
    }
    public void verificarUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("SELECT * FROM Arcade");
//        ResultSet rs = bd.execSQL(query);
    }
    public void addRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_record,record.getId());
        values.put(nome_arcade,record.getNome());
        values.put(ranking,record.getranking());
        db.insert(dbtable2,null,values);
        db.close();
    }
}
