package pl.unizeto.rnd.mad_2.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import pl.unizeto.rnd.mad_2.R;

public class Utils {
    public static void fragmentTransactionSetup(Fragment fragment,
                                                FragmentManager fragmentManager,
                                                String tag) {

        fragmentManager.executePendingTransactions();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        fragmentTransaction.replace(R.id.container, fragment, tag);

        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
}
