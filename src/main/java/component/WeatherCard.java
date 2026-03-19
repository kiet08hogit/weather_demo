package component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.kordamp.ikonli.javafx.FontIcon;

/* Component to display weather information in a card */
public class WeatherCard extends VBox {
    private Text periodNameTxt;
    private Text shortForecastTxt;
    private FontIcon iconTxt;
    private Text tempTxt;
    private Text windTxt;

    public WeatherCard() {
        super(10);
        this.getStyleClass().add("weather-card");
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER_LEFT);

        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.TOP_LEFT);
        
        VBox labels = new VBox(5);
        periodNameTxt = new Text();
        periodNameTxt.getStyleClass().add("card-period-name");
        shortForecastTxt = new Text();
        shortForecastTxt.getStyleClass().add("card-short-forecast");
        labels.getChildren().addAll(periodNameTxt, shortForecastTxt);

        iconTxt = new FontIcon();
        iconTxt.getStyleClass().add("card-icon");

        HBox.setHgrow(labels, javafx.scene.layout.Priority.ALWAYS);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        topRow.getChildren().addAll(labels, spacer, iconTxt);

        tempTxt = new Text();
        tempTxt.getStyleClass().add("card-temp");

        windTxt = new Text();
        windTxt.getStyleClass().add("card-wind");

        HBox windBox = new HBox(6);
        windBox.setAlignment(Pos.CENTER_LEFT);
        FontIcon windIconLbl = new FontIcon("fas-wind");
        windIconLbl.setIconSize(12);
        windIconLbl.setIconColor(javafx.scene.paint.Color.WHITE);
        windBox.getChildren().addAll(windIconLbl, windTxt);

        this.getChildren().addAll(topRow, tempTxt, windBox);
    }

    // Update data for weather card(name, description, temperature, wind,icon)
    public void setData(String name, String shortFcast, String temp, String wind, FontIcon iconObj) {
        periodNameTxt.setText(name);
        shortForecastTxt.setText(shortFcast);
        tempTxt.setText(temp + "\u00B0 F");
        windTxt.setText(wind);
        iconTxt.setIconLiteral(iconObj.getIconLiteral());
    }
}
