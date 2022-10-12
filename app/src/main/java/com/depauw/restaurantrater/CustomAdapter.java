package com.depauw.restaurantrater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<Review> reviews;

    public CustomAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getCount()
    {
        return reviews.size();
    }

    @Override
    public Object getItem(int position)
    {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.listview, viewGroup, false);
        }
        Review thisReview = reviews.get(position);

        RatingBar restaurantRating = view.findViewById(R.id.ratingbar_restaurant_rating);
        TextView name = view.findViewById(R.id.textView_name);
        RadioButton breakfast = view.findViewById(R.id.radio_breakfast);
        RadioButton lunch = view.findViewById(R.id.radio_lunch);
        RadioButton dinner = view.findViewById(R.id.radio_dinner);
        ProgressBar rating = view.findViewById(R.id.progressbar_rating);

        restaurantRating.setRating(thisReview.getIsFavorite());

        name.setText(thisReview.getRestaurantName());

        if (thisReview.getMeal().equals("Breakfast"))
        {
            breakfast.setChecked(true);
            lunch.setChecked(false);
            dinner.setChecked(false);
        }
        if (thisReview.getMeal().equals("Lunch"))
        {
            breakfast.setChecked(false);
            lunch.setChecked(true);
            dinner.setChecked(false);
        }
        if (thisReview.getMeal().equals("Dinner"))
        {
            breakfast.setChecked(false);
            lunch.setChecked(false);
            dinner.setChecked(true);
        }
        rating.setProgress(thisReview.getRating());

        return view;

    }
}

