package service;

import java.util.List;
import weather.Period;
import weather.WeatherAPI;

// service implementation
public class RealWeatherService implements IWeatherService{
    @Override
    public List<Period> getForecast(String region, int gridx, int gridy){
        // delegates directly to the existing WeatherAPI
        return WeatherAPI.getForecast(region, gridx, gridy);
    }
}
