package service;

import java.util.List;
import weather.Period;

// service interface
public interface IWeatherService{
    List<Period> getForecast(String region, int gridx, int gridy);
}
