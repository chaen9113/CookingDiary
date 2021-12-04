package com.termproject.db.myskl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database";
    public static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlInfo = "CREATE TABLE IF NOT EXISTS INFO ("
                + "id text primary key, "
                + "pw text, "
                + "name text);";
        db.execSQL(sqlInfo);

        String sqlRecipe = "CREATE TABLE IF NOT EXISTS RECIPE ("
                + "num_recipe integer primary key autoincrement, "
                + "food text, "
                + "recipe text, "
                + "id text references INFO(id));";
        db.execSQL(sqlRecipe);

        String sqlPersonal = "CREATE TABLE IF NOT EXISTS PERSONAL ("
                + "id text references INFO(id), "
                + "num_recipe integer primary key references RECIPE(num_recipe));";
        db.execSQL(sqlPersonal);

        String sqlIngredient = "CREATE TABLE IF NOT EXISTS INGREDIENT ("
                + "num_recipe integer primary key references RECIPE(num_recipe), "
                + "ingredient text);";
        db.execSQL(sqlIngredient);

        String sqlFood = "CREATE TABLE IF NOT EXISTS FOOD ("
                + "num_recipe integer primary key references RECIPE(num_recipe), "
                + "food text references RECIPE(food), "
                + "fcategory text);";
        db.execSQL(sqlFood);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlInfo = "DROP TABLE IF EXISTS INFO";
        db.execSQL(sqlInfo);

        String sqlRecipe = "DROP TABLE IF EXISTS RECIPE";
        db.execSQL(sqlRecipe);

        String sqlPersonal = "DROP TABLE IF EXISTS PERSONAL";
        db.execSQL(sqlPersonal);

        String sqlIngredient = "DROP TABLE IF EXISTS INGREDIENT";
        db.execSQL(sqlIngredient);

        String sqlFood = "DROP TABLE IF EXISTS FOOD";
        db.execSQL(sqlFood);

        onCreate(db);
    }
}
