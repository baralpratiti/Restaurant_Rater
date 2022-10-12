package com.depauw.restaurantrater;

public class Review {
    private String restaurantName;
    private String date;
    private String time;
    private String meal;
    private int rating;
    private int isFavorite;


    public Review (String restaurantName, String date, String time, String meal, int rating, int isFavorite)
    {
        this.restaurantName = restaurantName;
        this.date = date;
        this.time = time;
        this.meal = meal;
        this.rating = rating;
        this.isFavorite = isFavorite;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getMeal()
    {
        return meal;
    }

    public int getRating()
    {
        return rating;
    }

    public int getIsFavorite()
    {
        return isFavorite;
    }

}
