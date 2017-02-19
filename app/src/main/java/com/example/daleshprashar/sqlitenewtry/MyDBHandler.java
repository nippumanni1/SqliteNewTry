package com.example.daleshprashar.sqlitenewtry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DALESH PRASHAR on 2/18/2017.
 */

//its gonna be responsible for everything we do with database..

public class MyDBHandler extends SQLiteOpenHelper
{
    //its gonna have one table and that is gonna store however items you want and
    //          those items are gonna have id and productname.
 private static final int DATABASE_VERSION =1;
    // what we are doing that we are saving the info in the file...
    //     so that they can come later and get the data what they were doing earlier.
    private static final String DATABASE_NAME="products.db";
// name of the table. how sql works is we can store bunch of different tables
    //     in that database.
    public  static  final  String TABLE_PRODUCTS="products";
    // name of columns.
    public  static  final  String COLUMN_ID ="_id";
    public  static  final  String COLUMN_PRODUCTNAME ="productname";


    // constructor is for housekeeping stuff , we need to pass some info along to the  superclass(class that works directly with android)
    // 1st parameter is context...
    // 2nd paramter name =which file to save under
    // factory background info
    // version = database version
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
// what you want me to do.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // lets crrate a  new query...
        // we write "CRRATE TABLE"
        // then pass table products
        // then what columns you want first is id
        // put closing and opening brackets good.
        // what properties you want for columnid and productname separaretley
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_PRODUCTNAME + " TEXT " +
                ");";
        sqLiteDatabase.execSQL(query);

    }
// if we are upgrading the version , we can work on that.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
// delete current table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        // if we just leave the first line as it is , we wont have any table to work with
      //  so , we need Oncrrate to call again and pass along sqlitedatabse.
        onCreate(sqLiteDatabase);
    }


    // the housekeeping stuff is done above , now we need to add 3 more really easy methods.
    // FIRST METHOD to add new row.
    // SECOND METHOD to delete a product.
    // THIRD METHOD to convert the databse to string so m that we can print out the stuff.

    //Adda new row to the database
    public  void addProduct (Products products)
    {
        // cv are class that allows you to set bunch of different values
        // for different columns and then allows you to insert all of them.
        // in one statement.
        // so inserting rows into tables becomes easy for us.
        ContentValues contentValues = new ContentValues();
        // now we are gonna store list of values..
        // two parameters . first one is what columns we will be storing this in
        // second is what is the value that you wanna put in that column.
        contentValues.put(COLUMN_PRODUCTNAME, products.get_productname());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // database in above line is the database we are gonna write into.
        // this line is gonna insert a new product or new row into the table.

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, contentValues);
        sqLiteDatabase.close();
        // so the above method is when the user is gonna click add.

    }

    // METHOD TO DELETE TABLE FROM DATATBASE
    public  void deleteProduct(String productName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // in order to dleete a single row from the table , we dont wanna use
        // DROP , because Drop can delete entire table.
        // delete from      this table(tableproducts)   where   productname(Columnproducts)  is eualto   whatever     productname   we passed in.
        sqLiteDatabase.execSQL("DELETE FROM "+ TABLE_PRODUCTS +" WHERE " + COLUMN_PRODUCTNAME + "=\"" +  productName  + "\";" );
    }



    // PRINTING DATABASE RESULTS
    public  String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // select all from table where 1= every condition is matched.
        //      kind means select every column and select every row.
        String query = "SELECT * FROM " + TABLE_PRODUCTS + "WHERE 1";
// cursor like pointer what we want in the results and what can we printout // everything like that.
        // so , cursor points to a location in your results.
        Cursor c = sqLiteDatabase.rawQuery(query ,null);
        // Move to the first row in your results.
        c.moveToFirst();
// Not positioned after last.
        while (!c.isAfterLast())
        {
            if (c.getString(c.getColumnIndex("productname"))!= null)
            {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
/*
adding and deleting rows...
 */