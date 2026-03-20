package service;

import java.util.List;
import weather.Period;

// STEP 3: Service Interface
public interface IWeatherService {
    List<Period> getForecast(String region, int gridx, int gridy);
}
