package pl.edu.zut.wi.mad_3.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.URL;

import pl.edu.zut.wi.mad_3.R;

/**
 * Created by rafalmielnik on 21.04.15.
 */
public class FragmentA extends Fragment {

    private ImageView imageView;
    private String urlToImage;
    private Bundle bundle;
    private static final String URL = "url";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        imageView = (ImageView) view.findViewById(R.id.imageView);
        bundle = getArguments();
        urlToImage = bundle.getString(URL);
        new DownloadImagesTask().execute(urlToImage);
    }

    private class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "Download image", "Please wait...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return download_Image(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            progressDialog.dismiss();
            imageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {

            Bitmap bmp = null;
            try {
                URL imageURL = new URL(url);
                bmp = BitmapFactory.decodeStream(imageURL.openStream());
                if (null != bmp)
                    return bmp;

            } catch (Exception e) {
            }
            return bmp;
        }
    }
}
