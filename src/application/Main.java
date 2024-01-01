// Imran Nasir A00146


package application;
	
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	// VARIABLES USED
	int score=0;	
	int b;

	Random random = new Random(); 
	public int rand = random.nextInt(9)+1;
	
	public static int[] potentialA = {0,0,0,0};
	
	@Override
	public void start(Stage primaryStage) {
		try {

			
			// NODES
							//first screen
			
			//Button
			Button giveUp = new Button("Give up");
			
			//Panes
			BorderPane bott = new BorderPane();	// pane for give up and score
			BorderPane root = new BorderPane();	// overall pane for first screen
			VBox topp = new VBox();	// pane for instructions and response
			
			//TextField
			TextField inp = new TextField();
			//Text
			Text response = new Text(); // responses to user guess
			Text inst = new Text("How To Play:\nInput a NUMBER(0-10) and press enter\nTry to get the answer with as a low of a score as possible\n");
			Text scoreOut = new Text(Integer.toString(score)); // visualisation of score
			
			
							//second screen	
			//pane
			BorderPane finishScreen = new BorderPane();
			
			//stage
			Stage finished = new Stage();
		
			//button
			Button exit = new Button("Exit");
			Button again = new Button("Again");
			
			//text
			Text finalScore = new Text();
			
						// Second Game
			RadioButton guess = new RadioButton("Guessing game");
			RadioButton answer = new RadioButton("Math game");
			ToggleGroup group = new ToggleGroup();
			guess.setToggleGroup(group);
			answer.setToggleGroup(group);
			
			 Menu options = new Menu("Options");	//nodes for menu bar
			 MenuItem m1 = new MenuItem("New game");
		     CheckMenuItem m2 = new CheckMenuItem("Advanced");
		     
		     options.getItems().add(m1);
		     options.getItems().add(m2);
		     
			 Menu view = new Menu("View"); 
			 Menu n1 = new Menu("Font");
		     Menu n2 = new Menu("Theme");
		     MenuItem sub1 = new MenuItem("Small");
		     MenuItem sub2 = new MenuItem("Large");
		     MenuItem sub3 = new MenuItem("Red");
		     MenuItem sub4 = new MenuItem("Black");
		     MenuItem sub5 = new MenuItem("Blue");
		     
		     n2.getItems().addAll(sub3,sub4,sub5);
		     
		     
		     view.getItems().add(n1);
		     n1.getItems().add(sub1);
		     n1.getItems().add(sub2);
		     
		     view.getItems().add(n2);
		     

		     
		     
		     MenuBar mb = new MenuBar();
		     mb.getMenus().add(view);
		     
		     TextField userName = new TextField();
		     Text enterName = new Text("Enter your name:  ");
		     HBox names = new HBox();
			BorderPane root1 = new BorderPane();	// overall pane for first screen

		     Text idk = new Text("");
		     Text equ = new Text("");
		     Button a1 = new Button();
		     Button a2 = new Button();
		     Button a3 = new Button();
		     Button a4 = new Button();
		     HBox answers = new HBox();
		     answers.getChildren().addAll(a1,a2,a3,a4);

		     
		     
		     idk.textProperty().bind(userName.textProperty());
		     names.getChildren().addAll(enterName,userName);

		// VISUAL
			
							//first screen
			inst.setFont(Font.font ("Arial",13));	//setting font
			response.setFont(Font.font(20));	//setting font
			root.setCenter(inp);
			root1.setCenter(answers);	

			bott.setLeft(giveUp);
			bott.setRight(scoreOut);
			bott.setCenter(names);
			topp.getChildren().addAll(mb,guess,answer,inst, equ,response);	
			root.setTop(topp);		
			root.setBottom(bott);
			root.setPadding(new Insets(10, 10, 10, 10));
			
			
			//second screen
			finishScreen.setLeft(exit);
			finishScreen.setRight(again);
			finishScreen.setCenter(finalScore);
			
			// EVENTS

			
			sub1.setOnAction(e->{
				root.getStyleClass().removeAll();			//Events for different view
				root1.getStyleClass().removeAll();

				root.getStyleClass().add("smallFont");
				root1.getStyleClass().add("smallFont");
		
			});
			
			
			
			sub2.setOnAction(e->{
				root.getStyleClass().removeAll();
				root1.getStyleClass().removeAll();

				root.getStyleClass().add("largeFont");
				root1.getStyleClass().add("largeFont");
				
			});
			sub3.setOnAction(e->{
				root.setId("redBackground");
				root1.setId("redBackground");

				
			});
			sub4.setOnAction(e->{
				root.setId("blackBackground");
				root1.setId("blackBackground");

			});
			sub5.setOnAction(e->{
				root.setId("blueBackground");
				root1.setId("blueBackground");

			});

							//first screen
			inp.setOnKeyPressed(e->{
				//Event handler to take in the input when the user presses enter
				if(e.getCode() == KeyCode.ENTER) {	//checks if Enter is pressed
					String currentAnswer = inp.getText();
					if(isNumeric(currentAnswer)){	//calls is numeric to check it only contains numbers
						int userAnswer = answer(Integer.parseInt(currentAnswer)); //calls answer to find which case it is
						switch(userAnswer) { 
						case -1: response.setText("Range is inbetween 0-100. Try again! " + idk.getText());
								 score+=1; // updates score
								 scoreOut.setText((Integer.toString(score))); // updates scores visual
								 break;	 
						case 0: response.setText("Too High " + idk.getText());
						 		score+=1;
						 		scoreOut.setText((Integer.toString(score)));
						 		break;
						case 1: response.setText("Too Low "+idk.getText());
						 	    score+=1;
						 	    scoreOut.setText((Integer.toString(score)));
						 	    break;
						 
						case 2: response.setText("Correct " + idk.getText());
								primaryStage.close();	// closes primary stag to stop players from continuing to play even after the game is over
								finalScore.setText("Final Score: " + (Integer.toString(score))); // sets up the final score text
								finished.show(); // calls the end game window
				 				break;
						};
					}
					else {
						response.setText("this is not a number try again! " + idk.getText()); // when the text field contains a letter
					}
					inp.clear(); // clears the text field every time you press enter
					
				}
				
			});
			
			a1.setOnAction(e->{											//event that checks each button and sees if they were the right button to press or not
				int userAnswer = Integer.parseInt(a1.getText());
				if(checkX(rand,userAnswer,b)) {
					response.setText("Good job, " + idk.getText()+ "!");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score +=1;
					scoreOut.setText((Integer.toString(score)));
}
				else {
					System.out.println(rand + userAnswer + b);
					response.setText("Try again, " + idk.getText());
					score -=1;
					scoreOut.setText((Integer.toString(score)));

				
				};
				
			});
			
			a2.setOnAction(e->{
				int userAnswer = Integer.parseInt(a2.getText());
				if(checkX(rand,userAnswer,b)) {
					response.setText("Good job, " + idk.getText()+ "!");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score +=1;
					scoreOut.setText((Integer.toString(score)));
}
				else {
					System.out.println(rand + userAnswer + b);
					response.setText("Try again, " + idk.getText());
					score -=1;
					scoreOut.setText((Integer.toString(score)));

				
				};
				
			});
			a3.setOnAction(e->{
				int userAnswer = Integer.parseInt(a3.getText());
				if(checkX(rand,userAnswer,b)) {
					response.setText("Good job, " + idk.getText()+ "!");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score +=1;
					scoreOut.setText((Integer.toString(score)));
}
				else {
					System.out.println(rand + userAnswer + b);
					response.setText("Try again, " + idk.getText());
					score -=1;
					scoreOut.setText((Integer.toString(score)));

				
				};
				
			});
			a4.setOnAction(e->{
				int userAnswer = Integer.parseInt(a4.getText());
				if(checkX(rand,userAnswer,b)) {
					response.setText("Good job, " + idk.getText()+ "!");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score +=1;
					scoreOut.setText((Integer.toString(score)));
}
				else {
					System.out.println(rand + userAnswer + b);
					response.setText("Try again, " + idk.getText());
					score-=1;
					scoreOut.setText((Integer.toString(score)));

				
				};
				
			});
			
			m2.setOnAction(e->{						//advance game. checks if the box is slected then changes the game accordingly
				if(m2.isSelected()) {
					rand = random.nextInt(90)+11;
					b = creatingB(rand);
					equ.setText(b + " = " + rand + "x");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score =0;
					scoreOut.setText((Integer.toString(score)));

}
				else {
					rand = random.nextInt(9)+1;
					b = creatingB(rand);
					equ.setText(b + " = " + rand + "x");
					b = creatingB(rand);
					System.out.println(b);
					equ.setText(b + " = " + rand + "x");
					clearAwnsers();
					setAwnsers(rand,b);
					a1.setText(Integer.toString(potentialA[0]));
					a2.setText(Integer.toString(potentialA[1]));
					a3.setText(Integer.toString(potentialA[2]));
					a4.setText(Integer.toString(potentialA[3]));
					score =0;
					scoreOut.setText((Integer.toString(score)));

					
}
				});
			giveUp.setOnMouseClicked(e->{
				// logic for clicking give up button
				response.setText((Integer.toString(rand) + " was the answer \n Better luck next time!!!")); // sets up response
				finalScore.setText("Game over! \n Score: " + score);
				primaryStage.close(); // closes first screen so only end game screen is up
				finished.show();
			});

			
			
						//second screen
			exit.setOnMouseClicked(e->{
				// exit button
				finished.close();
			});	
			
			again.setOnMouseClicked(e->{
				finished.close();
				// everything underneath is clearing all the variable that get updated while playing so it looks like the game reset
				inp.clear();
				rand = random.nextInt(100);
				score = 0;
				scoreOut.setText((Integer.toString(score)));
				response.setText("");
				b = creatingB(rand);
				System.out.println(b);
				equ.setText(b + " = " + rand + "x");
				clearAwnsers();
				setAwnsers(rand,b);
				a1.setText(Integer.toString(potentialA[0]));
				a2.setText(Integer.toString(potentialA[1]));
				a3.setText(Integer.toString(potentialA[2]));
				a4.setText(Integer.toString(potentialA[3]));
				primaryStage.show(); // restarts the game
			});	
			
			// EXECUTE
			Scene scene = new Scene(root,400,400);
			Scene mathGame = new Scene(root1,400,400);
			Scene scene2 = new Scene(finishScreen, 400,200);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mathGame.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			finished.setScene(scene2);
			
			answer.setOnAction(e->{		// changs the game to the math game
				b = creatingB(rand);
				System.out.println(b);
				equ.setText(b + " = " + rand + "x");
				clearAwnsers();
				setAwnsers(rand,b);
				a1.setText(Integer.toString(potentialA[0]));
				a2.setText(Integer.toString(potentialA[1]));
				a3.setText(Integer.toString(potentialA[2]));
				a4.setText(Integer.toString(potentialA[3]));
				primaryStage.setScene(mathGame);
				response.setText("");
				score = 0;
				scoreOut.setText((Integer.toString(score)));
				root1.setTop(topp);	
				root1.setBottom(bott);
				root1.setPadding(new Insets(10, 10, 10, 10));
				inst.setText("answer the math question by finding X");
				giveUp.setText("Exit");
			    mb.getMenus().add(options);

				
			});
			m1.setOnAction(e->{ // play again
				rand = random.nextInt(9)+1;
				b=creatingB(rand);
				equ.setText(b + " = " + rand + "x");
				clearAwnsers();
				setAwnsers(rand,b);
				a1.setText(Integer.toString(potentialA[0]));
				a2.setText(Integer.toString(potentialA[1]));
				a3.setText(Integer.toString(potentialA[2]));
				a4.setText(Integer.toString(potentialA[3]));

				response.setText("");
				score = 0;
				scoreOut.setText((Integer.toString(score)));
				
			});
			guess.setOnAction(e->{ // changes the game back to guess
				score=0;
				scoreOut.setText((Integer.toString(score)));

				
			    mb.getMenus().remove(options);

				giveUp.setText("Give up");
				response.setText("");

				primaryStage.setScene(scene);
				equ.setText("");
				root.setTop(topp);		
				root.setBottom(bott);
				root.setPadding(new Insets(10, 10, 10, 10));
				inst.setText("How To Play:\nInput a NUMBER(0-10) and press enter\nTry to get the answer with as a low of a score as possible\n");

				
			});
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	// LOGIC  
	public int answer(int x) {
		if(x>100 || x<-1) { // to help out players
			return -1;
		}
		if(x > rand) {		// too high
			return 0;
		}
		if(x < rand) {		// too low
			return 1;
		}
		return 2;		// correct
	}
	
	public static int creatingB(int a){  // creates b in the equation ax = b and returns it
		int b;
		Random random2 = new Random(); 
		int x = random2.nextInt(10)+1;
		
		b=x*a;
		
		return b;
	}
	public static void setAwnsers(int a, int b) { //sets the answer in an array[0,0,0,0] randomly with only one of the answers being correct 
		Random random3 = new Random(); 
		int rand3 = random3.nextInt(3);
		potentialA[rand3] = b/a;
		System.out.println(Arrays.toString(potentialA));
		for(int i =0;i<=3;i++) {
			if(potentialA[i] == 0) {
				rand3 = random3.nextInt(10)+1;
				System.out.println(rand3);


				while(duplicate(rand3)) { //checks out for duplicated
					rand3 = random3.nextInt(10)+1;
					System.out.println(Arrays.toString(potentialA));					
				}
				potentialA[i] =  rand3;
				System.out.println(Arrays.toString(potentialA));

			}
		}
	}
	public static boolean duplicate(int x) { //simple arrray check for duplicate
        boolean test = false;
        for (int element : potentialA) {
            if (element == x) {
                test = true;
                break;
            }
        }
        return test;
	}
	public static void clearAwnsers() {
		for(int i =0;i<=3;i++) { //clears the button so they are ready for new answers
			potentialA[i] = 0;
			}
	}
	public static boolean checkX(int a, int userInput, int b) { // checks the userinput is the right x
		System.out.println(a + " " + userInput + " " + b);
		if(userInput*a == b){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public static boolean isNumeric(String string) {  
		if(string == null || string.equals("")) {
	        return false; // if it empty
	    }
	    
	    try {
	        return true; // tries to convert the string to integer 
	    } catch (NumberFormatException e) {	//if it catches an error it means it contains a string
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
