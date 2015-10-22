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
	}
	
	public void insertLastNode(Node node) {
		end.setNextNode(node);
		end = node;
		size ++;
	}
	
	public void printMatrix (int m) {
		System.out.println("MATRIX: ");
		int x = 0;
		int matrixSize = m * m;
		
		Node temp = start;
		boolean found;
		
		for (int i = 0; i < m; i ++) {
			for (int j = 0; j < m; j++) {
				found = false;
				while (temp.getNextRow() != null) {
					if(temp.getRow() == i && temp.getCol() == j) {
						System.out.print(temp.getVal() + "\t");
						found = true;
						temp = start;
						break;
					}
					else {
						temp = temp.getNextRow();
					}
				}
				if (found == false) {
					if (temp.getRow() == i && temp.getCol() == j) {
						System.out.print(temp.getVal());
						temp = start;
					}
					else {
						System.out.print("0\t");
						temp = start;
					}
				}
			}
			System.out.print("\n");
		}
	}
	
	public void printList() {
		System.out.println("LIST OF NODES: ");
		System.out.println("Row\t" + "Column\t" + "Value");
		Node temp = start;
		int x = 0;
		
		while(x < size){
			System.out.println(temp.getRow() + "\t" + temp.getCol() + "\t" + temp.getVal());
			if(temp.getNextRow() != null) {
				temp = temp.getNextRow();
			}
			x++;
		}
		System.out.println("\n");
	}
}

public class sparseMatrix {
	
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
		
		list.printList();
		list.printMatrix(n);
	}
	
}