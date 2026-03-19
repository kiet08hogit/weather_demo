package controller;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import model.WeatherModel;
import util.ImageUtil;
import weather.Period;

import java.net.URL;
import java.util.ResourceBundle;

public class TodayController implements Initializable {
    private WeatherModel model;
    private SceneManager sceneManager;
    private ForecastController forecastController;

    @FXML private Text tempTxt;
    @FXML private Text shortForecastTxt;
    @FXML private Text windSpeedTxt;
    @FXML private Text windDirTxt;
    @FXML private FontIcon centerIconTxt;
    @FXML private Text locationTxt;

    public void setForecastController(ForecastController fc) {
        this.forecastController = fc;
    }

    private static final java.util.Map<String, String> CITY_COORDS = java.util.Map.ofEntries(
        java.util.Map.entry("new york", "40.71,-74.00"),
        java.util.Map.entry("los angeles", "34.05,-118.24"),
        java.util.Map.entry("chicago", "41.87,-87.62"),
        java.util.Map.entry("houston", "29.76,-95.36"),
        java.util.Map.entry("phoenix", "33.44,-112.07"),
        java.util.Map.entry("philadelphia", "39.95,-75.16"),
        java.util.Map.entry("san antonio", "29.42,-98.49"),
        java.util.Map.entry("san diego", "32.71,-117.16"),
        java.util.Map.entry("dallas", "32.77,-96.79"),
        java.util.Map.entry("san jose", "37.33,-121.88"),
        java.util.Map.entry("austin", "30.26,-97.74"),
        java.util.Map.entry("jacksonville", "30.33,-81.65"),
        java.util.Map.entry("san francisco", "37.77,-122.41"),
        java.util.Map.entry("sf", "37.77,-122.41"),
        java.util.Map.entry("columbus", "39.96,-82.99"),
        java.util.Map.entry("fort worth", "32.75,-97.33"),
        java.util.Map.entry("indianapolis", "39.76,-86.15"),
        java.util.Map.entry("charlotte", "35.22,-80.84"),
        java.util.Map.entry("seattle", "47.60,-122.33"),
        java.util.Map.entry("denver", "39.73,-104.99"),
        java.util.Map.entry("dc", "38.90,-77.03"),
        java.util.Map.entry("washington dc", "38.90,-77.03"),
        java.util.Map.entry("boston", "42.36,-71.05"),
        java.util.Map.entry("miami", "25.76,-80.19"),
        java.util.Map.entry("las vegas", "36.17,-115.13"),
        java.util.Map.entry("atlanta", "33.74,-84.38")
    );

    @FXML
    private void handleAddLocation() {
        javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog("New York");
        dialog.setTitle("Add Another Location");
        dialog.setHeaderText("Enter City Name");
        dialog.setContentText("City:");

        java.util.Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String cityNameInput = result.get().trim().toLowerCase();
            String coordsStr = CITY_COORDS.get(cityNameInput);
            
            if (coordsStr == null) {
                // Try geocoding via Nominatim
                coordsStr = geocodeCity(result.get().trim());
            }

            if (coordsStr != null) {
                try {
                    String[] latLon = coordsStr.split(",");
                    String lat = latLon[0];
                    String lon = latLon[1];
                    String pointsUrl = "https://api.weather.gov/points/" + lat + "," + lon;
                    java.net.http.HttpResponse<String> response = java.net.http.HttpClient.newHttpClient().send(
                        java.net.http.HttpRequest.newBuilder().uri(java.net.URI.create(pointsUrl)).header("User-Agent", "WeatherDemoApp").build(),
                        java.net.http.HttpResponse.BodyHandlers.ofString()
                    );
                    
                    if (response.statusCode() == 200) {
                        com.fasterxml.jackson.databind.JsonNode rootNode = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.body());
                        com.fasterxml.jackson.databind.JsonNode props = rootNode.path("properties");
                        String gridId = props.path("gridId").asText();
                        int gridX = props.path("gridX").asInt();
                        int gridY = props.path("gridY").asInt();
                        String cityName = props.path("relativeLocation").path("properties").path("city").asText();
                        String stateName = props.path("relativeLocation").path("properties").path("state").asText();
                        
                        // Use the input name if it's a known city, otherwise use NWS name
                        String finalName = cityNameInput.substring(0, 1).toUpperCase() + cityNameInput.substring(1) + ", " + stateName;
                        if (cityNameInput.contains(" ")) {
                             // Handle multi-word names like "San Francisco"
                             String[] words = cityNameInput.split(" ");
                             finalName = "";
                             for (String w : words) finalName += w.substring(0, 1).toUpperCase() + w.substring(1) + " ";
                             finalName = finalName.trim() + ", " + stateName;
                        }

                        updateLocation(finalName, gridId, gridX, gridY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String geocodeCity(String city) {
        try {
            String url = "https://nominatim.openstreetmap.org/search?q=" + java.net.URLEncoder.encode(city, "UTF-8") + "&format=json&limit=1";
            java.net.http.HttpResponse<String> response = java.net.http.HttpClient.newHttpClient().send(
                java.net.http.HttpRequest.newBuilder().uri(java.net.URI.create(url)).header("User-Agent", "WeatherDemoApp").build(),
                java.net.http.HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                com.fasterxml.jackson.databind.JsonNode rootNode = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.body());
                if (rootNode.isArray() && rootNode.size() > 0) {
                    String lat = rootNode.get(0).path("lat").asText();
                    String lon = rootNode.get(0).path("lon").asText();
                    return lat + "," + lon;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML 
    private void handleSwitchLocationChicago() {
        updateLocation("Chicago, IL", "LOT", 77, 70);
    }

    @FXML 
    private void handleSwitchLocationPortland() {
        updateLocation("Portland, ME", "GYX", 52, 75);
    }

    @FXML 
    private void handleSwitchLocationLasVegas() {
        updateLocation("Las Vegas, NV", "VEF", 119, 93);
    }

    @FXML 
    private void handleSwitchLocationBoston() {
        updateLocation("Boston, MA", "BOX", 71, 90);
    }

    private void updateLocation(String name, String region, int x, int y) {
        model.setLocation(name, region, x, y);
        locationTxt.setText(name);
        if (model.fetchForecast()) {
            updateView(model.getToday());
            if (forecastController != null) {
                forecastController.refresh();
            }
        }
    }

    @FXML private Text outfitTxt;
    @FXML private Text activityTxt;

    public void setModel(WeatherModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;

        if (model.fetchForecast()) {
            Period today = model.getToday();
            updateView(today);
        }
    }

    private void updateView(Period todayPeriod) {
        if (todayPeriod == null) return;
        
        tempTxt.setText(String.valueOf(todayPeriod.temperature));
        shortForecastTxt.setText(todayPeriod.shortForecast);
        windSpeedTxt.setText(todayPeriod.windSpeed);
        windDirTxt.setText(todayPeriod.windDirection);
        
        FontIcon icon = ImageUtil.getIconForWeather(todayPeriod.shortForecast, todayPeriod.isDaytime);
        centerIconTxt.setIconLiteral(icon.getIconLiteral());

        outfitTxt.setText(util.RecommendationUtil.getClothing(todayPeriod.temperature, todayPeriod.shortForecast));
        activityTxt.setText(util.RecommendationUtil.getActivity(todayPeriod.temperature, todayPeriod.shortForecast));
    }

    @FXML
    private void switchToForecast() {
        if (sceneManager != null) {
            sceneManager.switchScene("Forecast");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
