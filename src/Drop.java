import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Drop extends Application implements EventHandler<ActionEvent>{
	public static void main(String[] args) {
		launch(args);
	}
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
	List<Double> tfXCoordinates = new ArrayList<Double>();
	List<Double> tfYCoordinates = new ArrayList<Double>();
	List<Double> bXCoordinates = new ArrayList<Double>();
	List<Double> bYCoordinates = new ArrayList<Double>();
	
	Button createTextField = new Button();
	List<TextField> tfArray = new ArrayList<TextField>();
	int tfCounter = 0;

	Button createTextArea = new Button();
	TextArea[] taArray = new TextArea[10];
	int taCounter = 0;
	
	Button createButton = new Button();
	Button[] bArray = new Button[10];
	int bCounter = 0;
	
	Button submit = new Button();
	
	Pane firstPane = new Pane();
	
	public void start(Stage primaryStage) {
		Stage firstStage = new Stage();
		Scene scene = new Scene(firstPane,500,500);
		firstStage.setScene(scene);
		firstStage.setTitle("GUI Maker");
		firstStage.show();
		Label toolLabel = new Label("Tools:   ");
		toolLabel.setFont(Font.font("Times New Roman", 16));
		HBox toolBar = new HBox();
		toolBar.getChildren().addAll(toolLabel, createTextField, createButton,  createTextArea, submit);
		firstPane.getChildren().add(toolBar);
		submit.setText("Create");
		submit.setOnAction(this);
		createTextArea.setText("TA");
		createTextArea.setOnAction(this);
		createTextField.setText("TF");
		createTextField.setOnAction(this);
		createButton.setText("B");
		createButton.setOnAction(this);
	}
	
	public void handle(ActionEvent e) {
		if(e.getSource().equals(createTextField)) {
			tfArray.add(new TextField());
			tfArray.get(tfCounter).setOnMousePressed(nodeOnMousePressedHandler);
			tfArray.get(tfCounter).setOnMouseDragged(nodeOnMouseDraggedHandler);
			tfArray.get(tfCounter).setLayoutX(250);
			tfArray.get(tfCounter).setLayoutY(250);
			firstPane.getChildren().add(tfArray.get(tfCounter));
			tfCounter++;
		}
		if(e.getSource().equals(createTextArea)) {
			taArray[taCounter] = new TextArea();
			taArray[taCounter].setOnMousePressed(nodeOnMousePressedHandler);
			taArray[taCounter].setOnMouseDragged(nodeOnMouseDraggedHandler);
			taArray[taCounter].setLayoutX(250);
			taArray[taCounter].setLayoutY(250);
			firstPane.getChildren().add(taArray[taCounter]);
			taCounter++;
		}
		if(e.getSource().equals(createButton)) {
			bArray[bCounter] = new Button();
			bArray[bCounter].setOnMousePressed(nodeOnMousePressedHandler);
			bArray[bCounter].setOnMouseDragged(nodeOnMouseDraggedHandler);
			bArray[bCounter].setLayoutX(250);
			bArray[bCounter].setLayoutY(250);
			firstPane.getChildren().add(bArray[bCounter]);
			bCounter++;
		}
		if(e.getSource().equals(submit)) {
			for(TextField a : tfArray) {
				int counter = 0;
				tfXCoordinates.add(a.getLayoutX());
				tfYCoordinates.add(a.getLayoutY());
				counter++;
			}
			for(Button b : bArray) {
				int counter = 0;
				//bXCoordinates[counter] = b.getLayoutX();
				//bYCoordinates[counter] = b.getLayoutY();
				counter++;
			}
			generateCode();
		}
	}
	public void generateCode() {
		for(TextField a : tfArray) {
			int counter = 0;
			System.out.println("TextField " + "name " + " = new TextField();"); //replace name with a variable name that makes sense later
			double x = tfXCoordinates.get(counter);
			double y = tfYCoordinates.get(counter);
			Double.toString(x);
			Double.toString(y);
			System.out.println("name.setLayoutX("+ (int)x + ");");
			System.out.println("name.setLayoutY("+ (int)y + ");");
		}
	}
	EventHandler<MouseEvent> nodeOnMousePressedHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
		}
	};
	EventHandler<MouseEvent> nodeOnMouseDraggedHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			((Node)(t.getSource())).setTranslateX(newTranslateX);
			((Node)(t.getSource())).setTranslateY(newTranslateY);
		}
	};
}