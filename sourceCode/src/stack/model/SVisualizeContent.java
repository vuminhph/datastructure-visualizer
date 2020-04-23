package stack.model;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SVisualizeContent extends VBox{
	
	TextField valueInput;
	Button pushButton;
	Button popButton;
	
	public TextField getValueInput() {
		return valueInput;
	}

	public Button getPushButton() {
		return pushButton;
	}

	public Button getPopButton() {
		return popButton;
	}

	public SVisualizeContent() {
		super();
		
		this.valueInput = new TextField("Enter node Value");
		this.valueInput.getStylesheets().add("textField.css");
		
		this.valueInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(valueInput.getText().equals("Enter node Value"))
					valueInput.setText("");
			}
		});
		this.valueInput.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(valueInput.getText().equals("Enter node Value"))
					valueInput.setText("");
			}
		});
		
		this.pushButton = new Button("PUSH");
		this.popButton = new Button("POP");
		this.pushButton.getStylesheets().add("actionLayerButton.css");
		this.popButton.getStylesheets().add("actionLayerButton.css");
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth() / 8;
		int height = gd.getDisplayMode().getHeight() / 20;
		
//		Action Menu
		HBox actionMenu = new HBox();
		actionMenu.setSpacing(20);
		double actionMenuHeight = height * 1.5;
		actionMenu.setMaxHeight(actionMenuHeight);
		actionMenu.setMinHeight(actionMenuHeight);
		actionMenu.setAlignment(Pos.CENTER);
		actionMenu.getChildren().addAll(valueInput,pushButton,popButton);
		this.getChildren().add(actionMenu);
		valueInput.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case ENTER: pushButton.fire();
				default:
					break;
				}
			}
		});
		
//		Stack Layer
		StackContainer stack = new StackContainer(width, height);
		int stackHeight = stack.getStackHeight();
		
		StackPane stackLayer = new StackPane();
		VBox.setVgrow(stackLayer, Priority.ALWAYS);
		stackLayer.setPrefHeight(gd.getDisplayMode().getHeight() * 17/20 - actionMenuHeight);
		stackLayer.setAlignment(Pos.CENTER);
		stackLayer.getChildren().add(stack);
		stackLayer.setStyle("-fx-background-color: whitesmoke; -fx-opacity: 0.9");
		this.getChildren().add(stackLayer);
		
		Text fullText = new Text();
		actionMenu.setViewOrder(0);
		stackLayer.setViewOrder(1);
		
//		init node
		ArrayList<SNode> Nodes = new ArrayList<SNode>();
		SNode node = new SNode(width,height);
		Nodes.add(node);
		stackLayer.getChildren().add(node);
		node.setVisible(false);
		
		//		pushButton ACTION
		pushButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean numeric = true;
				try {
					@SuppressWarnings("unused")
					Double num = Double.parseDouble(valueInput.getText());
				}catch(NumberFormatException e) {
					numeric = false;
				}
				if(numeric == true) {
					if ((Nodes.size() - 1) * height == stackHeight) {
						Alert fullAlert = new Alert(AlertType.ERROR);
						fullAlert.setTitle("Push ERROR");
						fullAlert.setHeaderText(null);
						fullAlert.setContentText("The stack is full");
						fullAlert.showAndWait();
					}
					else {
						if (Nodes.size() != 1) Nodes.get(Nodes.size() - 2).getBody().setFill(Color.BISQUE);
					
						String Num = valueInput.getText();
						SNode node = Nodes.get(Nodes.size() - 1);
						node.setValue(Num);
						node.setVisible(true);
						
						Path pushPath = new Path();
						pushPath.getElements().add(new MoveTo(width/2 + 5,height));
						pushPath.getElements().add(new LineTo(stackLayer.getWidth() / 2,height));
						pushPath.getElements().add(new LineTo(stackLayer.getWidth() / 2, stack.getLayoutY() + (stack.getStackHeight() - height * .5 - 5)));
						stack.setStackHeight(stack.getStackHeight() - height);
											
						PathTransition pushTrans = new PathTransition();
						pushTrans.setDuration(Duration.millis(1000));
						pushTrans.setNode(node);
						pushTrans.setPath(pushPath);
						
						pushTrans.play();
						
						valueInput.clear();
						valueInput.requestFocus();
						
						SNode newNode = new SNode(width,height);
						newNode.getBody().setFill(Paint.valueOf("#5385BF"));
						Nodes.add(newNode);
						stackLayer.getChildren().add(newNode);
						newNode.setVisible(false); 
						
						if ((Nodes.size() - 1) * height == stackHeight) {
							popButton.requestFocus();
							fullText.setText("The Stack is now Full!");
							stackLayer.getChildren().add(fullText);
							StackPane.setAlignment(fullText, Pos.BOTTOM_CENTER);
						}
					}
				}
				else {
					Alert wrongInput = new Alert(AlertType.WARNING);
					wrongInput.setTitle("Input ERROR");
					wrongInput.setHeaderText(null);
					wrongInput.setContentText("Invalid Input: Value Has To Be A Number! ");
					wrongInput.showAndWait();
				}
			}
		});
		
//		popButton ACTION
		popButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				if(Nodes.size() == 1) {
					Alert emptyAlert = new Alert(AlertType.ERROR);
					emptyAlert.setTitle("Pop ERROR");
					emptyAlert.setHeaderText(null);
					emptyAlert.setContentText("The stack is empty");
					emptyAlert.showAndWait();
				}
				else {
					popButton.setDisable(true);
					Nodes.get(Nodes.size() - 2).getBody().setFill(Paint.valueOf("#5385BF"));
					Path popPath = new Path();
					popPath.getElements().add(new MoveTo(stackLayer.getWidth() / 2, stack.getLayoutY() + (stack.getStackHeight() - height * .5 - 5)));
					popPath.getElements().add(new LineTo(stackLayer.getWidth() / 2,height));
					popPath.getElements().add(new LineTo(stackLayer.getWidth() - 5 - width/2,height));
					
					PathTransition popTrans = new PathTransition();
					popTrans.setDuration(Duration.millis(1000));
					popTrans.setNode(Nodes.get(Nodes.size() - 2));
					popTrans.setPath(popPath);
					
					popTrans.play();
					
					popTrans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							stackLayer.getChildren().remove(Nodes.get(Nodes.size() - 2));
							Nodes.remove(Nodes.size() - 2);
							popButton.setDisable(false);
						}
					});
					stack.setStackHeight(stack.getStackHeight() + height);
					if((Nodes.size() - 1) * height <= stackHeight) {
						stackLayer.getChildren().remove(fullText);
					}
				}
			}
			
		});
	}
}
