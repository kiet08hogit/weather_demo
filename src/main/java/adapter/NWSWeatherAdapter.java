package adapter;

import java.util.ArrayList;
import java.util.List;
import model.WeatherData;
import weather.Period;
import service.IWeatherService;

// STEP 7: Implement the NWS specific adapter
public class NWSWeatherAdapter implements IWeatherAdapter {
    private IWeatherService service;

    public NWSWeatherAdapter(IWeatherService service) {
        this.service = service;
    }

    @Override
    public List<WeatherData> getWeatherData(String region, int gridx, int gridy) {
        // Fetch raw API data from the specified service dependency
        List<Period> periods = service.getForecast(region, gridx, gridy);
        List<WeatherData> weatherDataList = new ArrayList<>();
        
        if (periods != null) {
            // Convert each remote API Period object into a clean local WeatherData object
            for (Period p : periods) {
                weatherDataList.add(new WeatherData(
                    p.name,
                    p.isDaytime,
                    p.temperature,
                    p.shortForecast,
                    p.windSpeed,
                    p.windDirection
                ));
            }
        }
        return weatherDataList;
    }
}
