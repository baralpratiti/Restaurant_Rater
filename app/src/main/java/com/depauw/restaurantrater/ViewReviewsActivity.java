package com.depauw.restaurantrater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.depauw.restaurantrater.databinding.ActivityViewReviewsBinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewReviewsActivity extends AppCompatActivity {

    private ActivityViewReviewsBinding binding;
    private ArrayList<Review> myArrayList = new ArrayList<>();
    public static final int FROM_ADD_REVIEW_ACTIVITY = 1;

    private AdapterView.OnItemClickListener listview_reviews_onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)

        {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(ViewReviewsActivity.this);
            Review review = (Review) adapterView.getItemAtPosition(i);
            String date = review.getDate();
            String time = review.getTime();
            myBuilder.setTitle("Review Details")
                     .setMessage("This review was created on " + date + " at " + time);
            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        }

    };

    private View.OnClickListener button_add_review_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(ViewReviewsActivity.this, AddReviewActivity.class);
            startActivityForResult(myIntent, FROM_ADD_REVIEW_ACTIVITY);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       generateReviews();

    }

    public void generateReviews()
    {
        File myFile = new File(getFilesDir(),"reviews.csv");
        try(Scanner myScanner = new Scanner(myFile)) {
            while(myScanner.hasNextLine()){

                String line = myScanner.nextLine();
                String[] myArray = line.split(",",0);
                Review myReview = new Review(myArray[0],myArray[1],myArray[2],myArray[3],Integer.parseInt(myArray[4]),Integer.parseInt(myArray[5]));
                myArrayList.add(myReview);
                CustomAdapter myAdapter = new CustomAdapter(this, myArrayList);
                binding.listviewReviews.setAdapter(myAdapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        generateReviews();
        binding.listviewReviews.setOnItemClickListener(listview_reviews_onItemClickListener);
        binding.buttonAddReview.setOnClickListener(button_add_review_onClickListener);
    }
}