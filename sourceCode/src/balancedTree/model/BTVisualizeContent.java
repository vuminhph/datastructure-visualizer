package balancedTree.model;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BTVisualizeContent extends VBox{
	
	TextField valueInput; 
	TextField valueOutput;
	Button insertButton;
	Button deleteButton;
	
	public Button getInsertButton() {
		return insertButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public TextField getValueInput() {
		return valueInput;
	}

	public TextField getValueOutput() {
		return valueOutput;
	}

	public int count = 0;
	public BTVisualizeContent() {
		super();
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		float width = gd.getDisplayMode().getWidth() / 20;
		float height = gd.getDisplayMode().getHeight() / 20;
		
		float rootPos = height * 10;
//		action Layer
		double actionMenuHeight = height * 1.5;
		GridPane actionLayer = new GridPane();
		actionLayer.setPadding(new Insets(20));
		actionLayer.setHgap(20);
		actionLayer.setVgap(15);
		actionLayer.setMaxHeight(actionMenuHeight);
		actionLayer.setMinHeight(actionMenuHeight);
		actionLayer.setAlignment(Pos.CENTER);
		
//			insert
		this.valueInput = new TextField("Enter node Value");
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
		
		this.valueInput.setAlignment(Pos.CENTER_LEFT);
		this.valueInput.getStylesheets().add("textField.css");
		actionLayer.add(valueInput, 0, 0);
		valueInput.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case ENTER: insertButton.fire();
				default:
					break;
				}
			}
		});
		
		this.insertButton = new Button("INSERT");
		this.insertButton.getStylesheets().add("actionLayerButton.css");
		actionLayer.add(insertButton, 1, 0);
		
		
//			delete
		this.valueOutput = new TextField("Enter node Value");
		this.valueOutput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(valueOutput.getText().equals("Enter node Value"))
					valueOutput.setText("");
			}
		});
		this.valueOutput.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(valueOutput.getText().equals("Enter node Value"))
					valueOutput.setText("");
			}
		});
		
		this.valueOutput.setAlignment(Pos.CENTER_LEFT);
		this.valueOutput.getStylesheets().add("textField.css");
		actionLayer.add(valueOutput, 0, 1);
		valueOutput.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case ENTER: deleteButton.fire();
				default:
					break;
				}
			}
		});
		this.deleteButton = new Button("DELETE");
		this.deleteButton.getStylesheets().add("actionLayerButton.css");
		actionLayer.add(deleteButton, 1, 1);
		

//		Balanced Tree layer
		BTContainer balancedTree = new BTContainer();
		Pane balancedTreeLayer = new Pane();
		VBox.setVgrow(balancedTreeLayer, Priority.ALWAYS);
		balancedTreeLayer.setStyle("-fx-background-color: whitesmoke; -fx-opacity: 0.9");
		
		double disX = 600, disY = 100;
		double diameter = 40;
		
//		insert ACTION
		insertButton.setOnAction(new EventHandler<ActionEvent>() {
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
					balancedTree.stack.clear();
					insertButton.setDisable(true);
					deleteButton.setDisable(true);
					Circle cir = new Circle(disX + 20,disY + 20,20,Color.TRANSPARENT);
					cir.setStrokeWidth(3);
					cir.setStroke(Color.LIGHTSALMON);
					if(balancedTree.Root != null) {
						balancedTreeLayer.getChildren().add(cir);
						count = balancedTree.Root.height + 1;
					}
					
					String value = valueInput.getText();
					
					BTNode node = new BTNode(diameter,value);
					balancedTreeLayer.getChildren().add(node.treeNode);
					balancedTree.insertNavigateAnimation(balancedTree.Root, node, cir,disX,disY);
					
					TranslateTransition trans1 = new TranslateTransition();
					trans1.setDuration(Duration.millis(count*200));
					trans1.setNode(new Circle(1));
					trans1.setToY(rootPos);
					trans1.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
					    public void handle(ActionEvent event) {

							balancedTree.Root = balancedTree.insert(balancedTree.Root, node,disX, disY, 25);
							System.out.println("ok");
						}
					});
					trans1.play();

					TranslateTransition trans = new TranslateTransition();
					trans.setDuration(Duration.millis((count*2)* 200));
					trans.setNode(new Circle(1));
					trans.setToY(rootPos);
					trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
					    public void handle(ActionEvent event) {

							balancedTree.traverseBalance(cir,balancedTreeLayer,Integer.parseInt(valueInput.getText()),disX,disY);
							for (int i = 0; i < balancedTree.line.size();i++)
								balancedTreeLayer.getChildren().remove(balancedTree.line.get(i));
							balancedTree.line.clear();
							insertButton.setDisable(false);
							deleteButton.setDisable(false);
						}
					});
					trans.play();
					
					TranslateTransition trans2 = new TranslateTransition();
					trans2.setDuration(Duration.millis((count*3+1)*200));
					trans2.setNode(new Circle(1));
					trans2.setToY(rootPos);
					trans2.play();
					trans2.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
					    public void handle(ActionEvent event) {
							balancedTreeLayer.getChildren().remove(cir);
							balancedTree.addLines(balancedTree.Root, balancedTreeLayer);
				}
					});
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
		
//		Delete ACTION
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				insertButton.setDisable(true);
				deleteButton.setDisable(true);
				String value = valueInput.getText();
				balancedTree.stack.clear();
				balancedTree.key = Integer.parseInt(value);
				Circle cir = new Circle(620,120,20,Color.TRANSPARENT);
				cir.setStrokeWidth(3);
				cir.setStroke(Color.BLUE);
				if(balancedTree.Root != null) {
					balancedTreeLayer.getChildren().add(cir);
					count = balancedTree.Root.height + 1;
				}
				balancedTree.deleteAnimation(balancedTree.Root, balancedTree.key, cir,disX,disY);
				TranslateTransition trans1 = new TranslateTransition();
				trans1.setDuration(Duration.millis(count*200));
				trans1.setNode(new Circle(1));
				trans1.setToY(rootPos);
				trans1.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
				    public void handle(ActionEvent event) {
						balancedTreeLayer.getChildren().remove(cir);
						for (int i = 0; i < balancedTree.line.size();i++)
							balancedTreeLayer.getChildren().remove(balancedTree.line.get(i));
						balancedTree.line.clear();
						balancedTree.Root = balancedTree.deleteNode(balancedTree.Root,Integer.parseInt(value),balancedTreeLayer,disX,disY);
						if (balancedTree.isDeletable(balancedTree.Root, Integer.parseInt(value)) == true)
							balancedTree.changeCoordinate(balancedTree.Root, 600, 100);
					}
				});
				trans1.play();
				
				TranslateTransition trans = new TranslateTransition();
				trans.setDuration(Duration.millis(count*2*200));
				trans.setNode(new Circle(1));
				trans.setToY(rootPos);
				trans.play();
				trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
				    public void handle(ActionEvent event) {
						
						balancedTree.Root = balancedTree.deleteNodeSelfBalancing(balancedTree.Root,Integer.parseInt(value),balancedTreeLayer,disX,disY);
						balancedTree.changeCoordinate(balancedTree.Root, 600, 100);
						
						insertButton.setDisable(false);
						deleteButton.setDisable(false);
					}
				});
				TranslateTransition trans2 = new TranslateTransition();
				trans2.setDuration(Duration.millis((count*2+1) * 200));
				trans2.setNode(new Circle(1));
				trans2.setToY(rootPos);
				trans2.play();
				trans2.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
				    public void handle(ActionEvent event) {
						balancedTree.addLines(balancedTree.Root, balancedTreeLayer);
					}
				
				});
			}
		});
			
		this.getChildren().addAll(actionLayer,balancedTreeLayer);
		balancedTreeLayer.setViewOrder(0);
		balancedTreeLayer.setViewOrder(1);
		
	}

}
