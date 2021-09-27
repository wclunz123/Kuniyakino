package com.example.kuniyakino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kuniyakino.model.Account;
import com.example.kuniyakino.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Database name and table declaration
    private static final String DB_NAME = "Kuniyakino.db";
    private static final String DB_TABLE = "Account";
    private static final String DB_TABLE2 = "Product";
    private static final String DB_TABLE3 = "Cart";

    // Account Table
    private static final String EMAIL = "EMAIL";
    private static final String NAME = "NAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String ADDLINE1 = "ADDLINE1";
    private static final String ADDLINE2 = "ADDLINE2";
    private static final String MOBILE = "MOBILE";
    private static final String PAYMENT = "PAYMENT";
    private static final String CARDNO = "CARDNO";

    // SQL to create account table
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            EMAIL + " TEXT PRIMARY KEY, " +
            NAME + " TEXT, " +
            PASSWORD + " TEXT, " +
            ADDLINE1 + " TEXT, " +
            ADDLINE2 + " TEXT, " +
            MOBILE + " TEXT, " +
            PAYMENT + " TEXT, " +
            CARDNO + " TEXT " + ")";

    // Book Table
    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String AUTHOR = "AUTHOR";
    private static final String PUBLICATION = "PUBLICATION";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String PRICE = "PRICE";
    private static final String PAGE = "PAGE";
    private static final String QUANTITY = "QUANTITY";
    private static final String URL = "URL";

    // SQL to create book table
    private static final String CREATE_TABLE2 = "CREATE TABLE " + DB_TABLE2 + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            TITLE + " TEXT, " +
            AUTHOR + " TEXT, " +
            PUBLICATION + " TEXT, " +
            DESCRIPTION + " TEXT, " +
            PRICE + " INTEGER, " +
            PAGE + " INTEGER, " +
            QUANTITY + " INTEGER, " +
            URL + " INTEGER " + ")";

    // Cart Table
    private static final String CART_ID = "CART_ID";
    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String PRODUCT_ID = "PRODUCT_ID";

    // SQL to create cart table
    private static final String CREATE_TABLE3 = "CREATE TABLE " + DB_TABLE3 + " (" +
            CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_EMAIL + " TEXT, " +
            PRODUCT_ID + " INTEGER " + ")";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE3);
        onCreate(db);
    }

    // Method used to insert new account into the database
    public boolean insertAccountData(String email, String name, String password, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, email);
        contentValues.put(NAME, name);
        contentValues.put(PASSWORD, password);
        contentValues.put(MOBILE, mobile);
        long result = db.insert(DB_TABLE, null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    // Method used to insert new product data into the database
    public boolean insertProductData(int id, String title, String author, String publication, String price, String desc, int page, int qty, int url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(TITLE, title);
        contentValues.put(AUTHOR, author);
        contentValues.put(PUBLICATION, publication);
        contentValues.put(DESCRIPTION, desc);
        contentValues.put(PRICE, price);
        contentValues.put(PAGE, page);
        contentValues.put(QUANTITY, qty);
        contentValues.put(URL, url);
        long result = db.insert(DB_TABLE2, null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    // Method used to insert specific cart item into the user's cart
    public boolean insertCartData(String email, int product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL, email);
        contentValues.put(PRODUCT_ID, product_id);
        long result = db.insert(DB_TABLE3, null, contentValues);

        if (result == -1) return false;
        else return true;
    }

    // Method used to check if the user email and password is valid in the database for login purpose
    public boolean getUserAccount(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE
                + " WHERE EMAIL = '" + email
                + "' AND PASSWORD = '" + password +"';";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount()>0) return true;
        else return false;
    }

    // Method used to get the Account object or credentials of the user
    public Account getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE
                + " WHERE EMAIL = '" + email +"';";
        Cursor cursor = db.rawQuery(sql, null);

        Account account = new Account();
        if (cursor.moveToFirst()) {
            account.setName(cursor.getString(1));
            // account.setPassword(cursor.getString(2));
            account.setAddressLine1(cursor.getString(3));
            account.setAddressLine2(cursor.getString(4));
            account.setMobileNumber(cursor.getLong(5));
            account.setPaymentMethod(cursor.getString(6));
            account.setCardNo(cursor.getString(7));
        }
        return account;
    }

    // Method used to get specific product based on the product id
    public Book getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE2
                + " WHERE ID = '" + id +"';";

        Cursor cursor = db.rawQuery(sql, null);

        Book book = new Book();
        if (cursor.moveToFirst()) {
            book.setId(cursor.getInt(0));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setPublication(cursor.getString(3));
            book.setDescription(cursor.getString(4));
            book.setPrice(cursor.getString(5));
            book.setPage(cursor.getInt(6));
            book.setQuantity(cursor.getInt(7));
            book.setImageURL(cursor.getInt(8));
        }
        return book;
    }

    // Method to get all the products list
    public List<Book> getAllProduct() {
        List<Book> booksList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE2;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setPublication(cursor.getString(3));
                book.setDescription(cursor.getString(4));
                book.setPrice(cursor.getString(5));
                book.setPage(cursor.getInt(6));
                book.setQuantity(cursor.getInt(7));
                book.setImageURL(cursor.getInt(8));
                booksList.add(book);
            } while (cursor.moveToNext());
        }
        return booksList;
    }

    // Method to get the cart items of the user
    public List<Book> getCartData(String email) {
        List<Book> booksList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT PRODUCT_ID FROM " + DB_TABLE3
                + " WHERE USER_EMAIL = '" + email + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int currentProduct = cursor.getInt(0);
                Book book = getProduct(currentProduct);
                booksList.add(book);
            } while (cursor.moveToNext());
        }
        return booksList;
    }

    // Method to get the number of items in the user's cart
    public int getCartCount(String email) {
        int cartCount = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT PRODUCT_ID FROM " + DB_TABLE3
                + " WHERE USER_EMAIL = '" + email + "';";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                cartCount++;
            } while (cursor.moveToNext());
        }
        return cartCount;
    }

    // Method used to update the account credentials of the user
    public boolean updateAccount(String email, String name, String addLine1, String addLine2, long mobile, String payment, String cardNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(ADDLINE1, addLine1);
        contentValues.put(ADDLINE2, addLine2);
        contentValues.put(MOBILE, mobile);
        contentValues.put(PAYMENT, payment);
        contentValues.put(CARDNO, cardNo);

        long result = db.update(DB_TABLE, contentValues, EMAIL + " = ?",
                new String[] { String.valueOf(email) });

        if (result == -1) return false;
        else return true;
    }

    // Method used to delete specific cart item of the user
    public void deleteCartData(String email, int product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + DB_TABLE3
                + " WHERE USER_EMAIL = '" + email + "' " +
                "AND PRODUCT_ID = '" + product_id + "';";

        if (db != null)
            db.execSQL(sql);
    }

    // Method used to delete all the cart item during checkout or clearcart
    public void deleteAllCartData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + DB_TABLE3
                + " WHERE USER_EMAIL = '" + email + "';";

        if (db != null)
            db.execSQL(sql);
    }
}
