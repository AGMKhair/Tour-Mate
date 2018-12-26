package com.xcoders.tourmate.Fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xcoders.tourmate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {

    private WebView webView;
    private ProgressBar progBar;
    private TextView infoView;
    private Button tryButton;

    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        webView = view.findViewById(R.id.webViewId);
        progBar = view.findViewById(R.id.progBarId);
        infoView = view.findViewById(R.id.infoTv);
        tryButton = view.findViewById(R.id.tryBtn);

        if (isNetConnected()) {
            webView.loadUrl("https://www.shohoz.com/index.php");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDisplayZoomControls(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setWebViewClient(new dKodersView());
        } else {

            infoView.setVisibility(View.VISIBLE);
            tryButton.setVisibility(View.VISIBLE);
            progBar.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
        }

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(TicketFragment.this).attach(TicketFragment.this).commit();
            }
        });

        return view;
    }

    public class dKodersView extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            progBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
