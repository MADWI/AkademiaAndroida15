package pl.mad_4.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import pl.mad_4.R;

public class FragmentSharedPreferences extends Fragment implements View.OnClickListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText editTextKey;
    private EditText editTextValue;

    private Button buttonSave;
    private Button buttonReadAll;

    private TextView textViewContent;

    public static Fragment newInstance() {
        FragmentSharedPreferences fragment = new FragmentSharedPreferences();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared_preferences, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        editTextKey = (EditText) view.findViewById(R.id.edit_text_key);
        editTextValue = (EditText) view.findViewById(R.id.edit_text_value);

        buttonSave = (Button) view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);
        buttonReadAll = (Button) view.findViewById(R.id.button_read_all);
        buttonReadAll.setOnClickListener(this);

        textViewContent = (TextView) view.findViewById(R.id.text_view_content);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = pref.edit();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_save:
                saveValue();
                break;

            case R.id.button_read_all:
                readAllValues();
                break;
        }
    }

    private void saveValue() {

        if (editTextKey.getText().toString().equals("")) {
            editTextKey.setError(getString(R.string.required_filed));
            return;
        }

        if (editTextValue.getText().toString().equals("")) {
            editTextValue.setError(getString(R.string.required_filed));
            return;
        }

        String key = editTextKey.getText().toString();
        String value = editTextValue.getText().toString();

        editor.putString(key, value);
        editor.commit();

    }


    private void readAllValues() {
        StringBuilder builder = new StringBuilder();

        Map<String, ?> values = pref.getAll();
        for (Map.Entry<String, ?> entry : values.entrySet()) {

            String key = entry.getKey();
            String value = (String) entry.getValue();

            builder.append(String.format("%s: %s\n", key, value));
        }

        textViewContent.setText(builder.toString());
    }
}
