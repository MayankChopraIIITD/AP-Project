package application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Block {
	private ArrayList<Rectangle> block_list=new ArrayList<Rectangle>(); 
	Shield shield=new Shield();
	
	public Group generate_blocks(int in,int len) {
		int num=0; 
//		try {
//			shield.createShield(root);
//		}catch(Exception e) {
//		}
		Random r = new Random();
		int p=r.nextInt(3);
		if(p==1) {
			num=r.nextInt(len)+1;
		}else {
			num=r.nextInt(20)+1;
		}
		Rectangle rect = new Rectangle((in*120),-900,120,120);
		rect.setArcHeight(15);
		rect.setArcWidth(15);
		int g=r.nextInt(10);
		String a=Integer.toString(num);
		Text n=new Text(a);
		n.setX(rect.getX()+45);
		n.setY(rect.getY()+75);
		n.setFont(new Font(40));
		switch(g) {
		case 0:
			rect.setFill(Color.GOLDENROD);
		case 1:
			rect.setFill(Color.AQUA);
			break;
		case 2:
			rect.setFill(Color.BLUEVIOLET);
			break;
		case 3:
			rect.setFill(Color.CORAL);
			break;
		case 4:
			rect.setFill(Color.GREENYELLOW);
			break;
		case 5:
			rect.setFill(Color.VIOLET);
			break;
		case 6:
			rect.setFill(Color.SNOW);
			break;
		case 7:
			rect.setFill(Color.MEDIUMSLATEBLUE);
			break;
		case 8:
			rect.setFill(Color.MEDIUMVIOLETRED);
		case 9:
			rect.setFill(Color.BEIGE);
		}
		rect.setStroke(Color.BLACK);
		Group grp=new Group(rect,n);
		return grp;
	}
}