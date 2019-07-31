package edu.csc4360.project2.wineinventory.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WineDatabaseHandler extends SQLiteOpenHelper {

    public WineDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String DATABASE_NAME = "test.db";

    //wine description table
    private static final String WINE_TABLE = "wine_table";

    private static final String COL_WINE_ID = "Wine_id";
    private static final String COL_MODEL = "Model";
    private static final String COL_BRAND = "Brand";
    private static final String COL_TYPE = "Type";
    private static final String COL_YEAR = "Year";
    private static final String COL_COST = "Cost";

    //inventory table
    private static final String INVENTORY_TABLE = "inventory_table";

    private static final String COL_INVENTORY = "inventory";

    //sales table
    private static final String SALES_TABLE = "sales_table";

    private static final String COL_INVOICE = "Invoice_Number";
    private static final String COL_QUANTITY = "Quantity";
    private static final String COL_DATE = "Date";


    SQLiteDatabase database;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ WINE_TABLE +" ( "+COL_WINE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_MODEL+" TEXT, "+COL_BRAND+" TEXT, "+COL_TYPE+" TEXT, "+COL_YEAR+" INTEGER, "+COL_COST+" FLOAT)");
        db.execSQL("CREATE TABLE "+ INVENTORY_TABLE +" ( "+COL_WINE_ID+" INTEGER, "+COL_INVENTORY+" INTEGER)");
        db.execSQL("CREATE TABLE "+ SALES_TABLE +" ( "+COL_INVOICE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_WINE_ID+" INTEGER, "+COL_QUANTITY+" INTEGER, "+COL_DATE+" DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ WINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ INVENTORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ SALES_TABLE);
        onCreate(db);
    }

    public void insertData(String model, String brand, String type, String year, String cost, String inventory){
        database = getWritableDatabase();
        ContentValues contentWineTable = new ContentValues();
        contentWineTable.put(COL_MODEL,model);
        contentWineTable.put(COL_BRAND,brand);
        contentWineTable.put(COL_TYPE,type);
        contentWineTable.put(COL_YEAR,year);
        contentWineTable.put(COL_COST,cost);
        database.insert(WINE_TABLE,null , contentWineTable);

        ContentValues contentInventoryTable = new ContentValues();
        contentInventoryTable.put(COL_INVENTORY,inventory);
        database.insert(INVENTORY_TABLE,null, contentInventoryTable);
    }

    public Cursor getAllData(){
        database = getWritableDatabase();
        String query = "SELECT * FROM " + WINE_TABLE;
        Cursor result = database.rawQuery(query, null);
        return result;
    }

    public Cursor search(String brand){
        database = getWritableDatabase();
        String query = "SELECT  * FROM " + WINE_TABLE+ " where " + COL_MODEL + " = ? ";
        Cursor result = database.rawQuery(query, new String[]{brand});
        return result;
    }

    public Cursor searchById(String id){
        database = getWritableDatabase();
        String query = "SELECT  * FROM " + WINE_TABLE+ " where " + COL_WINE_ID + " = ? ";
        Cursor result = database.rawQuery(query, new String[]{id});
        return result;
    }

    public void  updateData(String wineId, String model, String brand, String type, String year, String cost,String inventory){
        database = getWritableDatabase();

        ContentValues contentWineTable = new ContentValues();
        contentWineTable.put(COL_MODEL,model);
        contentWineTable.put(COL_BRAND,brand);
        contentWineTable.put(COL_TYPE,type);
        contentWineTable.put(COL_YEAR,year);
        contentWineTable.put(COL_COST,cost);
        database.update(WINE_TABLE, contentWineTable,"Wine_id = ?",new String[]{wineId});

        ContentValues contentInventoryTable = new ContentValues();
        contentInventoryTable.put(COL_INVENTORY,inventory);
        database.update(INVENTORY_TABLE, contentInventoryTable,"Wine_id = ?",new String[]{wineId});
    }


    public Integer deleteData(String wineId){
        database = getWritableDatabase();
        return database.delete(WINE_TABLE, "Wine_id = ?", new String[]{wineId});
    }
    
    //This delete metohd is used in the ViewActivity
    public Integer deletename(String winename ){
        database = getWritableDatabase();
        return database.delete((WINE_TABLE), "model = ?", new String[]{winename});

    }



}
