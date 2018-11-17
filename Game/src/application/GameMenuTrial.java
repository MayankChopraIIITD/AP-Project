import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

class MenuButton extends StackPane{
	private Text text;
	
	public MenuButton(String name){
		text = new Text(name);
		text.setFont(text.getFont().font(22));
		Rectangle back = new Rectangle(200,40);
		back.setOpacity(0.7);
		back.setFill(Color.DARKKHAKI);
		GaussianBlur b = new GaussianBlur(3.2);
		back.setEffect(b);
		
		setAlignment(Pos.CENTER_LEFT);
		getChildren().addAll(back,text);
		
		setOnMouseEntered(event -> {
			back.setTranslateX(10);
			text.setTranslateX(10);
			back.setFill(Color.BLACK);
			text.setFill(Color.GREY);
		});
		setOnMouseExited(event -> {
			back.setTranslateX(0);
			text.setTranslateX(0);
			back.setFill(Color.DARKKHAKI);
			text.setFill(Color.BLACK);
		});			
	}	
}

class playTimeline implements Runnable{
	private Timeline t;
	
	public playTimeline(Timeline t){
		this.t = t;
	}
	
	@Override
	public void run(){
		t.play();
	}
	
}

public class GameMenuTrial extends Application  {
	private boolean paused=false;
	private Pane root;
	private Pane pane_game = new Pane();
	private Scene scene_main, scene_instructions,scene_leaderboard,scene_newgame;
	private Block rect = new Block();
	private Ball ball = new Ball();
	private ArrayList<Group> block_list ;
	private ArrayList<Group> ball_list;
	private ArrayList<Sprite> snake_list = new ArrayList<>();
	private ArrayList<Group> block_list_2 = new ArrayList<>();
	private ArrayList<Group> ball_list_2 = new ArrayList<>();
	private ArrayList<TranslateTransition> blocks;
	private ArrayList<TranslateTransition> balls;
	private Snake snake;
	private Text Score=new Text("0");
	private int num_blocks = 0;
	private int num_balls = 0;
	private Text Score_name=new Text("Score:");
	public GameMenuTrial(){
		root = new Pane();
	}
	
	private class Timehandler1 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			num_blocks = block_list_2.size();
			
			Random random_num = new Random();
			int block_index = random_num.nextInt(5)+ num_blocks;
			for(int i=0;i<5;i++){
				block_list_2.add(rect.generate_blocks(i));
			}
			int block_value = random_num.nextInt(snake_list.size())+1;
			Text j = (Text)block_list_2.get(block_index).getChildren().get(1);
			j.setText(Integer.toString(block_value));
			block_list_2.get(block_index).getChildren().set(1,j);

			blocks=Animate_blocks(block_list_2,num_blocks);
			
			if(!paused) {
				for(int u=0;u<blocks.size();u++) {
					blocks.get(u).play();
				}
			}else {
				for(int u=0;u<blocks.size();u++) {
					blocks.get(u).pause();
				}
			}
			
			for(int i=num_blocks;i<block_list_2.size();i++){
				pane_game.getChildren().add(block_list_2.get(i));
			}
		}
	}
	private class Timehandler_balls_1 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			ball_list = new ArrayList<>();
			Random r = new Random();
			int num_balls_random = r.nextInt(4)+1;
//			int num = ball_list_2.size();
			num_balls = ball_list_2.size();
			for(int i=0;i<num_balls_random;i++){
				ball_list.add(ball.createball());
				ball_list_2.add(ball.createball());
			}
			balls=Animate_balls(ball_list_2,num_balls);
			for(int o=0;o<balls.size();o++) {
				balls.get(o).play();
			}
			for(int i=num_balls;i<ball_list_2.size();i++){
				pane_game.getChildren().add(ball_list_2.get(i));
			}
		}
	}
	
	private class Timehandler2 implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			
			for(int i=0;i<block_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(block_list_2.get(i).getBoundsInParent())){
					block_list_2.get(i).setVisible(false);
					Text gh=(Text)block_list_2.get(i).getChildren().get(1);
					int h=Integer.parseInt(gh.getText());
					if(h>=snake_list.size()) {
						System.exit(0);
					}
					int index=snake_list.size()-1;
					for(int l=index;l>index-h;l--) {
						pane_game.getChildren().remove(snake_list.get(l));
						
				}	for(int l=index;l>index-h;l--) {
						snake_list.remove(l);
				}	
				snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())-h));
				Score.setText(Integer.toString(Integer.parseInt(Score.getText())+h));
				}
			}
		}
	}
	private class Timehandler_balls_2  implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event){
			for(int i=0;i<ball_list_2.size();i++){
				if(snake_list.get(0).getBoundsInParent().intersects(ball_list_2.get(i).getBoundsInParent())){
					ball_list_2.get(i).setVisible(false);
					Text gh=(Text)ball_list_2.get(i).getChildren().get(1);
					int h=Integer.parseInt(gh.getText());
					int index=snake_list.size()-1;
					for(int j=0;j<h;j++) {
						int x=(int)snake_list.get(index).getTranslateX();
						int y=(int)snake_list.get(index).getTranslateY();
						int y_new=y+30;
						Sprite a=new Sprite(x,y_new,15,"snake",Color.BLUE);
						snake_list.add(a);
						pane_game.getChildren().add(a);
						index=snake_list.size()-1;
				}
					snake.getlen().setText(Integer.toString(Integer.parseInt(snake.getlen().getText())+h));
					
				}
				}		
		}
	}
	
	public ArrayList<TranslateTransition> Animate_blocks(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++) {
			a=new TranslateTransition(Duration.millis(8000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
		}
		return l;
	}
	
	public ArrayList<TranslateTransition> Animate_balls(ArrayList<Group> list, int num){
		ArrayList<TranslateTransition> l=new ArrayList<>();
		TranslateTransition a;
		for(int i=num;i<list.size();i++){
			a = new TranslateTransition(Duration.millis(8000));
			a.setByY(1750);
			a.setNode(list.get(i));
			l.add(a);
		}
		return l;
	}
	
	public ArrayList<Group> get_ball_list(int num){
		for(int i=0;i<num;i++){
			ball_list.add(ball.createball());
		}
		return ball_list;
		
	}
	
	@Override
	public void start(Stage stage) throws Exception,InterruptedException{
		stage.initStyle(StageStyle.UNDECORATED);
		root.setPrefSize(800,800);
		
		// For MAIN MENU
		Label l = new Label("SNAKES V/S BLOCKS");
		l.setTranslateX(75);
		l.setTranslateY(50);
		l.setTextFill(Color.BLACK);
		l.setFont(l.getFont().font("SNAKES V/S BLOCKS", FontWeight.BOLD, 70));
		Image img = null;
		InputStream in = null;
		try{
			in = Files.newInputStream(Paths.get("res/images/snake2.jpg"));	
			img = new Image(in);
		}finally{	
			in.close();
		}
		ImageView view = new ImageView(img);
		ImageView view2 = new ImageView(img);
		ImageView view3 = new ImageView(img);
		view.setFitWidth(800);
		view.setFitHeight(800);
		view2.setFitWidth(800);
		view2.setFitHeight(800);
		view3.setFitWidth(800);
		view3.setFitHeight(800);
		
		VBox menu = new VBox(15);
		menu.setTranslateX(250);
		menu.setTranslateY(200);
		MenuButton btn1 = new MenuButton("RESUME");
		MenuButton btn2 = new MenuButton("NEW GAME");
		MenuButton btn3 = new MenuButton("LEADERBOARDS");
		MenuButton btn4 = new MenuButton("HELP");
		MenuButton btn5 = new MenuButton("EXIT");
		
		btn1.setDisable(true);
		
		btn3.setOnMouseClicked(event -> stage.setScene(scene_leaderboard));
		
		btn4.setOnMouseClicked(event -> stage.setScene(scene_instructions));	
	
		btn5.setOnMouseClicked(event -> {
			System.exit(0);
		});
		menu.getChildren().addAll(btn1,btn2,btn3,btn4,btn5);
	
		root.getChildren().add(view);
		root.getChildren().add(l);
		root.getChildren().add(menu);
		scene_main = new Scene(root);
		stage.setScene(scene_main);
		
		// NEW GAME
		
		Image img2 = null;
		InputStream in2 = null;
		try{
			in2 = Files.newInputStream(Paths.get("res/images/black.jpg"));	
			img2 = new Image(in2);
		}finally{	
			in2.close();
		}
		ImageView vie = new ImageView(img2);
		vie.setFitWidth(600);
		vie.setFitHeight(800);
		pane_game.getChildren().add(vie);
		Score_name.setX(0);
		Score_name.setY(100);
		Score_name.setFont(new Font(30));
		Score_name.setFill(Color.YELLOW);
		pane_game.getChildren().add(Score_name);
		Score.setX(80);
		Score.setY(100);
		Score.setFont(new Font(30));
		Score.setFill(Color.YELLOW);
		pane_game.getChildren().add(Score);
		snake = new Snake();
		snake_list = snake.getSnake();

		btn2.setOnMouseClicked(event -> {
			
			KeyFrame frame = new KeyFrame(Duration.millis(8000),new Timehandler1());
			KeyFrame frame_balls_1 = new KeyFrame(Duration.millis(8000),new Timehandler_balls_1());
			KeyFrame frame2 = new KeyFrame(Duration.millis(2000),new Timehandler2());
			KeyFrame frame_balls_2  = new KeyFrame(Duration.millis(200),new Timehandler_balls_2());
			
			Timeline t = new Timeline(frame);
			Timeline t_balls_1 = new Timeline(frame_balls_1);
			t.setCycleCount(Timeline.INDEFINITE);
			t_balls_1.setCycleCount(Timeline.INDEFINITE);
	
			Timeline t2 = new Timeline(frame2);
			Timeline t_balls_2 = new Timeline(frame_balls_2);
			t2.setCycleCount(Timeline.INDEFINITE);
			t_balls_2.setCycleCount(Timeline.INDEFINITE);
			
			t.play();
			t_balls_1.play();
			t2.play();
			t_balls_2.play();
			
			scene_newgame = new Scene(snake.createContent(pane_game));
			scene_newgame.setOnKeyPressed(e -> {
				switch (e.getCode()){
					case LEFT:
						if(!paused) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveLeft();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-8);
							}
						}
						break;
					case RIGHT:
						if(!paused) {
							for(int i=0;i<snake_list.size();i++){
								snake_list.get(i).moveRight();
								snake.getlen().setX(snake_list.get(0).getTranslateX()-5);
							}
						}
						break;		
					case P:
						paused=true;
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).pause();
						}
						for(int i=0;i<balls.size();i++) {
							balls.get(i).pause();
						}
						t.pause();
						t_balls_1.pause();
						t2.pause();
						t_balls_2.pause();
							break;
					
					case ESCAPE:
						System.exit(0);
						
					default:
						paused=false;		
						for(int i=0;i<blocks.size();i++) {
							blocks.get(i).play();
						}
						for(int i=0;i<balls.size();i++) {
							balls.get(i).play();
						}
						t.play();
						t_balls_1.play();
						t2.play();
						t_balls_2.play();
						break;
				}
				
			});
			stage.setScene(scene_newgame);	
			stage.show();
		});
		
		// FOR LEADERBOARD
		Pane pane_leaderboard = new Pane();
		Label l3 = new Label("LEADERBOARD");
		Label sno = new Label("Sno.");
		Label score = new Label("SCORE");
		Label date = new Label("DATE");
		l3.setTranslateX(150);
		l3.setTranslateY(40);
		l3.setTextFill(Color.BLACK);
		l3.setFont(l3.getFont().font("LEADERBOARD",FontWeight.BOLD,70));

		sno.setTranslateX(40); 	  sno.setTranslateY(150);  sno.setTextFill(Color.BLACK);
		score.setTranslateX(200); score.setTranslateY(150); score.setTextFill(Color.BLACK);
		date.setTranslateX(400);  date.setTranslateY(150);  date.setTextFill(Color.BLACK);	
		sno.setFont(sno.getFont().font("Sno.",FontWeight.BOLD,40));
		score.setFont(score.getFont().font("SCORE",FontWeight.BOLD,40));
		date.setFont(date.getFont().font("DATE",FontWeight.BOLD,40));
		
		MenuButton back1 = new MenuButton("BACK");
		pane_leaderboard.getChildren().add(view3);
		pane_leaderboard.getChildren().add(l3);
		pane_leaderboard.getChildren().add(back1);
		pane_leaderboard.getChildren().add(sno);
		pane_leaderboard.getChildren().add(score);
		pane_leaderboard.getChildren().add(date);
		
		scene_leaderboard = new Scene(pane_leaderboard,800,800);	
		
		// FOR INSTRUCTIONS
		
		Instructions inst = new Instructions();
		Pane pane_instructions = inst.get_instructions_pane();
		MenuButton back = inst.get_instructions_button();
		
		scene_instructions = new Scene(pane_instructions,800,800);
				
		back1.setOnMouseClicked(event -> stage.setScene(scene_main));  // getting back from leaderboard to main_menu
		
		back.setOnMouseClicked(event -> stage.setScene(scene_main));   // getting back from instructions to main_menu
		
		stage.show();	
	}
			
	public static void main(String[] args){
		launch(args);
	}
}
