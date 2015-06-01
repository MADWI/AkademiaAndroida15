package pl.mad_4.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.mad_4.R;
import pl.mad_4.database.DatabaseHelper;
import pl.mad_4.database.models.User;

/**
 * Created by rafalmielnik on 29.05.15.
 */
public class FragmentDatabase extends Fragment implements View.OnClickListener {

    private Button buttonAdd;
    private Button buttonUpdate;
    private Button buttonDelete;
    private Button buttonRead;

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextProfession;

    private TextView textViewContent;

    private DatabaseHelper databaseHelper;


    public static Fragment newInstance() {
        FragmentDatabase fragmentMain = new FragmentDatabase();
        return fragmentMain;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        buttonAdd = (Button) view.findViewById(R.id.button_db_add);
        buttonAdd.setOnClickListener(this);
        buttonUpdate = (Button) view.findViewById(R.id.button_db_update);
        buttonUpdate.setOnClickListener(this);
        buttonDelete = (Button) view.findViewById(R.id.button_db_delete);
        buttonDelete.setOnClickListener(this);
        buttonRead = (Button) view.findViewById(R.id.button_db_read);
        buttonRead.setOnClickListener(this);

        editTextId = (EditText) view.findViewById(R.id.edit_text_id);
        editTextName = (EditText) view.findViewById(R.id.edit_text_name);
        editTextSurname = (EditText) view.findViewById(R.id.edit_text_surname);
        editTextProfession = (EditText) view.findViewById(R.id.edit_text_profession);

        textViewContent = (TextView) view.findViewById(R.id.text_view_db_content);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
    }

    @Override
    public void onClick(View v) {

        hideErrors();

        switch (v.getId()) {
            case R.id.button_db_add:
                add();
                break;
            case R.id.button_db_update:
                update();
                break;
            case R.id.button_db_delete:
                delete();
                break;
            case R.id.button_db_read:
                read();
                break;
        }

    }

    private void add() {

        if (editTextName.getText().toString().equals("")) {
            editTextName.setError(getString(R.string.required_filed));
            return;
        }

        if (editTextSurname.getText().toString().equals("")) {
            editTextSurname.setError(getString(R.string.required_filed));
            return;
        }

        if (editTextProfession.getText().toString().equals("")) {
            editTextProfession.setError(getString(R.string.required_filed));
            return;
        }


        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String profession = editTextProfession.getText().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        databaseHelper.insertUser(db, new User(0, name, surname, profession));
        db.close();

        clearEditTexts();

        read();
    }

    private void update() {

        if (editTextId.getText().toString().equals("")) {
            editTextId.setError(getString(R.string.required_filed));
            return;
        }

        long id = Long.parseLong(editTextId.getText().toString());
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String profession = editTextProfession.getText().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        databaseHelper.updateUser(db, new User(id, name, surname, profession));
        db.close();

        clearEditTexts();

        read();
    }

    private void delete() {

        if (editTextId.getText().toString().equals("")) {
            editTextId.setError(getString(R.string.required_filed));
            return;
        }

        long id = Long.parseLong(editTextId.getText().toString());

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        databaseHelper.deleteUser(db, new User(id, "", "", ""));
        db.close();

        clearEditTexts();

        read();
    }

    private void read() {

        List<User> users = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        users.addAll(databaseHelper.selectAllUsers(db));
        db.close();

        StringBuilder builder = new StringBuilder();

        for (User user : users) {
            builder.append(" Id: ");
            builder.append(user.getId());

            builder.append(" Imię: ");
            builder.append(user.getName());

            builder.append(" Nazwisko: ");
            builder.append(user.getSurname());

            builder.append(" Zawód: ");
            builder.append(user.getProfession());

            builder.append("\n");
        }

        textViewContent.setText(builder.toString());
    }

    private void clearEditTexts() {
        editTextId.setText("");
        editTextName.setText("");
        editTextSurname.setText("");
        editTextProfession.setText("");
    }

    private void hideErrors() {
        editTextId.setError(null);
        editTextName.setError(null);
        editTextSurname.setError(null);
        editTextProfession.setError(null);
    }
}
