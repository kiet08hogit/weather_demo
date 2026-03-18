package util;

import javafx.scene.text.Text;

// Simple util to map short weather descriptions onto text-based emoji icons
public class ImageUtil {
    public static Text getIconForWeather(String shortForecast, boolean isDay) {
        String iconChar = "☁"; // default cloud
        
        if (shortForecast == null) {
            return new Text(iconChar);
        }

        String lowerCaseForecast = shortForecast.toLowerCase();
        if (lowerCaseForecast.contains("sunny") || lowerCaseForecast.contains("clear")) {
            iconChar = isDay ? "☀" : "🌙";
        } else if (lowerCaseForecast.contains("rain") || lowerCaseForecast.contains("showers")) {
            iconChar = "\uD83C\uDF27"; // rain cloud
        } else if (lowerCaseForecast.contains("snow")) {
            iconChar = "❄";
        } else if (lowerCaseForecast.contains("thunder") || lowerCaseForecast.contains("storm")) {
            iconChar = "⛈";
        } else if (lowerCaseForecast.contains("cloud")) {
            if (lowerCaseForecast.contains("partly")) {
                iconChar = "⛅";
            } else {
                iconChar = "☁";
            }
        }

        Text iconText = new Text(iconChar);
        iconText.getStyleClass().add("weather-icon");
        return iconText;
    }
}
