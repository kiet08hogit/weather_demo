package app;

import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.HashMap;

public class SceneManager{
    private HashMap<String, Parent> roots = new HashMap<>();
    private Scene mainScene;
    private javafx.stage.Stage stage;

    public SceneManager(javafx.stage.Stage stage){
        this.stage = stage;
    }

    public void setMainScene(Scene scene){
        this.mainScene = scene;
        this.stage.setScene(scene);
    }

    public void addRoot(String name, Parent root){
        roots.put(name, root);
    }
    
    public void switchScene(String name){
        Parent root = roots.get(name);
        if (root != null){
            if (mainScene != null){
                mainScene.setRoot(root);
            }
            else {
                System.err.println("Main scene not set!");
            }
        } 
        else{
            System.err.println("Screen " + name + " not found!");
        }
    }
}
