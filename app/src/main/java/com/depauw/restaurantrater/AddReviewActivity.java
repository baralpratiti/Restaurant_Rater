package com.depauw.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.depauw.restaurantrater.databinding.ActivityAddReviewBinding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class AddReviewActivity extends AppCompatActivity {

    private ActivityAddReviewBinding binding;

    private View.OnClickListener button_add_review_onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String restaurantName = binding.edittextRestaurantName.getText().toString();
            String date = binding.edittextReviewDate.getText().toString();
            String time = binding.edittextReviewTime.getText().toString();
            int meal = binding.radiogroupMeals.getCheckedRadioButtonId();

            String mealUsed;

            switch (meal)
            {
                case R.id.radio_breakfast:
                {
                    mealUsed = "Breakfast";
                    break;
                }
                case R.id.radio_lunch:
                {
                    mealUsed = "Lunch";
                    break;
                }
                case R.id.radio_dinner:
                {
                    mealUsed = "Dinner";
                    break;
                }
                default:
                    throw new IllegalStateException("Invalid: " + meal);
            }

            String rating = String.valueOf(binding.seekbarRating.getProgress());

            String favorite;
            if(binding.checkboxFavorite.isChecked())
            {
                favorite = String.valueOf(1);
            }
            else
            {
                favorite = String.valueOf(0);
            }

            File myFile = new File(getFilesDir(),"reviews.csv");
            try(FileWriter myWriter = new FileWriter(myFile,true))
            {
                myWriter.write(restaurantName+","+date+","+time+","+mealUsed+","+rating+","+favorite+System.getProperty( "line.separator" ));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    };

    private TimePickerDialog.OnTimeSetListener timepicker_review_timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute)
        {
            String dayTime;
            String currTime;
            if (hour >= 0 && hour < 12)
            {
                dayTime = "AM";
            }
            else
            {
                dayTime = "PM";
            }
            String mins;
            if(minute < 10)
            {
                mins = "0" + minute;
            } else
            {
                mins = String.valueOf(minute);
            }
            if(hour > 12)
            {
                hour = hour - 12;
            }
            if(hour != 0)
            {
                currTime = String.valueOf(hour) + ":" + mins + " " + dayTime;
            }
            else
            {
                currTime = "0" + String.valueOf(hour) + ":" + mins + " " + dayTime;
            }
            binding.edittextReviewTime.setText(currTime);
        }
    };

    private DatePickerDialog.OnDateSetListener date_review_dateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
        {
            String result = String.valueOf(i1+1) + "/"+String.valueOf(i2) + "/" + String.valueOf(i);
            binding.edittextReviewDate.setText(result);
        }
    };


    private View.OnClickListener editText_review_time_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar myCalender = Calendar.getInstance();
            TimePickerDialog time = new TimePickerDialog(AddReviewActivity.this,timepicker_review_timeSetListener,myCalender.get(Calendar.HOUR_OF_DAY),myCalender.get(Calendar.MINUTE),false);
            time.show();

        }
    };

    private View.OnClickListener editText_review_date_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar myCalender = Calendar.getInstance();
            DatePickerDialog picker = new DatePickerDialog(AddReviewActivity.this,date_review_dateSetListener,myCalender.get(Calendar.YEAR),
                    myCalender.get(Calendar.MONTH),
                    myCalender.get(Calendar.DAY_OF_MONTH));
            picker.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent myIntent = getIntent();
        binding.edittextReviewDate.setOnClickListener(editText_review_date_clickListener);
        binding.edittextReviewTime.setOnClickListener(editText_review_time_clickListener);
        binding.buttonAddReview.setOnClickListener(button_add_review_onClickListener );
    }
}
