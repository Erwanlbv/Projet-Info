package Traducteur;

import javafx.event.*;
import file.*;

import java.io.File;
import java.util.*;
import java.util.Collection;
import java.util.Iterator;



import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import jdk.internal.misc.FileSystemOption;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Main extends Application {
	
	private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Some Files");
 
        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        fileChooser.getExtensionFilters().addAll(
        		new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        
        
        
    }

	public static void main(String[] args) {
		launch(args);
		String T = System.out.toString();
	}
	
	public void start(Stage stage) {
		
		double WIDTH = 1200;
	    double HEIGHT = 700;
	    double height_button = 75;
	    double width_button = 300;
	    
	    Group root = new Group();
	    Home accueil = new Home("Traducteur de Langage des Signes", false);
	    
	    stage.setTitle(accueil.name);
    	stage.setResizable(false);
    	
    	Scene scene = new Scene(root);
    	Canvas canvas = new Canvas(WIDTH, HEIGHT);
    	
    	root.getChildren().add(canvas);
	    
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
	    Button end = accueil.showButton("Quitter", WIDTH - 125, 25, 100, 50);
	    Button webcam = accueil.showButton("Webcam", (WIDTH/2 + (width_button/3)) , (5 * HEIGHT)/7, width_button, height_button);
	    Button upload = accueil.showButton("Upload", (WIDTH/2 - ((4 * width_button)/3)), (5 * HEIGHT)/7 , width_button, height_button);

	    
	    Image background = accueil.Background(WIDTH, HEIGHT);
	    
	    gc.drawImage(background, 0, 0);
	    root.getChildren().add(end);
	    root.getChildren().add(webcam);
	    root.getChildren().add(upload);

	    
	    stage.setScene(scene);
		stage.show();
	    	
	    upload.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    	final FileChooser fileChooser = new FileChooser();
		        configuringFileChooser(fileChooser);
		    	double NEW_WIDTH = 600;
		    	double NEW_HEIGHT = 600;
		    	FileSelect fileselect = new FileSelect();
			    Group file_group = fileselect.getGroup();
			   	
			   	Scene file_scene = new Scene(file_group, NEW_WIDTH, NEW_HEIGHT);
			   	Stage newWindow = new Stage();
			   	
			   	
			   	Button button = fileselect.showButton("Choisis l'image", 50, 90, 200, 50);
		    	Button valide = fileselect.showButton("Valider", 350, 450, 200, 50);
		    	
		    	newWindow.setTitle("Sélection de fichier");
			   	newWindow.setResizable(false);
			   	
			   	file_group.getChildren().add(valide);
			   	file_group.getChildren().add(button);
			      
			   	newWindow.setScene(file_scene);
			   	newWindow.show();;
			   	
			   	button.setOnAction(new EventHandler<ActionEvent>() {
			   		String T;	
			   	 
		            public void handle(ActionEvent event) {
		                File SelectedFile = fileChooser.showOpenDialog(newWindow);
		                T = SelectedFile.getPath();
		                System.out.println(T);;
		                newWindow.close();
		            }
		            
		        });
			   	valide.setOnAction(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent e) {
				    	newWindow.close();
				    	HandDetection hd = new HandDetection();
				    	Scene hand_scene = new Scene(hd.getGroup());
				    	stage.setScene(hand_scene);
				    	stage.show();
				    	}
				    });
			   }
		});
	    

		end.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	stage.close();
		    	}
		 });
		

		new AnimationTimer() {
			public void handle(long arg0) {
					
			}
		}.start(); 
		
		
		
		
	}
	
}
