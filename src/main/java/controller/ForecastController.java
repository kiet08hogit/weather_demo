package controller;

import app.SceneManager;
import component.WeatherCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;
import model.WeatherModel;
import util.ImageUtil;
import weather.Period;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/* Controller for 3-day forecast view */
public class ForecastController implements Initializable {
    private WeatherModel model;
    private SceneManager sceneManager;

    @FXML private GridPane grid;
    @FXML private javafx.scene.layout.BorderPane rootPane;

    // Assign model and switch screen
    public void setModel(WeatherModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
        refresh();
    }

    // Refresh data display
    public void refresh() {
        if (model != null) {
            ArrayList<Period> forecast = model.getThreeDayForecast();
            updateView(forecast);
        }
    }

    // Update list of weather forecast cards
    private void updateView(ArrayList<Period> periods) {
        grid.getChildren().clear();
        if (periods == null || periods.isEmpty()) return;

        // Use the first forecast to set the background and theme
        Period first = periods.get(0);
        String bgUrl = ImageUtil.getBackgroundUrl(first.shortForecast, first.isDaytime);
        rootPane.setStyle("-fx-background-image: url('" + bgUrl + "');");
        
        rootPane.getStyleClass().removeAll("dark-theme", "light-theme");
        if (ImageUtil.isDarkBackground(first.shortForecast, first.isDaytime)) {
            rootPane.getStyleClass().add("dark-theme");
        } else {
            rootPane.getStyleClass().add("light-theme");
        }

        int index = 0;
        for (Period p : periods) {
            WeatherCard card = new WeatherCard();
            FontIcon icon = ImageUtil.getIconForWeather(p.shortForecast, p.isDaytime);
            card.setData(p.name, p.shortForecast, String.valueOf(p.temperature), 
                         p.windSpeed + " " + p.windDirection, icon);
            
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
