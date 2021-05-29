import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;

// Classe de la gestion de la page d'accueil 
public class Home {
	String name;
	
	// Constructeur
	public Home(String name) {
		this.name = name;
	}
	// Méthode de création de boutton pour la page d'accueil 
	public Button showButton(String button_name, double x, double y, double sizeX, double sizeY) {
		Button button = new Button(button_name);
	    button.setPrefSize(sizeX, sizeY);
	    button.setFont(new Font("Courier New",15));
	    button.setLayoutX(x);
	    button.setLayoutY(y);
		return button;
	}
	public Text Title() {
		Text text = new Text();
		text.setFont(Font.font ("Copperplate", FontWeight.BOLD, FontPosture.ITALIC, 50));
		text.setX(150);
		text.setY(150);
		text.setWrappingWidth(900);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setUnderline(true);
		text.setText("Traducteur de langage des signes");
		return text;
	}
}