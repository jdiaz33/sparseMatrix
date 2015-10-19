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
}

public class sparseMatrix {

	//public int n;
	
	public static void main(String[] args) {
		System.out.println("Hello, and Welcome to the world of Matrix");
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.println("Enter the size of the Matrix: ");
		int n = keyBoard.nextInt();
		System.out.println("Your Matrix is a: " + n + " x " + n + "matrix");
		
		int input, row, col, val = 0;
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
			}
			else if(input == -1){break;}
			else {
				System.out.println("Wrong input. Try again..."); 
				input = 1;
			}
			inputCnt ++;
		} while ( input == 1 && inputCnt < n * n);
		
		
		
		linkedList list = new linkedList();
	}
}