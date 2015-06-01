package pl.mad_4.utils;

import android.database.Cursor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by rafalmielnik on 29.05.15.
 */
public class Utils {

    public static void writeToFile(String data, String path) {

        if (data == null || path == null)
            throw new NullPointerException();

        File file = new File(path);

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContents(String path) {

        if (path == null)
            throw new NullPointerException();

        File fl = new File(path);
        FileInputStream fin = null;
        String ret = null;
        try {
            fin = new FileInputStream(fl);
            ret = convertStreamToString(fin);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }


    // Databse

    public static long getLongFromCursor(Cursor cursor, String columnName) {
        long value = -1;

        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            value = cursor.getLong(columnIndex);
        }

        return value;
    }

    public static String getStringFromCursor(Cursor cursor, String columnName) {
        String value = "";

        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            value = cursor.getString(columnIndex);
        }

        return value;
    }

}
