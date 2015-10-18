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
	
	//Constructor
	public Node (int r, int c, int v, Node nextR, Node nextC) {
		row = r;
		col = c;
		val = v;
		nextRow = nextR;
		nextCol = nextC;
	}
}

public class sparseMatrix {

	//public int n;
	
	public static void main(String[] args) {
		System.out.println("Hello, and Welcome to the world of Matrix");
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.println("Enter the size of the Matrix: ");
		int n = keyBoard.nextInt();
		System.out.println("The size of your Matrix is: " + n + " x " + n);
	}
}