package com.xcoders.tourmate.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xcoders.tourmate.R;

public class AddEventBSFragment extends BottomSheetDialogFragment {

    public static BottomSheetDialogFragment newInstance() {
        return new BottomSheetDialogFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_event_dialog, container,
                false);

        // get the views and attach the listener

        return view;

    }
}
