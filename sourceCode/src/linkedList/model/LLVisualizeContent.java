package linkedList.model;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import linkedList.path.InsertAtPos;
import linkedList.path.deleteAtPos;
import linkedList.path.moveBackAtPos;
import linkedList.path.moveFrontAtPos;

public class LLVisualizeContent extends VBox{
	
	TextField valueInput; 
	SplitMenuButton insertMenu;
	SplitMenuButton deleteMenu;
	
	public SplitMenuButton getInsertMenu() {
		return insertMenu;
	}

	public SplitMenuButton getDeleteMenu() {
		return deleteMenu;
	}

	public TextField getValueInput() {
		return valueInput;
	}

	public LLVisualizeContent() {
		super();
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		float width = gd.getDisplayMode().getWidth() / 20;
		float height = gd.getDisplayMode().getHeight() / 20;
		
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
				case ENTER: insertMenu.fire();
				default:
					break;
				}
			}
		});
		
		MenuItem insertFirstButton = new MenuItem("Insert First");
		MenuItem insertLastButton = new MenuItem("Insert Last");
		MenuItem insertPosButton = new MenuItem("Insert at Position");
		this.insertMenu = new SplitMenuButton(insertFirstButton,insertLastButton,insertPosButton);
		this.insertMenu.getStylesheets().add("splitMenu.css");
		this.insertMenu.setText("INSERT LAST");
		actionLayer.add(insertMenu, 1, 0);
		
		TextField insertPosInput = new TextField("Enter Node's Position");
		insertPosInput.setVisible(false);
		insertPosInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(insertPosInput.getText().equals("Enter Node's Position"))
					insertPosInput.setText("");
			}
		});
		insertPosInput.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(insertPosInput.getText().equals("Enter Node's Position"))
					insertPosInput.setText("");
			}
		});
		insertPosInput.setAlignment(Pos.CENTER_LEFT);
		insertPosInput.getStylesheets().add("textField.css");
		
		actionLayer.add(insertPosInput, 2, 0);
		
		insertLastButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				insertPosInput.setVisible(false);
				insertMenu.setText("INSERT LAST");
			}
		});
		
		insertPosButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				insertPosInput.setVisible(true);
				insertMenu.setText("INSERT AT POSITION");
			}
		});
		
		insertFirstButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				insertPosInput.setVisible(false);
				insertMenu.setText("INSERT FIRST");
				
			}
		});
		
//		
		
//			delete
		MenuItem deleteFirstButton = new MenuItem("Delete First");
		MenuItem deleteLastButton = new MenuItem("Delete Last");
		MenuItem deletePosButton = new MenuItem("Delete at Position");
		this.deleteMenu = new SplitMenuButton(deleteFirstButton,deleteLastButton,deletePosButton);
		this.deleteMenu.setText("DELETE LAST");
		this.deleteMenu.getStylesheets().add("splitMenu.css");
		actionLayer.add(deleteMenu, 1, 1);
		
		TextField deletePosInput = new TextField("Enter Node's Position");
		deletePosInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(deletePosInput.getText().equals("Enter Node's Position"))
					deletePosInput.setText("");
			}
		});
		deletePosInput.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(deletePosInput.getText().equals("Enter Node's Position"))
					deletePosInput.setText("");
			}
		});
		deletePosInput.setVisible(false);
		deletePosInput.getStylesheets().add("textField.css");
		
		actionLayer.add(deletePosInput, 2, 1);
		
		deleteLastButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deletePosInput.setVisible(false);
				deleteMenu.setText("DELETE LAST");
			}
		});
		
		deletePosButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deletePosInput.setVisible(true);
				deleteMenu.setText("DELETE AT POSITION");
			}
		});
		
		deleteFirstButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deletePosInput.setVisible(false);
				deleteMenu.setText("DELETE FIRST");
				
			}
		});
//		
		
//		
		
//		LinkedList layer
		linkedListContainer linkedList = new linkedListContainer(width,height);
		linkedList.setMaxSize(width * 10, height * 2);
		StackPane linkedListLayer = new StackPane();
		VBox.setVgrow(linkedListLayer, Priority.ALWAYS);
		linkedListLayer.setStyle("-fx-background-color: whitesmoke; -fx-opacity: 0.9");
		linkedListLayer.getChildren().add(linkedList);
		linkedList.setLayoutX(width * 7);
		linkedList.setLayoutY(height * 5);
//		
		
		
//		init node
		ArrayList<LLNode> Nodes = new ArrayList<>();
		LLNode node = new LLNode(width, height);
		Nodes.add(node);
		
		linkedListLayer.getChildren().add(node);
		
		node.setAlignment(null);
		node.getBody().setFill(Paint.valueOf("#5385BF"));
		node.setVisible(false);
		
		Text fullText = new Text();
	
//		insert ACTION
		insertMenu.setOnAction(new EventHandler<ActionEvent>() {
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
					if (Nodes.size() -1  == 10) {
						Alert fullAlert = new Alert(AlertType.ERROR);
						fullAlert.setTitle("Insert ERROR");
						fullAlert.setHeaderText(null);
						fullAlert.setContentText("The List is full");
						fullAlert.showAndWait();
					}
					else {
						for(int i = 0; i < Nodes.size() - 1; i++) {
							Nodes.get(i).getBody().setFill(Color.BISQUE);
						}
						
						String Num = valueInput.getText();
						LLNode node = Nodes.get(Nodes.size() - 1);
						node.setValue(Num);
						node.setVisible(true);
						
//						Transition for insert last
						if(insertMenu.getText().equals("INSERT LAST")) {
							
							InsertAtPos insertLastPath = new InsertAtPos(Nodes.size() -1, linkedListLayer,width,height);
							PathTransition insertLastTrans = new PathTransition();
							insertLastTrans.setDuration(Duration.millis(750));
							insertLastTrans.setNode(node);
							insertLastTrans.setPath(insertLastPath);
							
							insertLastTrans.play();
							
							valueInput.clear();
							valueInput.requestFocus();
							
							if(Nodes.size() == 10) {
								deleteMenu.requestFocus();
								
								fullText.setText("The List is now Full!");
								linkedListLayer.getChildren().add(fullText);
								StackPane.setAlignment(fullText, Pos.BOTTOM_CENTER);
							}
							LLNode newNode = new LLNode(width,height);
							newNode.getBody().setFill(Paint.valueOf("#5385BF"));
							Nodes.add(newNode);
							
							linkedListLayer.getChildren().add(newNode);
							
							newNode.setAlignment(Pos.CENTER);
							newNode.setVisible(false);
						}
						
//						Transition for insertFirst
						else if(insertMenu.getText().equals("INSERT FIRST")) {
							
							for(int i = Nodes.size() - 1; i >= 0; i -- ) {
								moveBackAtPos movePath = new moveBackAtPos(i,linkedListLayer,width,height);
								
								PathTransition moveTrans = new PathTransition();
								moveTrans.setDuration(Duration.millis(750));
								moveTrans.setNode(Nodes.get(i));
								moveTrans.setPath(movePath);
								moveTrans.play();
							}
							InsertAtPos insertFirstPath = new InsertAtPos(0,linkedListLayer,width,height);
							PathTransition insertFirstTrans = new PathTransition();
							insertFirstTrans.setDuration(Duration.millis(750));
							insertFirstTrans.setNode(node);
							insertFirstTrans.setPath(insertFirstPath);
							insertFirstTrans.play();
							Nodes.remove(Nodes.size() -1);
							Nodes.add(0, node);
							
							valueInput.clear();
							valueInput.requestFocus();
							
							if(Nodes.size() == 10) {
								deleteMenu.requestFocus();
								
								fullText.setText("The List is now Full!");
								linkedListLayer.getChildren().add(fullText);
								StackPane.setAlignment(fullText, Pos.BOTTOM_CENTER);
							}
							LLNode newNode = new LLNode(width,height);
							newNode.getBody().setFill(Paint.valueOf("#5385BF"));
							Nodes.add(newNode);
							linkedListLayer.getChildren().add(newNode);
							newNode.setAlignment(Pos.CENTER);
							newNode.setVisible(false);
						}
						
//						Transition for insertAtPos
						else {
							node.setVisible(false);
							insertPosInput.requestFocus();
							boolean numericPos = true;
							try {
								@SuppressWarnings("unused")
								int num = Integer.valueOf(insertPosInput.getText());
							}catch(NumberFormatException e) {
								numericPos = false;
							}
							if(numericPos == true) {
								int position = Integer.parseInt(insertPosInput.getText());
								if(position < 0 || position > Nodes.size()) {
									Alert wrongPos = new Alert(AlertType.ERROR);
									wrongPos.setTitle("Input ERROR");
									wrongPos.setHeaderText(null);
									wrongPos.setContentText("Invalid Input: Position Out Of Bound ");
									wrongPos.showAndWait();
								}
								else {
									node.setVisible(true);
									for(int i = Nodes.size() - 1;i >= position - 1;i--) {
										moveBackAtPos movePath = new moveBackAtPos(i,linkedListLayer,width,height);
									
										PathTransition moveTrans = new PathTransition();
										moveTrans.setDuration(Duration.millis(750));
										moveTrans.setNode(Nodes.get(i));
										moveTrans.setPath(movePath);
										moveTrans.play();
									}
									InsertAtPos insertPosPath = new InsertAtPos(position - 1 ,linkedListLayer,width,height);
									PathTransition insertFirstTrans = new PathTransition();
									insertFirstTrans.setDuration(Duration.millis(750));
									insertFirstTrans.setNode(node);
									insertFirstTrans.setPath(insertPosPath);
									insertFirstTrans.play();
									Nodes.remove(Nodes.size() -1);
									Nodes.add(position - 1, node);
									
									valueInput.clear();
									valueInput.requestFocus();
								
									if(Nodes.size() == 10) {
										deleteMenu.requestFocus();
									
										fullText.setText("The List is now Full!");
										linkedListLayer.getChildren().add(fullText);
										StackPane.setAlignment(fullText, Pos.BOTTOM_CENTER);
									}
									LLNode newNode = new LLNode(width,height);
									newNode.getBody().setFill(Paint.valueOf("#5385BF"));
									Nodes.add(newNode);
									linkedListLayer.getChildren().add(newNode);
									newNode.setAlignment(Pos.CENTER);
									newNode.setVisible(false);

								}
							}
							else {
								Alert wrongInput = new Alert(AlertType.WARNING);
								wrongInput.setTitle("Input ERROR");
								wrongInput.setHeaderText(null);
								wrongInput.setContentText("Invalid Input: Position Has To Be An Integer Number! ");
								wrongInput.showAndWait();
							}
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
		
//		Delete ACTION
		deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				if(Nodes.size() == 1) {
					Alert emptyAlert = new Alert(AlertType.ERROR);
					emptyAlert.setTitle("Delete ERROR");
					emptyAlert.setHeaderText(null);
					emptyAlert.setContentText("The List is empty");
					emptyAlert.showAndWait();
				}
				else {
					deleteMenu.setDisable(true);
					for(int i = 0; i < Nodes.size() - 1; i++) {
						Nodes.get(i).getBody().setFill(Color.BISQUE);
					}

//					Delete Last
					if(deleteMenu.getText().equals("DELETE LAST")) {
						Nodes.get(Nodes.size() - 2).getBody().setFill(Paint.valueOf("#5385BF"));
						deleteAtPos deleteLastPath = new deleteAtPos(Nodes.size() - 2,linkedListLayer,width,height);
						PathTransition deleteLastTrans = new PathTransition();
						deleteLastTrans.setNode(Nodes.get(Nodes.size() - 2));
						deleteLastTrans.setDuration(Duration.millis(750));
						deleteLastTrans.setPath(deleteLastPath);
						
						deleteLastTrans.play();

						deleteLastTrans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								linkedListLayer.getChildren().remove(Nodes.get(Nodes.size() -2));
								Nodes.remove(Nodes.size() -2);
								deleteMenu.setDisable(false);
							}
						});
						if(Nodes.size() - 1 <= 10) {
							linkedListLayer.getChildren().remove(fullText);
						}
					}
//					Delete First
					else if(deleteMenu.getText().equals("DELETE FIRST")) {
						
						Nodes.get(0).getBody().setFill(Paint.valueOf("#5385BF"));
						
						deleteAtPos deleteFirstPath = new deleteAtPos(0,linkedListLayer,width,height);
						PathTransition deleteFirstTrans = new PathTransition();
						deleteFirstTrans.setDuration(Duration.millis(750));
						deleteFirstTrans.setNode(Nodes.get(0));
						deleteFirstTrans.setPath(deleteFirstPath);
						deleteFirstTrans.play();
						
						for(int i = 1; i < Nodes.size(); i++) {
							moveFrontAtPos moveFront = new moveFrontAtPos(i,linkedListLayer,width,height);
							PathTransition moveFrontTrans = new PathTransition();
							moveFrontTrans.setDuration(Duration.millis(750));
							moveFrontTrans.setNode(Nodes.get(i));
							moveFrontTrans.setPath(moveFront);
							moveFrontTrans.play();
						}
						deleteFirstTrans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								linkedListLayer.getChildren().remove(Nodes.get(0));
								Nodes.remove(0);
								deleteMenu.setDisable(false);
							}
						});
						if(Nodes.size() - 1 <= 10) {
							linkedListLayer.getChildren().remove(fullText);
						}
					}
//					Delete At Pos
					else {
						deletePosInput.requestFocus();
						boolean numericPos = true;
						try {
							@SuppressWarnings("unused")
							Double num = Double.parseDouble(deletePosInput.getText());
						}catch(NumberFormatException e) {
							numericPos = false;
						}
						if(numericPos == true) {
							int position = Integer.parseInt(deletePosInput.getText());
							if(position < 0 || position >= Nodes.size()) {
								Alert wrongPos = new Alert(AlertType.ERROR);
								wrongPos.setTitle("Input ERROR");
								wrongPos.setHeaderText(null);
								wrongPos.setContentText("Invalid Input: Position Out Of Bound ");
								wrongPos.showAndWait();
							}
							else {
								Nodes.get(position - 1).getBody().setFill(Paint.valueOf("#5385BF"));
								deleteAtPos deletePosPath = new deleteAtPos(position - 1, linkedListLayer,width,height);
								PathTransition deletePosTrans = new PathTransition();
								deletePosTrans.setNode(Nodes.get(position - 1));
								deletePosTrans.setDuration(Duration.millis(750));
								deletePosTrans.setPath(deletePosPath);
								deletePosTrans.play();
							
								for(int i = position; i < Nodes.size();i++) {
									moveFrontAtPos moveFront = new moveFrontAtPos(i,linkedListLayer,width,height);
									PathTransition moveFrontTrans = new PathTransition();
									moveFrontTrans.setDuration(Duration.millis(750));
									moveFrontTrans.setNode(Nodes.get(i));
									moveFrontTrans.setPath(moveFront);
									moveFrontTrans.play();
								}
								deletePosTrans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										linkedListLayer.getChildren().remove(Nodes.get(position - 1));
										Nodes.remove(position - 1);
										deleteMenu.setDisable(false);
									}
								});
								if(Nodes.size() - 1 <= 10) {
									linkedListLayer.getChildren().remove(fullText);
								}
							}
						}
						else {
							Alert wrongInput = new Alert(AlertType.WARNING);
							wrongInput.setTitle("Input ERROR");
							wrongInput.setHeaderText(null);
							wrongInput.setContentText("Invalid Input: Position Has To Be An Integer Number! ");
							wrongInput.showAndWait();
						}
					}
				}
			}
		});
		
		this.getChildren().addAll(actionLayer,linkedListLayer);
		actionLayer.setViewOrder(0);
		linkedListLayer.setViewOrder(1);
		
	}

}
