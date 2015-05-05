package pl.edu.zut.wi.mad_3.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.edu.zut.wi.mad_3.R;

/**
 * Created by rafalmielnik on 21.04.15.
 */
public class FragmentMain extends Fragment implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;

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
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.button1:
            fragment = new FragmentA();
                break;

            case R.id.button2:
                fragment = new FragmentB();
                break;

            case R.id.button3:
                fragment = new FragmentC();
                break;

            default:
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction().replace(R.id.container_fragments, fragment).commit();
        }

    }

}
