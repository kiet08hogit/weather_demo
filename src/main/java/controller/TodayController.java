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

    @FXML private Text tempTxt;
    @FXML private Text shortForecastTxt;
    @FXML private Text windSpeedTxt;
    @FXML private Text windDirTxt;
    @FXML private FontIcon centerIconTxt;

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
