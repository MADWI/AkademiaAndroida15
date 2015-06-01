package pl.mad_4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pl.mad_4.database.models.User;

/**
 * Created by rafalmielnik on 29.05.15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "database.db";

    // Tabela użytkowników
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_PROFESSION = "profession";


    private Context context;


    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);",
                TABLE_USERS, COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME, COLUMN_PROFESSION);
        db.execSQL(createUsersQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXIST %s", TABLE_USERS));
        onCreate(db);
    }

    public long insertUser(SQLiteDatabase db, User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_SURNAME, user.getSurname());
        values.put(COLUMN_PROFESSION, user.getProfession());

        return db.insert(TABLE_USERS, null, values);
    }


    public List<User> selectAllUsers(SQLiteDatabase db) {
        List<User> users = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = new User(cursor);
            users.add(user);

            cursor.moveToNext();
        }

        cursor.close();

        return users;
    }


    public boolean updateUser(SQLiteDatabase db, User user) {
        ContentValues values = new ContentValues();
        if (!user.getName().equals("")) values.put(COLUMN_NAME, user.getName());
        if (!user.getSurname().equals("")) values.put(COLUMN_SURNAME, user.getSurname());
        if (!user.getProfession().equals("")) values.put(COLUMN_PROFESSION, user.getProfession());

        return db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(user.getId()) }) > 0;
    }


    public boolean deleteUser(SQLiteDatabase db, User user) {
        return db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[] { String.valueOf(user.getId()) }) > 0;
    }


}
