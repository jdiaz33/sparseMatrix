/************************************************************************
*	Name:		Jose Diaz
*	Course:		CS 435
*	Professor:	Dr. Perl
*	Programming Semester Project
*	A Computational System for Sparse Matrices
*
*************************************************************************/


import java.util.Scanner;
//import java.io.*;

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

/*********************************************************************************/

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
	
	public Node getStart () { return start; }
	
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
		int x = 0;
		//int matrixSize = m * m;
		
		Node temp = start;
		boolean found;
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
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
						System.out.print(temp.getVal() + "\t");
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
		System.out.println("\n");
	}
	
	public void printList() {
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
	
	public int getNodeVal(int i, int j){
		Node temp = start;
		int x = 0;
		
		while(x < size){
			if(temp.getRow() == i && temp.getCol() == j){
				return temp.getVal();
			}
			if(temp.getNextRow() != null){
				temp = temp.getNextRow();
			}
			x++;
		}
		return 0;
	}
}

/*************************************************************************************/

public class sparseMatrix {
	
	public static void main(String[] args) {
		System.out.println("Hello, and Welcome to the world of Matrix");
		
		Scanner keyBoard = new Scanner(System.in);
		linkedList A = new linkedList();
		
		System.out.println("Enter the size of the Matrix: ");
		int n = keyBoard.nextInt();
		System.out.println("Your Matrix is a: " + n + " x " + n + " matrix");
		
		int input = 0; int row = 0; int col = 0; int val = 0; int inputCnt = 0;
		
		do {
			System.out.println("Type 1 to enter triples or -1 to exit");
			input = keyBoard.nextInt();
			if (input == 1) {
				System.out.println("Enter row: ");
				row = keyBoard.nextInt();
				while (row > n || row < 1){
					System.out.println("Wrong input.");
					System.out.println("Enter row: ");
					row = keyBoard.nextInt();
				}
				
				System.out.println("Enter column: ");
				col = keyBoard.nextInt();
				while (col > n || col < 1) {
					System.out.println("Wrong input.");
					System.out.println("Enter column: ");
					col = keyBoard.nextInt();
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
				Node nodeA = new Node(row, col, val, null, null);
				insertNode(A, nodeA);
			}
		} while ( input == 1 && inputCnt < n * n);
		
		System.out.println("MATRIX A: ");
		A.printMatrix(n);
		
		linkedList B = new linkedList();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					Node nodeB = new Node(i, j, i, null, null);
					insertNode(B, nodeB);
				}
			}
		}
		System.out.println("MATRIX B: ");
		B.printMatrix(n);
		
		linkedList C = new linkedList();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == ((j + 1) % n)){
					val = (-2 * j) -1;
					Node nodeC = new Node(i, j, val, null, null);
					insertNode(C, nodeC);
				}
			}
		}
		System.out.println("MATRIX C: ");
		C.printMatrix(n);
		
		linkedList D = new linkedList();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if ((i % 2) == 0 && (j % 2) == 0) {
					val = i + j;
					Node nodeD = new Node(i, j, val, null, null);
					insertNode(D, nodeD);
				}
				else if (j == 3) {
					val = i * -1;
					Node nodeD = new Node(i, j, val, null, null);
					insertNode(D, nodeD);
				}
			}
		}
		System.out.println("MATRIX D: ");
		D.printMatrix(n);
		
		System.out.println("====================== STEP 2 ==========================");
		
		//E = B + D
		linkedList E = new linkedList();
		addition(B, D, E, n);
		System.out.println("MATRIX E = B + D: ");
		E.printMatrix(n);
		
		//F = D - C
		linkedList F = new linkedList();
		subtract(D, C, F, n);
		System.out.println("MATRIX F = D - C: ");
		F.printMatrix(n);
		
		//G = A + B
		linkedList G = new linkedList();
		addition(A, B, G, n);
		System.out.println("MATRIX G = A + B: ");
		G.printMatrix(n);
		
		//H = A - B
		linkedList H = new linkedList();
		subtract(A, B, H, n);
		System.out.println("MATRIX H = A - B: ");
		H.printMatrix(n);
		
		//I = E - F
		linkedList I = new linkedList();
		subtract(E, F, I, n);
		System.out.println("MATRIX I = E - F: ");
		I.printMatrix(n);
		
		//J = G + H
		linkedList J = new linkedList();
		addition(G, H, J, n);
		System.out.println("MATRIX J = G + H: ");
		J.printMatrix(n);
		
		//K = 5 * B
		linkedList K = new linkedList();
		scalar(5, B, K, n);
		System.out.println("MATRIX K = 5 * B: ");
		K.printMatrix(n);
		
		//L = 8 * C
		linkedList L = new linkedList();
		scalar(8, C, L, n);
		System.out.println("MATRIX L = 8 * C: ");
		L.printMatrix(n);
		
		//M = 3 * G
		linkedList M = new linkedList();
		scalar(3, G, M, n);
		System.out.println("MATRIX M = 3 * G: ");
		M.printMatrix(n);
		
		//N = 2 * H
		linkedList N = new linkedList();
		scalar(2, H, N, n);
		System.out.println("MATRIX N = 2 * H: ");
		N.printMatrix(n);
		
		//O = 2 * M
		linkedList O = new linkedList();
		scalar(2, M, O, n);
		System.out.println("MATRIX O = 2 * M: ");
		O.printMatrix(n);
		
		//P = 3 * F
		linkedList P = new linkedList();
		scalar(3, F, P, n);
		System.out.println("MATRIX P = 3 * F: ");
		P.printMatrix(n);
		
		System.out.println("====================== STEP 3 ==========================");
		
		//Q = A * B
		linkedList Q = new linkedList();
		matrixMultiply(A, B, Q, n);
		System.out.println("MATRIX Q = A * B: ");
		Q.printMatrix(n);
	}
	
/********************************* STATIC METHODS ******************************/

	public static void insertNode(linkedList list, Node node){
		if(list.isEmpty() == true) {
			list.insertFirstNode(node);
		}
		else {
			list.insertLastNode(node);
		}
	}
	
	public static void addition(linkedList matrix1,
								linkedList matrix2,
								linkedList result,
								int n){
		for (int i = 1; i <= n; i++){
			for (int j = 1; j <= n; j++){
				int x = matrix1.getNodeVal(i, j);
				int y = matrix2.getNodeVal(i, j);
				int z = x + y;
				
				if(z != 0){
					Node node = new Node(i, j, z, null, null);
					insertNode(result, node);
				}
			}
		}
	}
	
	public static void subtract(linkedList matrix1,
								linkedList matrix2,
								linkedList result,
								int n){
		for (int i = 1; i <= n; i++){
			for (int j = 1; j <= n; j++){
				int x = matrix1.getNodeVal(i, j);
				int y = matrix2.getNodeVal(i, j);
				int z = x - y;
				
				if(z != 0){
					Node node = new Node(i, j, z, null, null);
					insertNode(result, node);
				}
			}
		}
	}
	
	public static void scalar(int num, linkedList matrix, linkedList result, int n){
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				int x = matrix.getNodeVal(i, j);
				int y = x * num;
				
				if(y != 0){
					Node node = new Node(i, j, y, null, null);
					insertNode(result, node);
				}
			}
		}
	}
	
	public static void matrixMultiply(linkedList matrix1,
									  linkedList matrix2,
									  linkedList result,
									  int n){
		int [] rows = new int[n];
		int [] cols = new int[n];
		
		int row = 1;
		while(row <= n){
			for(int i = 0; i < n; i++){
				rows[i] = matrix1.getNodeVal(row, i+1);
			}
			int col = 1;
			while(col <= n){
				for(int j = 0; j < n; j++){
					cols[j] = matrix2.getNodeVal(j+1, col);
				}
				int nodeVal = computeVal(rows, cols);
				if(nodeVal != 0){
					Node node = new Node(row, col, nodeVal, null, null);
					insertNode(result, node);
				}
				col++;
			}
			row++;
		}
	}
	
	public static int computeVal(int[] row, int[] col){
		int val = 0;
		int cnt = 0;
		while(cnt < row.length){
			val = val + (row[cnt] * col[cnt]);
			cnt++;
		}
		return val;
	}
}