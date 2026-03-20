package model;

import java.util.List;
import java.util.ArrayList;
import adapter.IWeatherAdapter;
import adapter.NWSWeatherAdapter;
import service.IWeatherService;
import service.RealWeatherService;
import service.WeatherProxy;

public class WeatherModel {
    private List<WeatherData> forecast; // utilizing the decoupled WeatherData model
    private String currentLocationName = "Chicago, IL";
    private String gridRegion = "LOT";
    private int gridX = 77;
    private int gridY = 70;
    
    private IWeatherAdapter adapter; // retain an instance of the adapter layer

    public WeatherModel() {
        // instantiate the architecture map
        IWeatherService real = new RealWeatherService();
        IWeatherService proxy = new WeatherProxy(real);
        adapter = new NWSWeatherAdapter(proxy);
    }

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
            // Retrieve abstracted data completely seamlessly through the caching adapter proxy stack
            List<WeatherData> loaded = adapter.getWeatherData(gridRegion, gridX, gridY);
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
    public WeatherData getToday() {
        if (forecast != null && !forecast.isEmpty()) {
            return forecast.get(0);
        }
        return null;
    }

    // get 3-day forecast
    public List<WeatherData> getThreeDayForecast() {
        List<WeatherData> threeDays = new ArrayList<>();
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
