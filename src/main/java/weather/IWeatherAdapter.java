package weather;

import java.util.List;
import model.WeatherData;

// STEP 6: Adapter Interface
public interface IWeatherAdapter {
    List<WeatherData> getWeatherData(String region, int gridx, int gridy);
}
