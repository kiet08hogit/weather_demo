package model;

import weather.Period;
import weather.WeatherAPI;
import java.util.ArrayList;

public class WeatherModel {
    private ArrayList<Period> forecast;
    private String currentLocationName = "Chicago, IL";
    private String gridRegion = "LOT";
    private int gridX = 77;
    private int gridY = 70;

    // Set new location for weather model
    public void setLocation(String name, String region, int x, int y) {
        this.currentLocationName = name;
        this.gridRegion = region;
        this.gridX = x;
        this.gridY = y;
    }

    // Get current location name
    public String getCurrentLocationName() {
        return currentLocationName;
    }

    // Call API to get new forecast
    public boolean fetchForecast() {
        try {
            ArrayList<Period> loaded = WeatherAPI.getForecast(gridRegion, gridX, gridY);
            if (loaded != null && !loaded.isEmpty()) {
                this.forecast = loaded;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get today's weather information
    public Period getToday() {
        if (forecast != null && !forecast.isEmpty()) {
            return forecast.get(0);
        }
        return null;
    }

    // get 3-day forecast
    public ArrayList<Period> getThreeDayForecast() {
        ArrayList<Period> threeDays = new ArrayList<>();
        if (forecast != null) {
            // get first 6 weather period
            int limit = Math.min(forecast.size(), 6);
            for (int i = 0; i < limit; i++) {
                threeDays.add(forecast.get(i));
            }
        }
        return threeDays;
    }
}
