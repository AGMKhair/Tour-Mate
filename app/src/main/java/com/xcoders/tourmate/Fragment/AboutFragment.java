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
public class AboutFragment extends Fragment
{


    public AboutFragment()
    {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("More");
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }

}
