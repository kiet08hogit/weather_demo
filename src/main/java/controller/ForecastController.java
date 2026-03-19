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

public class ForecastController implements Initializable {
    private WeatherModel model;
    private SceneManager sceneManager;

    @FXML private GridPane grid;

    public void setModel(WeatherModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;

        ArrayList<Period> forecast = model.getThreeDayForecast();
        if (forecast.isEmpty()) {
            model.fetchForecast();
            forecast = model.getThreeDayForecast();
        }

        updateView(forecast);
    }

    private void updateView(ArrayList<Period> periods) {
        grid.getChildren().clear();
        if (periods == null || periods.isEmpty()) return;

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
