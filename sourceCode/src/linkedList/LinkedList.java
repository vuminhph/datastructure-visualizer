package linkedList;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import linkedList.Theoretical.LLTheoreticalBackground;
import linkedList.model.LLVisualizeContent;

public class LinkedList extends Stage{
	
	public LinkedList(){
		super();
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		float windowWidth = gd.getDisplayMode().getWidth() * 4/5;
		float windowHeight = (float) (windowWidth / 1.725);
		
		HBox root = new HBox();
		root.setStyle("-fx-background-image: url(\"/main/Icons/nature.jpg\");\r\n" + 
				"    -fx-background-size: cover;  \r\n" + 
				"    -fx-background-position: center center;\r\n" + 
				"    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
		
		@SuppressWarnings("unused")
		int width = gd.getDisplayMode().getWidth() / 8;
		int height = gd.getDisplayMode().getHeight() / 20;
		
		
//		menuSpace
		VBox menuSpace = new VBox();
		float menuWidth = windowWidth /4;
		float menuHeight = windowHeight;
		menuSpace.setMaxWidth(menuWidth);
		menuSpace.setSpacing(3);
		
		StackPane menuTitle = new StackPane();
		menuTitle.setPadding(new Insets(10));
		Text menuText = new Text("Linked List");
		menuText.setFill(Color.WHITE);
		menuText.setFont(Font.font("Proxima Nova",30));
		menuTitle.setPrefSize(menuWidth, height * 1.5 + 10);
		menuTitle.getChildren().add(menuText);
		StackPane.setAlignment(menuText, Pos.CENTER);
		menuSpace.getChildren().add(menuTitle);
		
        
//        SELECT
        VBox SelectContent = new VBox();
        
        StackPane visualizeSelect = new StackPane();
        visualizeSelect.setPrefSize(menuWidth, height);
        Rectangle visualBar = new Rectangle();
        visualBar.setFill(Paint.valueOf("#5385BF"));
        visualBar.setHeight(height);
        visualBar.setWidth(10);
        visualBar.setVisible(false);
        
        ImageView visualizeView = new ImageView("/main/Icons/visualize.png");
        Button visualizeButton = new Button("Visualization",visualizeView);
		visualizeButton.setPrefSize(menuWidth, height);
		visualizeButton.getStylesheets().add("selectButtonStyle.css");
		visualizeSelect.getChildren().addAll(visualBar,visualizeButton);
		visualBar.setViewOrder(0);
		visualizeButton.setViewOrder(1);
		StackPane.setAlignment(visualBar, Pos.CENTER_LEFT);
        
		StackPane theoSelect = new StackPane();
        visualizeSelect.setPrefSize(menuWidth, height);
        Rectangle theoBar = new Rectangle();
        theoBar.setFill(Paint.valueOf("#5385BF"));
        theoBar.setHeight(height);
        theoBar.setWidth(10);
        theoBar.setVisible(false);
        
		ImageView theoView = new ImageView("/main/Icons/background.png");
		Button theoButton = new Button("Theoretical Background",theoView);
		theoButton.setPrefSize(menuWidth, height);
		theoButton.getStylesheets().add("selectButtonStyle.css");
        theoSelect.getChildren().addAll(theoBar,theoButton);
        theoBar.setViewOrder(0);
		theoButton.setViewOrder(1);
        StackPane.setAlignment(theoBar, Pos.CENTER_LEFT);
        
        SelectContent.getChildren().addAll(visualizeSelect,theoSelect);
		menuSpace.getChildren().add(SelectContent);
		VBox.setVgrow(SelectContent, Priority.ALWAYS);
		
//		THEORICAL BACKGROUND INDEX
		TitledPane index = new TitledPane();
		index.setText("Background Contents");
		index.setExpanded(false);
		index.getStylesheets().add("titledStyle.css");
        
		VBox indexContent = new VBox();
		Button definition = new Button("Definition");
		
		definition.setPrefSize(menuWidth, height);
		definition.getStylesheets().add("buttonStyle.css");
		
		TitledPane operation = new TitledPane();
		operation.setText("Operations");
		operation.setExpanded(false);
		operation.getStylesheets().add("titledStyle.css");
		
		VBox operationContent = new VBox();
		Button insert = new Button("Insertion");
		insert.setPrefSize(menuWidth, height);
		insert.getStylesheets().add("buttonStyle.css");
		
		Button delete = new Button("Deletion");
		delete.setPrefSize(menuWidth, height);
		delete.getStylesheets().add("buttonStyle.css");
		
		operationContent.getChildren().addAll(insert,delete);
		operation.setContent(operationContent);
		
		indexContent.getChildren().addAll(definition,operation);
		index.setContent(indexContent);
		
		Rectangle indexSpace = new Rectangle(20,height);
		indexSpace.setFill(Paint.valueOf("#121212"));
		
		HBox indexContainer = new HBox();
		indexContainer.getChildren().addAll(indexSpace,index);
		
		SelectContent.getChildren().add(indexContainer);
		
		index.setVisible(false);
		
		
		
//		Menu BAR
		HBox menuBar = new HBox();
		double buttonSize = menuWidth / 4;
		double buttonHeight = buttonSize * 5/9;
		
		String defaultBARStyle = new String("-fx-background-color: #282828;-fx-opacity: 0.6");
		String hightlightBARStyle = new String("-fx-background-color: #282828");
		
		ImageView backView = new ImageView("/main/Icons/left.png");
		Button backButton = new Button("",backView);
		backButton.setPrefSize(buttonSize,buttonHeight);
		backButton.setStyle(defaultBARStyle);
		backButton.setOnMouseEntered(e->backButton.setStyle(hightlightBARStyle));
        backButton.setOnMouseExited(e->backButton.setStyle(defaultBARStyle));
        backButton.setTooltip(
        	    new Tooltip("Previous Slide")
        	);
		
        ImageView nextView = new ImageView("/main/Icons/right.png");
		Button nextButton = new Button("",nextView);
		nextButton.setPrefSize(buttonSize,buttonHeight);
		nextButton.setStyle(defaultBARStyle);
		nextButton.setOnMouseEntered(e->nextButton.setStyle(hightlightBARStyle));
        nextButton.setOnMouseExited(e->nextButton.setStyle(defaultBARStyle));
        nextButton.setTooltip(
        	    new Tooltip("Next Slide")
        	);
        
		ImageView resetView = new ImageView("/main/Icons/reset.png");
		Button resetButton = new Button("",resetView);
		resetButton.setPrefSize(buttonSize,buttonHeight);
		resetButton.setStyle(defaultBARStyle);
		resetButton.setOnMouseEntered(e->resetButton.setStyle(hightlightBARStyle));
        resetButton.setOnMouseExited(e->resetButton.setStyle(defaultBARStyle));
        resetButton.setTooltip(
        	    new Tooltip("Reset")
        	);
        
		ImageView exitView = new ImageView("/main/Icons/exit.png");
		Button exitButton = new Button("",exitView);
		exitButton.setPrefSize(buttonSize,buttonHeight);
		exitButton.setStyle(defaultBARStyle);
		exitButton.setOnMouseEntered(e->exitButton.setStyle(hightlightBARStyle));
        exitButton.setOnMouseExited(e->exitButton.setStyle(defaultBARStyle));
        exitButton.setTooltip(
        	    new Tooltip("Exit")
        	);
        
        menuBar.getChildren().addAll(resetButton,backButton,nextButton,exitButton);
        menuSpace.getChildren().add(menuBar);
        
		menuSpace.setMinSize(menuWidth,menuHeight);
		menuSpace.setStyle("-fx-background-color: #121212; -fx-opacity: 0.6");
		
//		demoSpace
		StackPane demoSpace = new StackPane();
		
		HBox.setHgrow(demoSpace, Priority.ALWAYS);
		float demoHeight =  windowHeight - 100;
		demoSpace.setAlignment(null);
		demoSpace.setMinHeight(demoHeight);
		
		LLVisualizeContent visualizeSpace = new LLVisualizeContent();
		LLTheoreticalBackground theoSpace = new LLTheoreticalBackground();
		
		demoSpace.getChildren().addAll(visualizeSpace, theoSpace);
		
		visualizeSpace.setVisible(false);
		theoSpace.setVisible(false);
		
		visualizeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				index.setVisible(false);
				theoBar.setVisible(false);
				visualBar.setVisible(true);
				
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					demoSpace.getChildren().get(demoSpace.getChildren().size() -1).setVisible(false);
					demoSpace.getChildren().get(0).setVisible(true);
					demoSpace.getChildren().get(0).toFront();
				}
				else {
					visualizeSpace.setVisible(true);
					visualizeSpace.toFront();
				}
			}
		});
		
		theoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				index.setVisible(true);
				theoBar.setVisible(true);
				visualBar.setVisible(false);
				
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLVisualizeContent && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					demoSpace.getChildren().get(demoSpace.getChildren().size() - 1).setVisible(false);
					demoSpace.getChildren().get(0).setVisible(true);
					demoSpace.getChildren().get(0).toFront();
				}
				else {
					theoSpace.setVisible(true);
					theoSpace.toFront();
				}
			}
		});
		
//		Menu action
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					((LLTheoreticalBackground) demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).getPreviousButton().fire();
				}
			}
		});
		
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					((LLTheoreticalBackground) demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).getNextButton().fire();
				}
			}
		});
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLVisualizeContent && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					LLVisualizeContent resetContent = new LLVisualizeContent();
					demoSpace.getChildren().remove(demoSpace.getChildren().size() - 1);
					demoSpace.getChildren().add(resetContent);
				}
				
				else if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					LLTheoreticalBackground resetContent = new LLTheoreticalBackground();
					demoSpace.getChildren().remove(demoSpace.getChildren().size() -1);
					demoSpace.getChildren().add(resetContent);
				}
			}
		});

		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});
		
		root.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof LLTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true){
					switch(event.getCode()) {
					case RIGHT: {
						theoSpace.getNextButton().fire();
						break;
					}
					case LEFT:{ 
						theoSpace.getPreviousButton().fire();
						break;
					}
					default:
						break; 
					}
				}
			}
		});
//		
		root.getChildren().addAll(menuSpace,demoSpace);
        menuSpace.setViewOrder(0);
        demoSpace.setViewOrder(3);
		
		Scene scene = new Scene(root,windowWidth,windowHeight);
		
		this.setTitle("Linked List");
		this.setScene(scene);
		
//		INDEX ACTION
		definition.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int definitionIndex = 2;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(definitionIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(definitionIndex - 1);
			}
		});
		
		insert.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int insertIndex = 8;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(insertIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(insertIndex - 1);
			}
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int deleteIndex = 13;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(deleteIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(deleteIndex - 1);
			}
		});
	}

}
