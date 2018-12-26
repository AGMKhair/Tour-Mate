package com.xcoders.tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context  = context;
    }

    public int[] slide_images =
            {
                    R.drawable.travels,
                    R.drawable.expenses,
                    R.drawable.memorable,
                    R.drawable.more,

            };

    public String[] slide_headings =
            {
                    "Travel Events",
                    "Expenses",
                    "Memorable Places",
                    "& More..."
            };

    public String[] slide_descs =
            {
                  "All your travel needs centralized",
                  "Keep & track record travel related expenses",
                  "Store all your memorable place into cloud",
                  "Found nearby, get weather forcast, book tickets online"
            };







    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==(RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = view.findViewById(R.id.imageId);
        TextView slideHeading = view.findViewById(R.id.headingId);
        TextView slideDescription = view.findViewById(R.id.detailsId);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
        container.removeView((RelativeLayout) object);
    }
}
