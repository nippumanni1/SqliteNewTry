package com.example.daleshprashar.sqlitenewtry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    EditText buckysInput;
    TextView buckysText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buckysInput = (EditText) findViewById(R.id.editText);
        buckysText = (TextView) findViewById(R.id.textView2);
        dbHandler =new MyDBHandler(this , null , null , 1);
        printdatabase();

    }

    // add a prouct to the dtaabse
    public void ADD(View view)
    {
        Products products = new Products(buckysInput.getText().toString());
        dbHandler.addProduct(products);
        printdatabase();
    }
    public  void  DELETE (View view)
    {
        String input = buckysInput.getText().toString();
        dbHandler.deleteProduct(input);
        printdatabase();

    }



    public  void printdatabase()
    {
        String dbString = dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText("");
    }

}
