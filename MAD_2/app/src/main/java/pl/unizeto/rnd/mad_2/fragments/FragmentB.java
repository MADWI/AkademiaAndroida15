package pl.unizeto.rnd.mad_2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.unizeto.rnd.mad_2.R;
import pl.unizeto.rnd.mad_2.utils.Utils;

public class FragmentB extends Fragment implements View.OnClickListener{

    private Button btnFragC;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_b_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        btnFragC = (Button) view.findViewById(R.id.btnFragC);
        btnFragC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnFragC:
                Utils.fragmentTransactionSetup(new FragmentC(), getFragmentManager(), FragmentC.class.getSimpleName());
                break;
        }
    }
}
