package uet.oop.bomberman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
 
public class test 
{
   
    public static void main(String[] args) throws IOException {
        try {
            System.out.println(new File("res\\textures\\classic.png").getCanonicalPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}