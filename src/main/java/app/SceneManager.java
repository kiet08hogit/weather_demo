package app;

import javafx.scene.Scene;
import java.util.HashMap;

public class SceneManager {
    private HashMap<String, Scene> scenes = new HashMap<>();
    private javafx.stage.Stage stage;

    public SceneManager(javafx.stage.Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public void switchScene(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
        } else {
            System.err.println("Scene " + name + " not found!");
        }
    }
}
