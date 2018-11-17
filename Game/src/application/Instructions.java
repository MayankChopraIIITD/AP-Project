import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Instructions {

	private Pane pane_instructions = new Pane();
	MenuButton back = new MenuButton("BACK");
	
	public Pane get_instructions_pane() throws Exception{
		Image img = null;
		InputStream in = null;
		try{
			in = Files.newInputStream(Paths.get("res/images/snake2.jpg"));	
			img = new Image(in);
		}finally{	
			in.close();
		}
		ImageView view2 = new ImageView(img);
		view2.setFitWidth(800);
		view2.setFitHeight(800);
		
		Label l2 = new Label("INSTRUCTIONS");
		l2.setTranslateX(150);
		l2.setTranslateY(40);
		l2.setTextFill(Color.BLACK);
		l2.setFont(l2.getFont().font("INSTRUCTIONS",FontWeight.BOLD,70));
		Image img_shield = null; Image img_magnet = null; Image img_destroy = null;
		InputStream in_shield = null; InputStream in_magnet = null; InputStream in_destroy = null; 
		try{
			in_shield = Files.newInputStream(Paths.get("res/images/shield.jpg"));
			in_magnet  = Files.newInputStream(Paths.get("res/images/Magnet.png"));
			in_destroy = Files.newInputStream(Paths.get("res/images/destroy.jpg"));
			img_shield = new Image(in_shield); img_magnet = new Image(in_magnet); img_destroy = new Image(in_destroy);
		}finally{	
			in_shield.close();
			in_magnet.close();
			in_destroy.close();
		}
		ImageView s_view = new ImageView(img_shield);
		ImageView m_view = new ImageView(img_magnet);
		ImageView d_view = new ImageView(img_destroy);
		s_view.setFitWidth(40); s_view.setFitHeight(40);  s_view.setTranslateX(30); s_view.setTranslateY(580);
		m_view.setFitWidth(40); m_view.setFitHeight(40);  m_view.setTranslateX(30); m_view.setTranslateY(680); 		
		d_view.setFitWidth(40); d_view.setFitHeight(40);  d_view.setTranslateX(30); d_view.setTranslateY(510);	
		String s2 = " Each block has a value, which represents the points made by the"; 
		String s2_1=" player when the snake eats it. After eating the block the length";
		String s2_2=" of the snake decreases by the value of the block";
		String s3 = " The game ends when the snake is not able to eat a ";
		String s3_ = " block completely. ";
		String s4 = " The length of the snake increases by the value of ";
		String s4_ = " the ball if it eats the ball. ";
		String s5 = " Destroy all the blocks present on the screen. ";
		String s6 = " Lets you to eat any block without decreasing the ";
		String s6_= " snake's length. ";
		String s7 = " Lets the snake to collect coins which are within a ";
		String s7_ = " certain distance from the head of the snake. ";
		Text t3 = new Text(s3); Text t3_ = new Text(s3_);
		Text t4 = new Text(s4); Text t5 = new Text(s5); Text t6 = new Text(s6); Text t6_ = new Text(s6_);
		Text t7 = new Text(s7); Text t7_ = new Text(s7_);
		Text t2 = new Text(s2); Text t2_1 = new Text(s2_1); Text t2_2 = new Text(s2_2);
		Text t4_ = new Text(s4_);
		t2.setTranslateX(75); t2.setTranslateY(170); t2_1.setTranslateX(75); t2_1.setTranslateY(210); 
		t2_2.setTranslateX(75); t2_2.setTranslateY(250); 
		t3.setTranslateX(75); t3.setTranslateY(325); t3_.setTranslateX(75); t3_.setTranslateY(362); 
		t4.setTranslateX(75); t4.setTranslateY(430); t4_.setTranslateX(75); t4_.setTranslateY(470);  
		t5.setTranslateX(75); t5.setTranslateY(540);
		t6.setTranslateX(75); t6.setTranslateY(600); t6_.setTranslateX(75); t6_.setTranslateY(640);
		t7.setTranslateX(75); t7.setTranslateY(700); t7_.setTranslateX(75); t7_.setTranslateY(740);	
		t4.setFill(Color.BLACK); t5.setFill(Color.BLACK); 
		t6.setFill(Color.BLACK); t7.setFill(Color.BLACK);
		t2.setFont(t2.getFont().font(s3, FontWeight.BOLD,20)); t2_1.setFont(t2_1.getFont().font(s3, FontWeight.BOLD,20));
		t2_2.setFont(t2_2.getFont().font(s3, FontWeight.BOLD,20));
		t3.setFont(t3.getFont().font(s3, FontWeight.BOLD,20)); t3_.setFont(t3_.getFont().font(s3, FontWeight.BOLD,20));
		t4.setFont(t4.getFont().font(s3, FontWeight.BOLD,20));
		t4_.setFont(t4_.getFont().font(s3, FontWeight.BOLD,20));
		t5.setFont(t5.getFont().font(s3, FontWeight.BOLD,20));
		t6.setFont(t6.getFont().font(s3, FontWeight.BOLD,20)); t6_.setFont(t6_.getFont().font(s3, FontWeight.BOLD,20));
		t7.setFont(t7.getFont().font(s3, FontWeight.BOLD,20)); t7_.setFont(t7_.getFont().font(s3, FontWeight.BOLD,20));
			
		pane_instructions.getChildren().add(view2);
		pane_instructions.getChildren().add(l2);
		pane_instructions.getChildren().add(back);
		pane_instructions.getChildren().addAll(t2,t2_1,t2_2,t3,t3_,t4,t4_,t5,t6,t6_,t7_,t7,s_view,m_view,d_view);
		
		
		return pane_instructions;
	}
	public MenuButton get_instructions_button(){
		return back;
	}
	
	
}
