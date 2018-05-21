import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Drop extends Application implements EventHandler<ActionEvent>{
	public static void main(String[] args) {
		launch(args);
	}
	
	Button createTextField = new Button();
	TextField[] tfArray = new TextField[10]; // make these statements expandable
	int tfCounter = 0;

	Button createTextArea = new Button();
	TextArea[] taArray = new TextArea[10];
	int taCounter = 0;
	
	Button createButton = new Button();
	Button[] bArray = new Button[10];
	int bCounter = 0;
	
	Pane firstPane = new Pane();
	
	public void start(Stage primaryStage) {
	  System.out.println("Hello world");
		Stage firstStage = new Stage();
		Scene scene = new Scene(firstPane,500,500);
		firstStage.setScene(scene);
		firstStage.setTitle("GUI Maker");
		firstStage.show();
		Label toolLabel = new Label("Tools:   ");
		toolLabel.setFont(Font.font("Times New Roman", 16));
		HBox toolBar = new HBox();
		toolBar.getChildren().addAll(toolLabel, createTextField, createButton,  createTextArea);
		firstPane.getChildren().add(toolBar);
		createTextArea.setText("TA");
		createTextArea.setOnAction(this);
		createTextField.setText("TF");
		createTextField.setOnAction(this);
		createButton.setText("B");
		createButton.setOnAction(this);
	}
	
	public void handle(ActionEvent e) {
		if(e.getSource().equals(createTextField)) {
			tfArray[tfCounter] = new TextField();
			tfArray[tfCounter].setLayoutX(250);
			tfArray[tfCounter].setLayoutY(250);
			//tfArray[tfCounter].setOnDragDetected(this); //need to figure out what the hell to put in here in order to make it able to move TextFields
			firstPane.getChildren().add(tfArray[tfCounter]);
			tfCounter++;
		}
		if(e.getSource().equals(createTextArea)) {
			taArray[taCounter] = new TextArea();
			taArray[taCounter].setLayoutX(250);
			taArray[taCounter].setLayoutY(250);
			firstPane.getChildren().add(taArray[taCounter]);
			taCounter++;
		}
		if(e.getSource().equals(createButton)) {
			bArray[bCounter] = new Button();
			bArray[bCounter].setLayoutX(250);
			bArray[bCounter].setLayoutY(250);
			firstPane.getChildren().add(bArray[bCounter]);
			bCounter++;
		}
	}
}