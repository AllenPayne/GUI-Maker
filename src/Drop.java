import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	List<String> tfNames = new ArrayList<String>();
	List<Double> tfXCoordinates = new ArrayList<Double>();
	List<Double> tfYCoordinates = new ArrayList<Double>();
	List<String> bNames = new ArrayList<String>();
	List<Double> bXCoordinates = new ArrayList<Double>();
	List<Double> bYCoordinates = new ArrayList<Double>();
	
	Button createTextField = new Button();
	List<TextField> tfArray = new ArrayList<TextField>();
	int tfCounter = 0;

	Button createTextArea = new Button();
	TextArea[] taArray = new TextArea[10];
	int taCounter = 0;
	
	Button createButton = new Button();
	List<Button> bArray = new ArrayList<Button>();
	int bCounter = 0;
	
	Button createLabel = new Button();
	List<Label> lArray = new ArrayList<Label>();
	int lCounter = 0;
	
	Button submit = new Button();
	
	Pane firstPane = new Pane();
	
	public void start(Stage primaryStage) {
		Stage firstStage = new Stage();
		Scene scene = new Scene(firstPane,500,500);
		firstStage.setScene(scene);
		firstStage.setTitle("GUI Maker");
		firstStage.show();
		Label toolLabel = new Label("Tools:   ");
		//Label trash = new Label("Trash");
		//trash.setFont(Font.font("Times New Roman", 20));
		//trash.setLayoutX(425);
		//trash.setLayoutY(25);
		toolLabel.setFont(Font.font("Times New Roman", 16));
		HBox toolBar = new HBox();
		toolBar.getChildren().addAll(toolLabel, createTextField, createButton,  createTextArea, createLabel, submit);
		firstPane.getChildren().add(toolBar);
		submit.setText("Create");
		submit.setOnAction(this);
		createTextArea.setText("TA");
		createTextArea.setOnAction(this);
		createTextField.setText("TF");
		createTextField.setOnAction(this);
		createButton.setText("B");
		createButton.setOnAction(this);
		createLabel.setText("L");
	}
	
	public void handle(ActionEvent e) {
		if(e.getSource().equals(createTextField)) {
			tfArray.add(new TextField());
			TextInputDialog tfDialog = new TextInputDialog();
			tfDialog.setTitle("Nameth your node");
			tfDialog.setContentText("Nameth here: ");
			Optional<String> result = tfDialog.showAndWait();
			result.ifPresent(name -> tfNames.add(name));
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
			bArray.add(new Button());
			TextInputDialog bDialog = new TextInputDialog();
			bDialog.setTitle("Nameth your node");
			bDialog.setContentText("Nameth here: ");
			Optional<String> result = bDialog.showAndWait();
			result.ifPresent(name -> bNames.add(name));
			bArray.get(bCounter).setOnMousePressed(nodeOnMousePressedHandler);
			bArray.get(bCounter).setOnMouseDragged(nodeOnMouseDraggedHandler);
			bArray.get(bCounter).setLayoutX(250);
			bArray.get(bCounter).setLayoutY(250);
			firstPane.getChildren().add(bArray.get(bCounter));
			bCounter++;
		}
		if(e.getSource().equals(submit)) {
			for(TextField a : tfArray) {
				tfXCoordinates.add(a.getLayoutX());
				tfYCoordinates.add(a.getLayoutY());
			}
			for(Button b : bArray) {
				bXCoordinates.add(b.getLayoutX());
				bYCoordinates.add(b.getLayoutY());
			}
			generateCode();
		}
	}
	public void generateCode() {
		int textFieldCount = 0;
		int buttonCount = 0;
		int textAreaCount = 0;
		for(TextField a : tfArray) {
			String name = tfNames.get(textFieldCount);
			System.out.println("TextField " + name + " = new TextField();"); //replace name with a variable name that makes sense later
			double x = tfXCoordinates.get(textFieldCount);
			double y = tfYCoordinates.get(textFieldCount);
			Double.toString(x);
			Double.toString(y);
			System.out.println(name + ".setLayoutX("+ (int)x + ");");
			System.out.println(name + ".setLayoutY("+ (int)y + ");");
			textFieldCount++;
		}
		for(Button b : bArray) {
			String name = bNames.get(buttonCount);
			System.out.println("Button " + name + " = new Button();"); //replace name with variable names from user
			double x = bXCoordinates.get(buttonCount);
			double y = bYCoordinates.get(buttonCount);
			Double.toString(x);
			Double.toString(y);
			System.out.println(name+ ".setLayoutX(" + (int)x +");");
			System.out.println(name+ ".setLayoutY(" + (int)y +");");
			buttonCount++;
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