import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ai.djl.ModelException;
import ai.djl.modality.Classifications;
import ai.djl.translate.TranslateException;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.image.ImageView;
import java.lang.String;
import javafx.scene.shape.Rectangle;

public class Traductor {
	private double WIDTH = 1200;
    private double HEIGHT = 700;
	Group group;
	String path;
	public Traductor(String path) throws TranslateException, IOException, ModelException {
		this.path = path;
		this.group = new Group();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		
		group.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(129,131,193));
		gc.fillRect(0, 0, canvas.getWidth() , canvas.getHeight());
		
		InputStream stream = new FileInputStream(path);
	    Image image = new Image(stream);
	    ImageView imageView = new ImageView();
	    imageView.setImage(image);
	    imageView.setX(150);
	    imageView.setY(300);
	    imageView.setFitWidth(300);
	    imageView.setPreserveRatio(true);
		
		Text text = new Text();
		text.setFont(Font.font ("Verdana", 20));
		text.setX(150);
		text.setY(100);
		text.setWrappingWidth(900);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setUnderline(true);
		text.setText("La photo que vous avez choisi est "+path);
		
		Rectangle r = Rectangle();
		
		group.getChildren().add(text);
		group.getChildren().add(imageView);
		group.getChildren().add(r);
		
		Classifications c = Translate(false, 1);
		System.out.println(c);
		
		String s = (c.best()).toString();
		String classe = s.substring(8, 9);
		
		Text resultat = new Text();
		resultat.setFont(new Font(20));
		resultat.setX(600);
		resultat.setY(350);
		resultat.setWrappingWidth(400);
		resultat.setUnderline(true);
		resultat.setText("D'après notre algorithme, le signe représenté est : "+classe);
		
		group.getChildren().add(resultat);
	}
	private Classifications Translate(boolean train, int model) throws TranslateException, IOException, ModelException {
		if (train) {
			switch (model) {
			case 1: Training t1 = new Training();
					t1.train();
					break;
			case 2: Training2 t2 = new Training2();
					t2.train();
					break;
			}
		}
		Inference inference = new Inference(path);
		Classifications classification = inference.predict();
		return classification;
	}
	public Group getGroup() {
		return group;
	}
	private Rectangle Rectangle() {
		Rectangle r = new Rectangle();
		r.setFill(Color.rgb(102, 102, 153));
		r.setX(500);
		r.setY(300);
		r.setWidth(600);
		r.setHeight(300);
		r.setArcWidth(20);
		r.setArcHeight(20);
		return r;
	}
	public Button showButton(String button_name, double x, double y, double sizeX, double sizeY) {
		Button button = new Button(button_name);
	    button.setPrefSize(sizeX, sizeY);
	    button.setFont(new Font("Courier New",15));
	    button.setLayoutX(x);
	    button.setLayoutY(y);
		return button;
	}
}
