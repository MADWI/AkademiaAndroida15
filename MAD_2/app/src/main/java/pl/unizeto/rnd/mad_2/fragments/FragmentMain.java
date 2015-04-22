package pl.unizeto.rnd.mad_2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.unizeto.rnd.mad_2.R;
import pl.unizeto.rnd.mad_2.utils.Utils;

public class FragmentMain extends Fragment implements View.OnClickListener {

    private Button btnFragA;
    private Button btnFragB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnFragA = (Button) view.findViewById(R.id.btnFragA);
        btnFragB = (Button) view.findViewById(R.id.btnFragB);

        btnFragA.setOnClickListener(this);
        btnFragB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnFragA:
                Utils.fragmentTransactionSetup(new FragmentA(), getFragmentManager(), FragmentA.class.getSimpleName());
                break;

            case R.id.btnFragB:
                Utils.fragmentTransactionSetup(new FragmentB(), getFragmentManager(), FragmentB.class.getSimpleName());
                break;
        }
    }
}
