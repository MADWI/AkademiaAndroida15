package pl.mad_4.database.models;

import android.database.Cursor;

import pl.mad_4.database.DatabaseHelper;
import pl.mad_4.utils.Utils;

/**
 * Created by rafalmielnik on 01.06.15.
 */
public class User {

    private long id;

    private String name;

    private String surname;

    private String profession;

    public User(Cursor cursor) {
        this.id = Utils.getLongFromCursor(cursor, DatabaseHelper.COLUMN_ID);
        this.name = Utils.getStringFromCursor(cursor, DatabaseHelper.COLUMN_NAME);
        this.surname = Utils.getStringFromCursor(cursor, DatabaseHelper.COLUMN_SURNAME);
        this.profession = Utils.getStringFromCursor(cursor, DatabaseHelper.COLUMN_PROFESSION);
    }

    public User() { this(0, "", "", "");}

    public User(long id, String name, String surname, String profession) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
