package model;

// STEP 2: Internal UI Data Model
public class WeatherData {
    private String name;
    private boolean isDaytime;
    private int temperature;
    private String shortForecast;
    private String windSpeed;
    private String windDirection;

    public WeatherData(String name, boolean isDaytime, int temperature, String shortForecast, String windSpeed, String windDirection) {
        this.name = name;
        this.isDaytime = isDaytime;
        this.temperature = temperature;
        this.shortForecast = shortForecast;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public String getName() { return name; }
    public boolean isDaytime() { return isDaytime; }
    public int getTemperature() { return temperature; }
    public String getShortForecast() { return shortForecast; }
    public String getWindSpeed() { return windSpeed; }
    public String getWindDirection() { return windDirection; }
}
