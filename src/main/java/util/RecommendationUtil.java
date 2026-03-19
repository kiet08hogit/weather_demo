package util;

/* Utility class for weather recommendations */
public class RecommendationUtil {

    // Get clothing recommendation based on temperature and weather
    public static String getClothing(int temp, String weather) {
        String w = weather != null ? weather.toLowerCase() : "";
        if (w.contains("rain") || w.contains("showers")) {
            return "Rain jacket and waterproof shoes";
        } else if (w.contains("snow")) {
            return "Heavy winter coat, gloves, and boots";
        } else if (temp < 40) {
            return "Warm coat, scarf, and beanie";
        } else if (temp < 60) {
            return "Light jacket or sweater and long pants";
        } else if (temp < 75) {
            return "T-shirt and light pants";
        } else {
            return "Shorts, t-shirt, and sunglasses";
        }
    }

    // Get activity recommendation based on temperature and weather
    public static String getActivity(int temp, String weather) {
        String w = weather != null ? weather.toLowerCase() : "";
        if (w.contains("rain") || w.contains("showers") || w.contains("storm")) {
            return "Perfect day for an indoor museum or movie";
        } else if (w.contains("snow")) {
            return "Build a snowman or drink hot cocoa inside";
        } else if (temp < 40) {
            return "Visit a cozy cafe or read a book indoors";
        } else if (temp > 85) {
            return "Great day for the pool or staying in the AC";
        } else if (w.contains("sunny") || w.contains("clear")) {
            return "Excellent weather for a nice walk or run!";
        } else {
            return "Good day for a casual outdoor stroll";
        }
    }
}
