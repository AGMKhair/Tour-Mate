package com.xcoders.tourmate.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xcoders.tourmate.R;
import com.xcoders.tourmate.TabAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Online Booking");

        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new TicketFragment(), "Tickets");
        adapter.addFragment(new HotelFragment(), "Hotels");



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_subtitles_black_24dp,
                R.drawable.ic_hotel_black_24dp

        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        return view;
    }

}
