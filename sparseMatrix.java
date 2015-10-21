/************************************************************************
*	Name:		Jose Diaz
*	Course:		CS 435
*	Professor:	Dr. Perl
*	Programming Project 1
*	A Computational System for Sparse Matrices
*
*************************************************************************/


import java.util.Scanner;
import java.io.*;

class Node {
	protected int row;
	protected int col;
	protected int val;
	protected Node nextRow;
	protected Node nextCol;
	
	//Node constructor
	public Node (int r, int c, int v, Node nextR, Node nextC) {
		row = r;
		col = c;
		val = v;
		nextRow = nextR;
		nextCol = nextC;
	}
	
	public void setNextNode(Node node) {
		//if(node.getRow() > row) {
		//	nextRow = node;
		//}
		//else if (node.getCol() > col) {
		//	nextCol = node;
		//}
		
		nextRow = node;
		nextCol = node;
	}
	
	public int getRow () { return row; }
	public int getCol () { return col; }
	public int getVal () { return val; }
	public Node getNextRow () { return nextRow; }
	public Node getNextCol () { return nextCol; }
}

class linkedList {
	protected Node start;
	protected Node end;
	public int size;
	
	//linkedList constructor
	public linkedList () {
		start = null;
		end = null;
		size = 0;
	}
	
	//check if the list of nodes is empty
	public boolean isEmpty() {
		if (start == null) {
			System.out.println("inside isEmpty");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void insertFirstNode(Node node) {
		start = node;
		end = node;
		size ++;
		System.out.println("inside insertFirst");
	}
	
	public void insertLastNode(Node node) {
		end.setNextNode(node);
		end = node;
		System.out.println("inside insertLast");
		size ++;
	}
	
	public void printMatrix () {
		int x = 0;
		Node temp1 = start;
		while(x < size) {
			System.out.print("Node " + x + ":");
			System.out.println(temp1.getRow() + " " + temp1.getCol() + " " + temp1.getVal());
			if(temp1.getNextRow() != null){
				temp1 = temp1.getNextRow();
			}
			x++;
		}	
	}
}

public class sparseMatrix {

	//public int n;
	
	public static void main(String[] args) {
		System.out.println("Hello, and Welcome to the world of Matrix");
		
		Scanner keyBoard = new Scanner(System.in);
		linkedList list = new linkedList();
		
		System.out.println("Enter the size of the Matrix: ");
		int n = keyBoard.nextInt();
		System.out.println("Your Matrix is a: " + n + " x " + n + " matrix");
		
		int input = 0; int row = 0; int col = 0; int val = 0;
		int inputCnt = 0;
		
		do {
			System.out.println("Type 1 to enter triples or -1 to exit");
			input = keyBoard.nextInt();
			if (input == 1) {
				System.out.println("Enter row < " + n + ": ");
				row = keyBoard.nextInt();
				if (row >= n){
					System.out.println("Wrong input.");
					break;
				}
				
				System.out.println("Enter column < " + n + ": ");
				col = keyBoard.nextInt();
				if (col >= n) {
					System.out.println("Wrong input.");
					break;
				}
				
				System.out.println("Enter value: ");
				val = keyBoard.nextInt();
				inputCnt ++;
			}
			else if(input == -1){break;}
			else {
				System.out.println("Wrong input. Try again..."); 
				input = 1;
			}
			
			if (val != 0) {
				Node node = new Node(row, col, val, null, null);
				if (list.isEmpty() == true) {
					list.insertFirstNode(node);
				}
				else {
					list.insertLastNode(node);
				}
			}
		} while ( input == 1 && inputCnt < n * n);
		
		System.out.println("before the end");
		list.printMatrix();
	}
	
}