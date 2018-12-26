package com.xcoders.tourmate.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.xcoders.tourmate.Adapter.ViewPagerAdapter;
import com.xcoders.tourmate.Model.Event;
import com.xcoders.tourmate.R;
import com.xcoders.tourmate.Variable.Variable;

import static com.xcoders.tourmate.Variable.Variable.myRef;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragment extends Fragment {

    private TextView nameTV, toDateTV, formDateTV, budgetTV;
    private CardView cardView;
    private View view;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_event_details, container, false);

        nameTV = view.findViewById(R.id.event_destinationNameTVId);
        //nameTV.setText("dsfa");
        toDateTV = view.findViewById(R.id.event_toDateTVId);
        formDateTV = view.findViewById(R.id.event_formDateTVId);
        budgetTV = view.findViewById(R.id.event_budgeTVId);


        tabLayout = view.findViewById(R.id.event_details_tab_id);
        viewPager = view.findViewById(R.id.viewPagerId);

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ExpenseFragment(), "Expenses");
        adapter.addFragment(new MomentFragment(), "Moments");


        viewPager.setAdapter(adapter);
        // tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Datashow();
    }

    private void Datashow() {
        //nameTV.setText("dataSnapshot.getChildren().toString()");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Event event1 = dataSnapshot1.getValue(Event.class);
                    if (Variable.event_id.equals(event1.getEventid())) {


                        nameTV.setText(event1.getName());
                        toDateTV.setText(event1.getTodate());
                        formDateTV.setText(event1.getFormdate());
                        budgetTV.setText(event1.getBudget());
                        Variable.event_id = event1.getEventid();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
