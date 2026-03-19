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
            primaryStage.setTitle("Weatherify");
            primaryStage.setResizable(true);

            sceneManager = new SceneManager(primaryStage);
            weatherModel = new WeatherModel();

            // Load Today Scene
            FXMLLoader todayLoader = new FXMLLoader(getClass().getResource("/FXML/today.fxml"));
            Parent todayRoot = todayLoader.load();
            TodayController todayCtrl = todayLoader.getController();
            todayCtrl.setModel(weatherModel, sceneManager);

            // Load Forecast Scene
            FXMLLoader forecastLoader = new FXMLLoader(getClass().getResource("/FXML/forecast.fxml"));
            Parent forecastRoot = forecastLoader.load();
            ForecastController forecastCtrl = forecastLoader.getController();
            forecastCtrl.setModel(weatherModel, sceneManager);

            // Link controllers
            todayCtrl.setForecastController(forecastCtrl);

            // Setup single Main Scene
            Scene mainScene = new Scene(new javafx.scene.layout.Pane(), 900, 750);

            // Add stylesheet
            String cssPath = getClass().getResource("/style.css") != null ? getClass().getResource("/style.css").toExternalForm() : "data:text/css,";
            mainScene.getStylesheets().add(cssPath);

            sceneManager.setMainScene(mainScene);
            sceneManager.addRoot("Today", todayRoot);
            sceneManager.addRoot("Forecast", forecastRoot);

            sceneManager.switchScene("Today");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
