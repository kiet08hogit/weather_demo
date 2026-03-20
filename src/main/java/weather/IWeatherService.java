package weather;

import java.util.List;

// STEP 3: Service Interface
public interface IWeatherService {
    List<Period> getForecast(String region, int gridx, int gridy);
}
