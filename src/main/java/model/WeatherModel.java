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

    public void setLocation(String name, String region, int x, int y) {
        this.currentLocationName = name;
        this.gridRegion = region;
        this.gridX = x;
        this.gridY = y;
    }

    public String getCurrentLocationName() {
        return currentLocationName;
    }

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

    public Period getToday() {
        if (forecast != null && !forecast.isEmpty()) {
            return forecast.get(0);
        }
        return null;
    }

    public ArrayList<Period> getThreeDayForecast() {
        ArrayList<Period> threeDays = new ArrayList<>();
        if (forecast != null) {
            // we want the first 6 periods (Day & Night for 3 days)
            int limit = Math.min(forecast.size(), 6);
            for (int i = 0; i < limit; i++) {
                threeDays.add(forecast.get(i));
            }
        }
        return threeDays;
    }
}
