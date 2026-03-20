package adapter;

import java.util.List;
import model.WeatherData;

// adapter interface
public interface IWeatherAdapter{
    List<WeatherData> getWeatherData(String region, int gridx, int gridy);
}
