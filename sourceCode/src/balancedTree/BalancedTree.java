package balancedTree;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import balancedTree.Theoretical.BTTheoreticalBackground;
import balancedTree.model.BTVisualizeContent;
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

public class BalancedTree extends Stage{
	
	public BalancedTree(){
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
		Text menuText = new Text("Balanced Tree");
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
		
		TitledPane AVLTree = new TitledPane();
		AVLTree.setText("AVL Tree");
		AVLTree.setExpanded(false);
		AVLTree.getStylesheets().add("titledStyle.css");
		
		VBox AVLContent = new VBox();
		Button AVLDef = new Button("Definition");
		AVLDef.setPrefSize(menuWidth, height * 5/6);
		AVLDef.getStylesheets().add("buttonStyle.css");
		
		Button AVLInsert = new Button("Insertion");
		AVLInsert.setPrefSize(menuWidth, height * 5/6);
		AVLInsert.getStylesheets().add("buttonStyle.css");
		
		Button AVLDeletion = new Button("Deletion");
		AVLDeletion.setPrefSize(menuWidth, height * 5/6);
		AVLDeletion.getStylesheets().add("buttonStyle.css");
		
		AVLContent.getChildren().addAll(AVLDef,AVLInsert,AVLDeletion);
		AVLTree.setContent(AVLContent);
		
		TitledPane RBTree = new TitledPane();
		RBTree.setText("Red Black Tree");
		RBTree.setExpanded(false);
		RBTree.getStylesheets().add("titledStyle.css");
		
		VBox RBContent = new VBox();
		Button RBDef = new Button("Definition");
		RBDef.setPrefSize(menuWidth, height * 5/6);
		RBDef.getStylesheets().add("buttonStyle.css");
		
		RBContent.getChildren().addAll(RBDef);
		RBTree.setContent(RBContent);
		
		TitledPane AATree = new TitledPane();
		AATree.setText("AA Tree");
		AATree.setExpanded(false);
		AATree.getStylesheets().add("titledStyle.css");
		
		VBox AAContent = new VBox();
		Button AADef = new Button("Definition");
		AADef.setPrefSize(menuWidth, height * 5/6);
		AADef.getStylesheets().add("buttonStyle.css");
		
		Button AAInsert = new Button("Insertion");
		AAInsert.setPrefSize(menuWidth, height * 5/6);
		AAInsert.getStylesheets().add("buttonStyle.css");
		
		Button AADeletion = new Button("Deletion");
		AADeletion.setPrefSize(menuWidth, height * 5/6);
		AADeletion.getStylesheets().add("buttonStyle.css");
		
		AAContent.getChildren().addAll(AADef,AAInsert,AADeletion);
		AATree.setContent(AAContent);
		
		indexContent.getChildren().addAll(AVLTree, RBTree, AATree);
		index.setContent(indexContent);
		
		Rectangle indexSpace = new Rectangle(20,height * 5/6);
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
		
		BTVisualizeContent visualizeSpace = new BTVisualizeContent();
		BTTheoreticalBackground theoSpace = new BTTheoreticalBackground();
		
		demoSpace.getChildren().addAll(visualizeSpace, theoSpace);
		
		visualizeSpace.setVisible(false);
		theoSpace.setVisible(false);
		
		visualizeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				index.setVisible(false);
				theoBar.setVisible(false);
				visualBar.setVisible(true);
				
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
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
				
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTVisualizeContent && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
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
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					((BTTheoreticalBackground) demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).getPreviousButton().fire();
				}
			}
		});
		
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					((BTTheoreticalBackground) demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).getNextButton().fire();
				}
			}
		});
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTVisualizeContent && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					BTVisualizeContent resetContent = new BTVisualizeContent();
					demoSpace.getChildren().remove(demoSpace.getChildren().size() - 1);
					demoSpace.getChildren().add(resetContent);
				}
				
				else if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true) {
					BTTheoreticalBackground resetContent = new BTTheoreticalBackground();
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
				if(demoSpace.getChildren().get(demoSpace.getChildren().size() - 1) instanceof BTTheoreticalBackground && (demoSpace.getChildren().get(demoSpace.getChildren().size() - 1)).isVisible() == true){
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
		AVLDef.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int definitionIndex = 2;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(definitionIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(definitionIndex - 1);
			}
		});
		
		AVLInsert.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int insertIndex = 6;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(insertIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(insertIndex - 1);
			}
		});
		
		AVLDeletion.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int deleteIndex = 10;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(deleteIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(deleteIndex - 1);
			}
		});
		
		RBDef.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int definitionIndex = 13;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(definitionIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(definitionIndex - 1);
			}
		});
		
		AADef.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int definitionIndex = 21;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(definitionIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(definitionIndex - 1);
			}
		});
		
		AAInsert.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int insertIndex = 35;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(insertIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(insertIndex - 1);
			}
		});
		
		AADeletion.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				int deleteIndex = 36;
				theoSpace.getSceneCollector().getSceneCollectiion().get(theoSpace.getSceneCollector().getSceneIndex()).setVisible(false);
				theoSpace.getSceneCollector().getSceneCollectiion().get(deleteIndex - 1).setVisible(true);
				theoSpace.getSceneCollector().setSceneIndex(deleteIndex - 1);
			}
		});
	}

}
