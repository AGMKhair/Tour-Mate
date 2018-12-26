package com.xcoders.tourmate.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xcoders.tourmate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByFragment extends Fragment {


    public NearByFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Search Near By Places");

        View view= inflater.inflate(R.layout.fragment_near_by, container, false);
        return view;
    }

}
