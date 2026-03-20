package util;

import org.kordamp.ikonli.javafx.FontIcon;

// utility class to get icon for weather
public class ImageUtil {
    public static FontIcon getIconForWeather(String shortForecast, boolean isDay){
        // default 
        String iconLiteral = "fas-cloud"; 
        if (shortForecast != null){
            String lowerCaseForecast = shortForecast.toLowerCase();
            // sunny/clear
            if (lowerCaseForecast.contains("sunny") || lowerCaseForecast.contains("clear")){
                if(isDay){
                    iconLiteral = "fas-sun";
                } 
                else{
                    iconLiteral = "fas-moon";
                }
            } 
            // rain
            else if (lowerCaseForecast.contains("rain") || lowerCaseForecast.contains("showers")){
                iconLiteral = "fas-cloud-rain";
            } 
            // snow
            else if (lowerCaseForecast.contains("snow")){
                iconLiteral = "fas-snowflake";
            } 
            // thunder/storm
            else if (lowerCaseForecast.contains("thunder") || lowerCaseForecast.contains("storm")){
                iconLiteral = "fas-bolt";
            } 
            // cloud
            else if (lowerCaseForecast.contains("cloud")){
                // partly cloud
                if (lowerCaseForecast.contains("partly")){
                    if(isDay){
                        iconLiteral = "fas-cloud-sun";
                    } 
                    else{
                        iconLiteral = "fas-cloud-moon";
                    }   
                } 
                // cloud
                else {
                    iconLiteral = "fas-cloud";
                }
            }
        }
        // set color of icon
        FontIcon icon = new FontIcon(iconLiteral);
        icon.getStyleClass().add("weather-icon");
        return icon;
    }

    //Get background img base on weather and day/night
    public static String getBackgroundUrl(String shortForecast, boolean isDay){
        if (shortForecast != null){
            String lower = shortForecast.toLowerCase();
            // snow-day/night
            if (lower.contains("snow")){
                if(isDay){
                    return "/background/snowday.jpg";
                }
                else{
                    return "/background/snownight.jpg";
                }
            } 
            // rain
            else if (lower.contains("rain") || lower.contains("showers") || lower.contains("storm") || lower.contains("thunder")){
                return "/background/rainy.jpg";
            } 
            // sunny-clear day / clear night
            else if (lower.contains("sunny") || lower.contains("clear")){
                if(isDay){
                    return "/background/sunny.jpg";
                } 
                else{
                    return "/background/night.jpg";
                }
            }
        }
        // default-cloudy-day/night
        if(isDay){
            return "/background/cloudyday.jpg";
        } else{
            return "/background/night.jpg";
        }
    }

    // set up text color for light/dark theme
    public static boolean isDarkBackground(String shortForecast, boolean isDay){
        // night-dark theme -> use white text
        if (!isDay) return true;
        
        // day-light theme -> use black text 
        if(shortForecast != null){
            String lower = shortForecast.toLowerCase();
            // mirror the exact hierarchy of getBackgroundUrl
            if(lower.contains("snow")){
                return false; 
            } 
            else if(lower.contains("rain") || lower.contains("showers") || lower.contains("storm") || lower.contains("thunder")){
                return true; 
            } 
            else if(lower.contains("sunny") || lower.contains("clear")) {
                return true; 
            }
        }
        return false; 
    }
}
