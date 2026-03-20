package controller;

import app.SceneManager;
import component.WeatherCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;
import model.WeatherModel;
import util.ImageUtil;
import model.WeatherData;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// controller for 3-day forecast view
public class ForecastController implements Initializable {
    private WeatherModel model;
    private SceneManager sceneManager;

    @FXML private GridPane grid;
    @FXML private javafx.scene.layout.BorderPane rootPane;

    // assign model and switch screen
    public void setModel(WeatherModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
        refresh();
    }

    // refresh data display
    public void refresh() {
        if (model != null) {
            List<WeatherData> forecast = model.getThreeDayForecast();
            updateView(forecast);
        }
    }

    // update list of weather forecast cards
    private void updateView(List<WeatherData> periods) {
        grid.getChildren().clear(); //remove all existing cards
        if (periods == null || periods.isEmpty()) return;

        // use the first forecast to set the background and theme
        WeatherData first = periods.get(0);
        String bgUrl = ImageUtil.getBackgroundUrl(first.getShortForecast(), first.isDaytime());
        rootPane.setStyle("-fx-background-image: url('" + bgUrl + "');");
        
        rootPane.getStyleClass().removeAll("dark-theme", "light-theme");
        if (ImageUtil.isDarkBackground(first.getShortForecast(), first.isDaytime())) {
            rootPane.getStyleClass().add("dark-theme");
        } else {
            rootPane.getStyleClass().add("light-theme");
        }

        int index = 0;
        // loop through each period
        for (WeatherData p : periods) {
            WeatherCard card = new WeatherCard();
            FontIcon icon = ImageUtil.getIconForWeather(p.getShortForecast(), p.isDaytime());
            card.setData(p.getName(), p.getShortForecast(), String.valueOf(p.getTemperature()), p.getWindSpeed() + " " + p.getWindDirection(), icon);

            // calculate column and row to fix with the index
            int col = index % 2;
            int row = index / 2;
            grid.add(card, col, row);
            index++;
        }
    }

    // switch to today view
    @FXML
    private void switchToToday() {
        if (sceneManager != null) {
            sceneManager.switchScene("Today");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
