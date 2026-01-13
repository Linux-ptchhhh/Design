package com.example.demo.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.DemoApplication;
import com.example.demo.buttons.sb;

public class app extends Application{
    private ConfigurableApplicationContext springContext;

    @Override
    public void start(Stage primaryStage) {
        BorderPane b = new BorderPane();
        Button b1 = sb.student();
        Button b2 = sb.academic();
       Pane p = new Pane();
      p.getChildren().addAll(b1,b2);
    
        Scene scene = new Scene(p, 800, 600);
     scene.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

        primaryStage.setTitle("Studnet Management Tools-50076-//@@k-community");
        primaryStage.setScene(scene);
        primaryStage.show();

        
    }

    @Override
    public void stop() {
        springContext.close();
    }
    
}
