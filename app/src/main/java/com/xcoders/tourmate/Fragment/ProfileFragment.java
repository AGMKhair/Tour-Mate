package com.xcoders.tourmate.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xcoders.tourmate.LoginActivity;
import com.xcoders.tourmate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private TextView userNameTv, userEmailTv;
    private Button btnLogout, btnAbout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userNameTv = view.findViewById(R.id.userNameTvId);
        userEmailTv = view.findViewById(R.id.userEmailTvId);
        btnLogout = view.findViewById(R.id.logoutBtnId);
        btnAbout = view.findViewById(R.id.aboutBtnId);

        String userName = currentUser.getDisplayName().toString();
        String userEmail = currentUser.getEmail().toString();

        userNameTv.setText(userName);
        userEmailTv.setText(userEmail);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                gotoLogin();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutBottomSheet;
                final TextView btnBottomSheet = view.findViewById(R.id.aboutBottomSheet);
                BottomSheetBehavior sheetBehavior;

                View view1 = getLayoutInflater().inflate(R.layout.fragment_about, null);

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(view1);
                dialog.show();
            }
        });

        return view;
    }

    public void logout() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
    }

    private void gotoLogin(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }



}
