import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;



public class finals extends Application {
    
    ArrayList<ArrayList<Tasks>> main_server = new ArrayList();
    ArrayList<Tasks> date_server = new ArrayList<>();
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage ps) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_LEFT);
        
        HBox hb1 = new HBox();
        hb1.setAlignment(Pos.TOP_LEFT);
        hb1.setPrefWidth(1000);  
        hb1.setPrefHeight(32);
        hb1.setSpacing(5);


        HBox hb2 = new HBox(); 
        hb2.setAlignment(Pos.TOP_LEFT);
        hb2.setPrefWidth(1000);  
        hb2.setPrefHeight(100);
        hb2.setSpacing(5);
        
        HBox hb3 = new HBox(); 
        hb3.setAlignment(Pos.TOP_LEFT);
        hb3.setPrefWidth(1000);  
        hb3.setPrefHeight(100);
        hb3.setSpacing(5);
        
        HBox vb1 = new HBox(); 
        vb1.setAlignment(Pos.TOP_LEFT);
        vb1.setPrefWidth(500);  
        vb1.setPrefHeight(20);
        vb1.setSpacing(5);
        
        VBox vbmain = new VBox();
        vbmain.setAlignment(Pos.TOP_LEFT);
        vbmain.setPrefHeight(500);  
        vbmain.setPrefWidth(700);
        vbmain.setSpacing(5);
        
        
        // LABELS
        //--------------------------------------------
        // - hb1 (labels)
        int fontSize = 13;
        Label lbltask = new Label("Task: ");
        lbltask.setFont(new Font(fontSize));

        Label lbldate = new Label("             Date: ");
        lbldate.setFont(new Font(fontSize));
        
        Label lblindex = new Label("             Index: ");
        lblindex.setFont(new Font(fontSize));
        //
        
        
        // BUTTONS
        // - hb2 (buttons)
        //--------------------------------------------
        Button btnadd = new Button("Add");
        Button btndel = new Button("Delete");
        Button btnmod = new Button("Modify");
        Button btnsort = new Button("Sort by Date");
        //
        
        
        // TXT FIELDS
        TextField tftask = new TextField();
        tftask.setPromptText("Task description");
        Scene scn1 = new Scene(root);
        //
        
        // DATE PICKER
        DatePicker datepick = new DatePicker();
        //
        

        // DISPLAYING
        // - hb3 (title area)
        Label lbltitle = new Label("Tasks:" + "Noob");
        //
        

        // BTN METHODS
        // --- needs indexing to specify which index to remove (as shown:)
        AtomicInteger btnadd_count = new AtomicInteger(0); //AtomicInt (bluetooth int vers.)
        btnadd.setOnAction(e -> {
            btnadd_count.incrementAndGet(); // similar to ++ incrementing to int (but to AtomicInt)
            ArrayList<Tasks> taskdate_placeholder = new ArrayList();
            
            if (datepick.getValue() != null) {
                System.out.println("if ran");
                System.out.println(datepick.getValue());
                taskdate_placeholder.add(new Tasks(tftask.getText(), datepick.getValue()));
                Tasks date = new Tasks(datepick.getValue());
                date_server.add(date);
            }
            else {
                taskdate_placeholder.add(new Tasks(tftask.getText()));
                System.out.println("else ran");
            }
            
            System.out.println(taskdate_placeholder); // -- 4 remove
            main_server.add(taskdate_placeholder);
            System.out.println(main_server); // -- 4 remove

            // Converting Task object to string
            ArrayList<String> stringTasks = new ArrayList<>();
            for (ArrayList<Tasks> group : main_server) {
                for (Tasks t : group) {
                    stringTasks.add(t.toString());
                }
            }
            
            // Converted Task (w/ date) object to string, then splits (soon to be used by sort by date)
            Label lblarea = new Label();
            int lbl_id = 0;
            for (String st : stringTasks) {
                lbl_id++;
                String[] parts = st.split(" - ", 2); // split only once at the first " - "
                
                String task = parts[0];
                String date = parts[1];
                date_server.add(Tasks.fromString(date));
                
                lblarea.setText(lbl_id + ". " + st);
            }
            vb1.getChildren().addAll(lblarea);
        });
        //


        hb1.getChildren().addAll(lbltask, tftask, lbldate, datepick, lblindex);
        hb2.getChildren().addAll(btnadd, btndel, btnmod, btnsort);
        hb3.getChildren().addAll(lbltitle);
//        hb4.getChildren().addAll(lblarea); --- previous position of hb4
        vbmain.getChildren().addAll(hb1, hb2, hb3);
        for (int i = 0; i < main_server.size(); i++) {
            vbmain.getChildren().addAll(vb1);
        }
        root.getChildren().addAll(vbmain);
        root.setPadding(new Insets(20));
        ps.setTitle("To-Do List with Dates");
        ps.setMaximized(false);
        ps.setScene(scn1);
        ps.show();
    }
}