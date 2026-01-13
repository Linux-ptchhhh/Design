package com.example.demo.buttons;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Node.*;

public class sb{
    public static Button student(){
            Button b0 = new Button("Strudent login");
            b0.setPrefSize(200, 60);
            b0.setLayoutX(0);
       b0.setLayoutY(190);
       b0.setId("student");
       return b0;
    }
    
     public  static Button academic(){
        Button b1 = new Button("Academic login");
        
        b1.setPrefSize(200, 60);
       b1.setLayoutX(0);
       b1.setLayoutY(275);
       b1.setId("student");

       return b1;
     }
       
      
}
