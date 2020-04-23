package balancedTree.model;

import java.util.ArrayList;
import java.util.Stack;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BTContainer {
	public BTNode Root; 
	public Stack<BTNode> stack = new Stack<BTNode>();
	public ArrayList<Line> line = new ArrayList<Line>();
	public int dy = 50;
	public int dx = 25;
	int key;
	
	int height(BTNode node) {
		if(node == null) return 0;
		return node.height;
	}
	
	int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	BTNode rotateRight(BTNode a) {
		BTNode b = a.left;
		BTNode c = b.right;
		
		b.right = a;
		a.left = c;
		
		a.height = max(height(a.left),height(a.right) + 1);
		b.height = max(height(b.left), height(b.left) + 1);
		
		return b;
	}
	
	BTNode rotateLeft(BTNode b) { 
		BTNode a = b.right; 
		BTNode c = a.left; 

		a.left = b; 
		b.right = c; 

		b.height = max(height(b.left), height(b.right)) + 1; 
		a.height = max(height(a.left), height(a.right)) + 1; 

		return a; 
	} 
	
	int getBalance(BTNode node) {
		if(node == null) return 0;
		return height(node.left) - height(node.right);
	}
	
	void insertNavigateAnimation(BTNode root, BTNode node, Circle glow, double disX, double disY) {
		if(root == null)
			return;
		
		TranslateTransition insertTrans = new TranslateTransition();
		insertTrans.setDuration(Duration.millis(200));
		insertTrans.setNode(glow);
		insertTrans.setToX(root.x - disX);
		insertTrans.setToY(root.y - disY);
		insertTrans.play();
		insertTrans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (node.num < root.num) 
					insertNavigateAnimation(root.left, node,glow,disX,disY);
				if (node.num > root.num)
					insertNavigateAnimation(root.right,node,glow,disX,disY);
				else return;
			}
		});
		return;
	}
	
	void findLeftMostAnimation(BTNode root, Circle glow, double disX, double disY) {
		if(root == null)
			return;
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(200));
		trans.setNode(glow);
		trans.setToX(root.x - disX);
		trans.setToY(root.y - disY);
		trans.play();
		trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				findLeftMostAnimation(root.left, glow, disX, disY);
			}
		});
	}
	
	BTNode findLeftMost(BTNode root)  
	{  
		BTNode cur = root;  
		while (cur.left != null)  
			cur = cur.left;  
		return cur;  
	}  
	
	void deleteAnimation(BTNode node, double num, Circle glow,  double disX, double disY) {
		if (node == null)  
			return;  
	
		
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.millis(200));
		trans.setNode(glow);
		trans.setToX(node.x - disX);
		trans.setToY(node.y - disY);
		trans.play();
		trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (num < node.num)  
					deleteAnimation(node.left, num,glow, disX, disY);  

				else if (num > node.num)  
					deleteAnimation(node.right, num,glow, disX, disY);  

				else
				{  	

					if ((node.left == null) || (Root.right == null))  
					{  
						if (node.left != null) {
							TranslateTransition trans = new TranslateTransition();
							trans.setDuration(Duration.millis(200));
							trans.setNode(glow);
							trans.setToX(node.left.x-disX);
							trans.setToY(node.left.y-disY);
							trans.play();
						}
						else if(node.right != null) {
							TranslateTransition trans = new TranslateTransition();
							trans.setDuration(Duration.millis(200));
							trans.setNode(glow);
							trans.setToX(node.right.x-disX);
							trans.setToY(node.right.y-disY);
							trans.play();
						}
						
					}  
					else
					{  
						findLeftMostAnimation(node.right,glow,disX, disY);
					}  
				}  
			}
		});
	
	}
	
	BTNode insert(BTNode root, BTNode node, double x, double y, int dis) { 

		if (root == null) {
			node.x = x;
			node.y = y;

			TranslateTransition trans = new TranslateTransition();
			trans.setDuration(Duration.millis(200));
			trans.setToX(node.x);
			trans.setToY(node.y);
			trans.play();
			return (node); 
		}
		stack.push(root);
		if (node.num < root.num) 
			root.left = insert(root.left, node, root.x-dis, y+dy,dis); 
		else if (node.num > root.num) 
			root.right = insert(root.right, node, root.x+dis, y+dy, dis); 
		else
			return root;

		root.height = 1 + max(height(root.left), 
				height(root.right)); 

		return root;

	} 
	
	BTNode insertSelfBalancing(BTNode node, double num) { 

		if (node == null) 
			return (new BTNode(40,Double.toString(num))); 

		if (num < node.num) 
			node.left = insertSelfBalancing(node.left, num); 
		else if (num > node.num) 
			node.right = insertSelfBalancing(node.right, num); 
		else
			return node; 

		node.height = 1 + max(height(node.left), 
				height(node.right)); 

		int balance = getBalance(node); 

		if (balance > 1 && num < node.left.num) 
			return rotateRight(node); 

		if (balance < -1 && num > node.right.num) 
			return rotateLeft(node); 

		if (balance > 1 && num > node.left.num) { 
			node.left = rotateLeft(node.left); 
			return rotateRight(node); 
		} 

		if (balance < -1 && num < node.right.num) { 
			node.right = rotateRight(node.right); 
			return rotateLeft(node); 
		} 

		return node; 
	} 
	
	void traverseBalance(Circle glow, Pane window, double num, double disX, double disY) {
		if (!stack.isEmpty()) {

			BTNode node = stack.pop();
			TranslateTransition trans = new TranslateTransition();
			trans.setDuration(Duration.millis(200));
			trans.setNode(glow);
			trans.setToX(node.x-disX);
			trans.setToY(node.y-disY);
			trans.play();

			trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					traverseBalance(glow,window, num,disX,disY);

					if(getBalance(node) > 1 || getBalance(node) < -1) {
						Root = insertSelfBalancing(Root, num);
						changeCoordinate(Root, disX, disY );
						window.getChildren().remove(glow);
					}

				}
			});

		}

	}
	
	boolean isDeletable(BTNode root, double num)  
	{  
		if (root == null)  
			return true;  
		if(root.num != num)
			stack.push(root);
		if (num < root.num)  
			return isDeletable(root.left, num);  

		else if (num > root.num)  
			return isDeletable(root.right, num);  

		else
		{  	
			if ((root.left == null) || (root.right == null))  
			{  
				return true;
			}
			else return false;
		}
	}
	
	BTNode deleteNode(BTNode node, double num, Pane window, double disX,double disY) {
		if (node == null)  
			return node;  
		if (num < node.num)  
			node.left = deleteNode(node.left, num,window,disX,disY);  
		else if (num > node.num)  
			node.right = deleteNode(node.right, num,window,disX,disY);  
		else
		{  	
			if ((node.left == null) || (node.right == null))  
			{  
				window.getChildren().remove(node.treeNode);
				BTNode tmp = null;  
				if (tmp == node.left)  
					tmp = node.right;  
				else
					tmp = node.left;  
				if (tmp == null)  
				{  
					tmp = node;  
					node = null;  
				}  
				else node = tmp; 
			}  
			else
			{  
				BTNode tmp = findLeftMost(node.right);  
				deleteAnimation(node, findLeftMost(node.right),window,disX,disY);
				node.right = deleteNodeSelfBalancing(node.right, tmp.num,window,disX,disY);  
			}  
		}  
		return node;
	}
	
	BTNode deleteNodeSelfBalancing(BTNode node, double num, Pane window,  double disX,double disY)  
	{  
		if (node == null)  
			return node;  
		if (num < node.num)  
			node.left = deleteNodeSelfBalancing(node.left, num,window,disX,disY);  
		else if (num > node.num)  
			node.right = deleteNodeSelfBalancing(node.right, num,window,disX,disY);  
		else
		{  	
			if ((node.left == null) || (node.right == null))  
			{  
				window.getChildren().remove(node.treeNode);
				BTNode temp = null;  
				if (temp == node.left)  
					temp = node.right;  
				else
					temp = node.left;  
				if (temp == null)  
				{  
					temp = node;  
					node = null;  
				}  
				else
				node = temp;
			}  
			else
			{  
				BTNode temp = findLeftMost(node.right);  
				deleteAnimation(node, findLeftMost(node.right),window,disX,disY);
				node.right = deleteNodeSelfBalancing(node.right, temp.num,window,disX,disY);  
			}  
		}  

		if (node == null)  
			return node;  
		node.height = max(height(node.left), height(node.right)) + 1;  
		int balance = getBalance(node);  
		if (balance > 1 && getBalance(node.left) >= 0)  
			return rotateRight(node);  

		if (balance > 1 && getBalance(node.left) < 0)  
		{  
			node.left = rotateLeft(node.left);  
			return rotateRight(node);  
		}  
		if (balance < -1 && getBalance(node.right) <= 0)  
			return rotateLeft(node);  
		if (balance < -1 && getBalance(node.right) > 0)  
		{  
			node.right = rotateRight(node.right);  
			return rotateLeft(node);  
		}  

		return node;  
	}  

	BTNode deleteAnimation(BTNode node, BTNode temp,Pane window, double disX,double disY) {
		StackPane c = new StackPane(new Circle(20,Color.GREENYELLOW),new Text(Double.toString(temp.num)));
		TranslateTransition trans = new TranslateTransition();
		c.setLayoutX(temp.x);
		c.setLayoutY(temp.y);
		trans.setDuration(Duration.millis(200));
		trans.setNode(c);
		trans.setToX(node.x-temp.x);
		trans.setToY(node.y-temp.y);
		window.getChildren().add(c);
		trans.play();
		
		window.getChildren().remove(node.treeNode);
		trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeCoordinate(Root,disX,disY);
				node.num = temp.num; 
				node.treeNode.getChildren().remove(1);
				node.treeNode.getChildren().add(new Text(Double.toString(temp.num)));
				window.getChildren().add(node.treeNode);
				window.getChildren().remove(c);
				
			}
		});
		
		return node;
	}
	
	void changeCoordinate(BTNode node, double x, double y) {
		if (node != null) {
			node.x = x;
			node.y= y;
			
			TranslateTransition trans = new TranslateTransition();
			trans.setDuration(Duration.millis(1500));
			trans.setNode(node.treeNode);
			trans.setToX(node.x);
			trans.setToY(node.y);
			trans.play();
			
			if (node.left != null)
				changeCoordinate(node.left, x-dx*Math.pow(2, node.left.height-1),y+dy);
			if(node.right !=null)
				changeCoordinate(node.right, x+dx*Math.pow(2, node.right.height-1),y+dy);


		}	
	}
	
	void addLines(BTNode node, Pane window ) {
		if(node == null) return;
		if (node.left != null) {
			Line l = new Line(node.x+20,node.y+28,node.left.x+20,node.left.y+14);
			l.setStroke(Color.CADETBLUE);
			l.setStrokeWidth(3);
			line.add(0,l);
			window.getChildren().add(line.get(0));
		}
		if (node.right != null) {
			Line l = new Line(node.x+20,node.y+28,node.right.x+20,node.right.y+14);
			l.setStroke(Color.CADETBLUE);
			l.setStrokeWidth(3);
			line.add(0,l);
			window.getChildren().add(line.get(0));
		}
		addLines(node.left, window);
		addLines(node.right,window);
		
		
	}

}
