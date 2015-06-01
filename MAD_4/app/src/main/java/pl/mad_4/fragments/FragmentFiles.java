package pl.mad_4.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import pl.mad_4.R;
import pl.mad_4.utils.Utils;

/**
 * Created by rafalmielnik on 29.05.15.
 */
public class FragmentFiles extends Fragment implements View.OnClickListener {

    private static final String FILE_NAME = "example.txt";

    private EditText editText;

    private Button buttonSave;
    private Button buttonRead;

    private TextView textViewFileContent;

    public static Fragment newInstance() {
        FragmentFiles fragment = new FragmentFiles();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        editText = (EditText) view.findViewById(R.id.edit_text);

        buttonSave = (Button) view.findViewById(R.id.button_save_file);
        buttonSave.setOnClickListener(this);
        buttonRead = (Button) view.findViewById(R.id.button_read_file);
        buttonRead.setOnClickListener(this);

        textViewFileContent = (TextView) view.findViewById(R.id.text_view_file_content);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_save_file:
                saveFile();
                break;
            case R.id.button_read_file:
                readFile();
                break;
        }

    }

    private void saveFile() {

        if (editText.getText().toString().equals("")) {
            editText.setError(getString(R.string.required_filed));
            return;
        }

        String filePath = Environment.getExternalStorageDirectory() + "/" + FILE_NAME;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileContent = editText.getText().toString();
        Utils.writeToFile(fileContent, filePath);

        editText.setText("");
    }

    private void readFile() {
        String sdPath = Environment.getExternalStorageDirectory().getPath();
        String filePath = sdPath + "/" + FILE_NAME;

        File file = new File(filePath);
        if (file.exists() && file.canRead()) {
            String fileContent = Utils.getFileContents(filePath);
            if (fileContent != null) {
                textViewFileContent.setText(fileContent);
            }
        } else {
            textViewFileContent.setText(getString(R.string.not_exist));
        }

    }

}
