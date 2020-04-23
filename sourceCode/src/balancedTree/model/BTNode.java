package balancedTree.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class BTNode{
	Circle body;
	Text value;
	int height;
	double num = 1, x, y;
	StackPane treeNode;
	
	BTNode left, right;
	
	public Circle getBody() {
		return body;
	}

	public BTNode(double diameter, String num) {
		
		this.num = Double.parseDouble(num);
		
		this.body = new Circle(diameter / 2);
		this.body.setFill(Color.CADETBLUE);
		
		this.height = 1;
		
		this.value = new Text();
		this.value.setText(num);

		this.treeNode = new StackPane();
		this.treeNode.getChildren().addAll(this.body,this.value);
		this.value.toFront();
	}
	
}
