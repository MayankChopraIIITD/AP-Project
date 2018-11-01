import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Block extends Application {
	
	@Override
	public void start(Stage stage) throws Exception{
		Pane pane = new Pane();
		Rectangle rect = new Rectangle(240,0,120,120);
		rect.setArcHeight(15);
		rect.setArcWidth(15);
		
		rect.setFill(Color.AQUA);
		rect.setStroke(Color.BLACK);
		pane.getChildren().add(rect);
		
		Scene scene = new Scene(pane,600,800);
		stage.setScene(scene);
		stage.show();
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
