package com.example.daleshprashar.sqlitenewtry;

/**
 * Created by DALESH PRASHAR on 2/18/2017.
 */

public class Products
{
    // _id of each product..
    private  int _id;
    private  String _productname;

    public  Products()
    {

    }


  public   Products(String productname)
    {
        this._productname= productname;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }
}
