package service;

import java.util.List;
import weather.Period;
import weather.WeatherAPI;

// STEP 4: Real Service Implementation
public class RealWeatherService implements IWeatherService {
    @Override
    public List<Period> getForecast(String region, int gridx, int gridy) {
        // Delegates directly to the existing WeatherAPI
        return WeatherAPI.getForecast(region, gridx, gridy);
    }
}
