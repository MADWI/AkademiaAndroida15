package pl.edu.zut.wi.mad_3.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.zut.wi.mad_3.R;
import pl.edu.zut.wi.mad_3.utils.JSONParser;

/**
 * Created by rafalmielnik on 21.04.15.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;

    private Button buttonJSONArray;
    private Button buttonJSONObj;

    private static final String URL1 = "http://dglinski.cba.pl/akademia_androida1.php";
    private static final String URL2 = "http://dglinski.cba.pl/akademia_androida2.php";

    private static final String RESPONSE = "response";
    private static final String URL = "url";

    private boolean isButtonAsArrayClicked = false;

    private String[] urlsTable = new String[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(this);

        buttonJSONArray = (Button) view.findViewById(R.id.jsonAsArrayButton);
        buttonJSONArray.setOnClickListener(this);

        buttonJSONObj = (Button) view.findViewById(R.id.jsonAsObjButton);
        buttonJSONObj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (v.getId()) {
            case R.id.button1:
                bundle.putString(URL, urlsTable[0]);
                fragment = new FragmentA();
                fragment.setArguments(bundle);
                break;

            case R.id.button2:
                bundle.putString(URL, urlsTable[1]);
                fragment = new FragmentB();
                fragment.setArguments(bundle);
                break;

            case R.id.button3:
                bundle.putString(URL, urlsTable[2]);
                fragment = new FragmentC();
                fragment.setArguments(bundle);
                break;

            case R.id.jsonAsArrayButton:
                isButtonAsArrayClicked = true;
                new GetJSONAsync().execute(URL1);
                break;

            case R.id.jsonAsObjButton:
                isButtonAsArrayClicked = false;
                new GetJSONAsync().execute(URL2);
                break;

            default:
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.container_fragments, fragment).commit();
        }

    }

    private class GetJSONAsync extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(getActivity(), "Parsing JSON", "Please wait...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser parser = new JSONParser();
            return parser.getJSONFromUrl(params[0]);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            try {
                if (isButtonAsArrayClicked) {
                    //parsowanie adresu URL1
                    JSONArray array = jsonObject.getJSONArray(RESPONSE);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        urlsTable[i] = object.getString(URL);
                    }
                } else {
                    //parsowanie adresu URL2
                    urlsTable[0] = jsonObject.getString("url1");
                    urlsTable[1] = jsonObject.getString("url2");
                    urlsTable[2] = jsonObject.getString("url3");
                }
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);

                dialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
