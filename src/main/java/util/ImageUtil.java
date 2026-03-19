package util;

import org.kordamp.ikonli.javafx.FontIcon;

// Simple util to map short weather descriptions onto FontAwesome icons
public class ImageUtil {
    public static FontIcon getIconForWeather(String shortForecast, boolean isDay) {
        String iconLiteral = "fas-cloud"; // default cloud
        
        if (shortForecast != null) {
            String lowerCaseForecast = shortForecast.toLowerCase();
            if (lowerCaseForecast.contains("sunny") || lowerCaseForecast.contains("clear")) {
                iconLiteral = isDay ? "fas-sun" : "fas-moon";
            } else if (lowerCaseForecast.contains("rain") || lowerCaseForecast.contains("showers")) {
                iconLiteral = "fas-cloud-rain";
            } else if (lowerCaseForecast.contains("snow")) {
                iconLiteral = "fas-snowflake";
            } else if (lowerCaseForecast.contains("thunder") || lowerCaseForecast.contains("storm")) {
                iconLiteral = "fas-bolt";
            } else if (lowerCaseForecast.contains("cloud")) {
                if (lowerCaseForecast.contains("partly")) {
                    iconLiteral = isDay ? "fas-cloud-sun" : "fas-cloud-moon";
                } else {
                    iconLiteral = "fas-cloud";
                }
            }
        }

        FontIcon icon = new FontIcon(iconLiteral);
        icon.getStyleClass().add("weather-icon");
        return icon;
    }
}
