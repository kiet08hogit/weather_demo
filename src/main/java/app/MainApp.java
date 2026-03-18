package app;

import controller.ForecastController;
import controller.TodayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WeatherModel;

public class MainApp extends Application {
    private SceneManager sceneManager;
    private WeatherModel weatherModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("JavaFX Weather App");

            sceneManager = new SceneManager(primaryStage);
            weatherModel = new WeatherModel();

            // Load Today Scene
            FXMLLoader todayLoader = new FXMLLoader(getClass().getResource("/FXML/today.fxml"));
            Parent todayRoot = todayLoader.load();
            TodayController todayCtrl = todayLoader.getController();
            todayCtrl.setModel(weatherModel, sceneManager);
            Scene todayScene = new Scene(todayRoot, 900, 600);

            // Load Forecast Scene
            FXMLLoader forecastLoader = new FXMLLoader(getClass().getResource("/FXML/forecast.fxml"));
            Parent forecastRoot = forecastLoader.load();
            ForecastController forecastCtrl = forecastLoader.getController();
            forecastCtrl.setModel(weatherModel, sceneManager);
            Scene forecastScene = new Scene(forecastRoot, 900, 600);

            // Add stylesheet
            String cssPath = getClass().getResource("/style.css") != null ? getClass().getResource("/style.css").toExternalForm() : "data:text/css,";
            todayScene.getStylesheets().add(cssPath);
            forecastScene.getStylesheets().add(cssPath);

            sceneManager.addScene("Today", todayScene);
            sceneManager.addScene("Forecast", forecastScene);

            sceneManager.switchScene("Today");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
