/*
AM:4315 EFFROSYNI ANDREOU
AM:4370 DAMIANOS KALAITSIDIS
AM:4438 FOTINI BOKOGIANNI
*/
import java.util.*;
import java.io.*;
public class State
{
	private int[][]matrix;
	private int[] currentPosA; //PlayerA position on the board
	private int[] currentPosB; //PlayerB position on the board
	private int[] currentPos=new int[2];
	private int M,Opponent; //(rows)
	private int N; //columns	
	private String pos;
	private int step;
	private boolean PlayerA;
	private boolean PlayerB;
	
	private HashMap<Integer,String> map=new HashMap<Integer,String>();
	private LinkedList<Integer>graph;
	
	//constructor
	public State(boolean PlayerA,boolean PlayerB,int[] currentPosA,int[]currentPosB)
	{
		this.PlayerA=PlayerA;
		this.PlayerB=PlayerB;
		this.currentPosA=currentPosA;
		this.currentPosB=currentPosB;
	}
	public void set(int step,String pos)
	{
		this.pos=pos;
		this.step=step;
	}
	public void setFlag(boolean playerA,boolean playerB)
	{
		this.PlayerA=playerA;
		this.PlayerB=playerB;
	}
	public HashMap<Integer,String>getMap()
	{
		return this.map;
	}
	public boolean isTerminal() 
	{
		return graph.isEmpty();
	}
	public int getUtility()//players
	{
		if(PlayerA)return 1;
		else return -1;
	}
	public void addEdge(int cost) //add the node to the graph
	{
		graph.add(cost);
	}

	//this method will create the graph with the cost of the possible moves
	public LinkedList<Integer> getAction(){
		int cost=0;
		int row,col,ColOpponent,RowOpponent;
		
		//if the current player is A
		if(PlayerA){
			graph=new LinkedList<Integer>();
			row=currentPosA[0];
			col=currentPosA[1];
			RowOpponent=currentPosB[0];
			ColOpponent=currentPosB[1];
			System.out.println("PlayerA");
			Opponent=-40;
		}
		//if the current player is B
		else{
			Opponent=40;
			graph=new LinkedList<Integer>();
			row=currentPosB[0];
			col=currentPosB[1];
			RowOpponent=currentPosA[0];
			ColOpponent=currentPosA[1];
			System.out.println("PlayerB");
		}
		if(col+2>=0&&col+2<N){
			
			//two step right
			if((matrix[row][col+1]!=1 && (matrix[row][col+1]!=Opponent))&&(matrix[row][col+2]!=1&&(matrix[row][col+2]!=Opponent))){
				cost=2;
				addEdge(cost);
				map.put(cost,"2 right");
				System.out.println(cost);
			}
		}
		if(col+1>=0&&col+1<N){
			//one step right
			if((matrix[row][col+1]!=1&&(matrix[row][col+1]!=Opponent))){
				cost=1;
				addEdge(cost);
				map.put(cost,"1 right");
				System.out.println(cost);
			}
		}
		//left
		if (col-2>=0 && col-2<N){//[0,N]
			//two step left
			if((matrix[row][col-1]!=1&&(matrix[row][col-1]!=Opponent))&&(matrix[row][col-2]!=1&&(matrix[row][col-2]!=Opponent))){
				cost=5;
				addEdge(cost);
				map.put(cost,"2 left");
				System.out.println(cost);
			}
		}
		if(col-1>=0&&col-1<N){
			//one step left
			if((matrix[row][col-1]!=1&&(matrix[row][col-1]!=Opponent)))
			{
				cost=4;
				addEdge(cost);
				map.put(cost,"1 left");
				System.out.println(cost);
			}
		}
		
		if(row+2>=0&&row+2<M){
			//two steps down
			if((matrix[row+1][col]!=1&&(matrix[row+1][col]!=Opponent))&&(matrix[row+2][col]!=1&&(matrix[row+2][col]!=Opponent))){
				cost=8;
				addEdge(cost);
				map.put(cost,"2 down");
				System.out.println(cost);
			}
		}
		if(row+1>=0&&row+1<M){
			//one step down
			if((matrix[row+1][col]!=1&&(matrix[row+1][col]!=Opponent)))
			{
				cost=7;
				addEdge(cost);
				map.put(cost,"1 down");
				System.out.println(cost);
			}
		}
		if(row-2>=0&&row-2<M){
			//two steps up
			if((matrix[row-1][col]!=1&&(matrix[row-1][col]!=Opponent))&&(matrix[row-2][col]!=1&&(matrix[row-2][col]!=Opponent))){
				cost=11;
				addEdge(cost);
				map.put(cost,"2 up");
				System.out.println(cost);
			}
		}
		if(row-1>=0 && row-1<M){
			//one step up
			if((matrix[row-1][col]!=1&&(matrix[row-1][col]!=Opponent)))
			{
				cost=10;
				addEdge(cost);
				map.put(cost,"1 up");
				System.out.println(cost);
			}
		}
		if(row-2>0&&row-2<M && col-2>=0&&col-2<N){
			//diagonal left up two steps
			if((matrix[row-1][col-1]!=1&&(matrix[row-1][col-1]!=Opponent))&&(matrix[row-2][col-2]!=1&&(matrix[row-2][col-2]!=Opponent))){
				cost=20;
				addEdge(cost);
				map.put(cost,"2 diagonal up left");
				System.out.println(cost);
			}
		}
		if(row-1>0&&row-1<M && col-1>=0&&col-1<N){
			//1 diagonal left up  one step
			if((matrix[row-1][col-1]!=1&&(matrix[row-1][col-1]!=Opponent))){
				cost=19;
				addEdge(cost);
				map.put(cost,"1 diagonal up left");
				System.out.println(cost);
			}
		}
		if(row+2>=0&&row+2<M && col-2>=0&&col-2<N){
			//diagonal left down two steps
			if((matrix[row+1][col-1]!=1&&(matrix[row+1][col-1]!=Opponent))&&(matrix[row+2][col-2]!=1&&(matrix[row+2][col-2]!=Opponent))){
				cost=23;
				addEdge(cost);
				map.put(cost,"2 diagonal down left");
				System.out.println(cost);
			}
		}
		if(row+1>=0&&row+1<M && col-1>=0&&col-1<N){
			//diagonal down left one step
			if((matrix[row+1][col-1]!=1&&(matrix[row+1][col-1]!=Opponent)))
			{
				cost=22;
				addEdge(cost);
				map.put(cost,"1 diagonal down left");
				System.out.println(cost);
			}
		}
		if(row-2>=0&&row-2<M && col+2>=0&&col+2<N){
			//diagonal right up two steps
			if((matrix[row-1][col+1]!=1&&(matrix[row-1][col+1]!=Opponent))&&(matrix[row-2][col+2]!=1&&(matrix[row-2][col+2]!=Opponent))){
				cost=14;
				addEdge(cost);
				map.put(cost,"2 diagonal up right");
				System.out.println(cost);
			}
		}
		if(row-1>=0&&row-1<M && col+1>=0&&col+1<N){
			//diagonal up right one step
			if((matrix[row-1][col+1]!=1&&(matrix[row-1][col+1]!=Opponent)))
			{
				cost=13;
				addEdge(cost);
				map.put(cost,"1 diagonal up right");
				System.out.println(cost);
			}
		}
		if(row+2>=0&&row+2<M && col+2>=0&& col+2<N){
			//diagonal right down two steps
			if((matrix[row+1][col+1]!=1&&(matrix[row+1][col+1]!=Opponent))&&(matrix[row+2][col+2]!=1&&(matrix[row+2][col+2]!=Opponent))){
				cost=17;
				addEdge(cost);
				map.put(cost,"2 diagonal down right");
				System.out.println(cost);
			}
		}
		if(row+1>=0&&row+1<M && col+1>=0&& col+1<N){
			//diagonal right down one step
			if((matrix[row+1][col+1]!=1&&(matrix[row+1][col+1]!=Opponent)))
			{
				cost=16;
				addEdge(cost);
				map.put(cost,"1 diagonal down right");
				System.out.println(cost);
			}
		}
		return graph; //we return the graph with contens node with the cost of every posible move
	}
	
	//this method create a matrix with given size 	M,N
	public void setMatrix(int m ,int n){
		this.M=m;
		this.N=n;
		this.matrix=new int[M][N];
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<N;j++)matrix[i][j]=0;
		}
		matrix[currentPosA[0]][currentPosA[1]]=40; //we symbolize the PlayerA as 40 on the board
		matrix[currentPosB[0]][currentPosB[1]]=-40; //we symbolize the PlayerB as -40 on the board
	}
	//this method is used to make the blocks black in the matrix
	//we symbolise black as 1 and white as 0
	//we symbilse player A as true and player B as false
	public void MarkMatrix()
	{
		int col;
		int[] currPosOpponent;
		int[] currPosPlayer;
		
		if(PlayerA){
			currPosOpponent=currentPosB;
			currPosPlayer=currentPosA;
			currentPos[0]=currentPosA[0];
			currentPos[1]=currentPosA[1];
			Opponent=-40;
		}
		else {
			currPosOpponent=currentPosA;
			currPosPlayer=currentPosB;
			currentPos[0]=currentPosB[0];
			currentPos[1]=currentPosB[1];
			Opponent=40;
		}
		col=currentPos[1]+1;
		switch(pos)
		{		
			case "left":
				for(int i=currentPos[1]-1;i>=currentPos[1]-step;i--){
					if(matrix[currentPos[0]][i]==0 && (matrix[currentPos[0]][i]!=Opponent)){
						matrix[currentPos[0]][i+1]=1;
						currPosPlayer[1]=i;
					}	
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "right":
				for(int i=currentPos[1]+1;i<=currentPos[1]+step;i++){
					if(matrix[currentPos[0]][i]==0 && (matrix[currentPos[0]][i]!=Opponent)){
						matrix[currentPos[0]][i-1]=1;
						currPosPlayer[1]=i;
					}
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "up":
				for(int i=currentPos[0]-1;i>=currentPos[0]-step;i--){
					if(matrix[i][currentPos[1]]==0 && (matrix[i][currentPos[1]]!=Opponent)){	
						matrix[i+1][currentPos[1]]=1;
						currPosPlayer[0]=i;
					}
				}	
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "down":
				for(int i=currentPos[0]+1;i<=currentPos[0]+step;i++){
					if(matrix[i][currentPos[1]]==0 && (matrix[i][currentPos[1]]!=Opponent)){	
						matrix[i-1][currentPos[1]]=1;
						currPosPlayer[0]=i;
					}
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "diagonal up left":
				for(int i=currentPos[0]-1;i>=currentPos[0]-step;i--){
					if(matrix[i][col]==0 && (matrix[i][col]!=Opponent)){	
						matrix[i+1][col]=1;
						currPosPlayer[0]=i;
						currPosPlayer[1]=col-2;
						col--;
					}
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case"diagonal up right":
				for(int i=currentPos[0]-1;i>=currentPos[0]-step;i--){
					if(matrix[i][col]==0 && (matrix[i][col]!=Opponent)){	
						matrix[i+1][col-1]=1;
						currPosPlayer[0]=i;
						currPosPlayer[1]=col;
						col++;
					}
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "diagonal down left":		
				for(int i=currentPos[0]+1;i<=currentPos[0]+step;i++){
					if(matrix[i][col-2]==0 && (matrix[i][col-2]!=Opponent)){	
						matrix[i-1][col-1]=1;
						currPosPlayer[0]=i;
						currPosPlayer[1]=col-2;
						col--;
					}
				}
				System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
				matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
				break;
			case "diagonal down right":
			for(int i=currentPos[0]+1;i<=currentPos[0]+step;i++){
				if(matrix[i][col]==0 && (matrix[i][col]!=Opponent)){				
					matrix[i-1][col-1]=1;
					currPosPlayer[0]=i;
					currPosPlayer[1]=col;
					col++;
				}
			}
			System.out.println("currPosPlayer"+currPosPlayer[0]+" "+currPosPlayer[1]);
			matrix[currPosPlayer[0]][currPosPlayer[1]]=Opponent*(-1);
			break;
		}
	}
	
	public void printmatrix(){
		for(int i=0;i<M;i++){
			for(int j=0;j<N;j++){
				if (matrix[i][j]==40){
					System.out.print("A"+"		");
				}
				else if(matrix[i][j]==-40){
					System.out.print("B"+"		");
				}
				else{
					System.out.print(matrix[i][j]+"		");
				}
			}
			System.out.println("");
		}
	} 	
	public static void main(String []args)
	{
		MiniMax minmax=new MiniMax();
		int[]a=new int[2];
		int[]b=new int[2];
		Scanner input1,input2,input3,input4,input5,input6;
		
		System.out.print("Give the M(width)of the matrix:\n");
		input1 = new Scanner (System.in);
		int M= input1.nextInt();
		System.out.print("Give the N(length)of the matrix:\n");
		input2 = new Scanner (System.in);
		int N= input2.nextInt();
		
		
		System.out.print("Type the row of Player A:\n");
		input3 = new Scanner (System.in);
		a[0]= input3.nextInt();
		while(a[0]>M || a[0]<0){
			System.out.print("Type again the row of Player A:\n");
			input3 = new Scanner (System.in);
			a[0]= input3.nextInt();
		}
			
		System.out.print("Type the column of Player A:\n");
		input4 = new Scanner (System.in);
		a[1]=input4.nextInt();
		while(a[1]>N || a[1]<0){
			System.out.print("Type again the column of Player A:\n");
			input4 = new Scanner (System.in);
			a[1]= input4.nextInt();
		}
		
		
		System.out.print("Type the row Player B:\n");
		input5 = new Scanner (System.in);
		b[0]= input5.nextInt();
		while(b[0]>M || b[0]<0){
			System.out.print("Type again the row of Player B:\n");
			input5 = new Scanner (System.in);
			b[0]= input5.nextInt();
		}
		
		
		System.out.print("Type the Column of Player B:\n");
		input6 = new Scanner (System.in);
		b[1]=input6.nextInt();
		while((b[1]>N || b[1]<0) || (a[1]==b[1] && a[0]==b[0])){
			System.out.print("Type again the column of Player B:\n");
			input6 = new Scanner (System.in);
			b[1]= input6.nextInt();
		}
		
		State state=new State(true,false,a,b);
		state.setMatrix(M,N);
		state.printmatrix();
		minmax.Desicion(state);
		
	}
}