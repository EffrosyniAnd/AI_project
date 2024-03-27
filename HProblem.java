/*
AM:4315 EFFROSYNI ANDREOU
AM:4370 DAMIANOS KALAITSIDIS
AM:4438 FOTINI BOKOGIANNI
*/
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HProblem{
	
	private static Scanner input1;
	private static Scanner input2;
	private static ArrayList<Integer> FS=new ArrayList<Integer>();  //final state
	private static List<Integer> OS=new ArrayList<Integer>(); //original state of the list given from the user
	private static List<List<Integer>> Edge=new ArrayList<List<Integer>>(); //children
	private static List<Node> Edges=new ArrayList<Node>(); //children for nodes
	private static int CostPath;
	private static int sum=0;  //for the cost of each child
	private static int N; //size of the list
	private static int extensions=0; // here we keep the extensions of the path
	public static void UCSsearch(List<Integer> List){
		
		PriorityQueue<Node>UCSQueue=new PriorityQueue<>(Node.costComparator); //creating empty queue
		ArrayList<Node>visited=new ArrayList<Node>(); //here we keep the visited nodes
		List<Node>path=new ArrayList<Node>(); //here we keep the path
		int count=0;
		
		//enter the first list to the queue
		Node FinalState=new Node();
		Node start=new Node();
		start.setList(OS,start);
		UCSQueue.add(start);
		
		while(!UCSQueue.isEmpty()){
			
			Node currentNode=UCSQueue.poll();
			if(visited.contains(currentNode)){
				continue;
			}
			count++;
			if(FS.equals(currentNode.has(currentNode))){ //final node
				CostPath+=currentNode.getCost();
				FinalState=currentNode;
				break;
			}
			MakeEdges(currentNode); //create children
			while(!Edges.isEmpty()) //while arraylist isn't empty
			{
				Node elem=Edges.remove(0); //remove one child from arraylist
				UCSQueue.add(elem);  //add child to Queue in order to be checked later
			}
			visited.add(currentNode);
		}
		//to create the path from the source to the destination
		Node finalstate=FinalState;
		while(finalstate!=null){
			path.add(0,finalstate);
			finalstate=finalstate.getfamily(finalstate);
		}
		System.out.print("Path: ");
		for(Node node:path){
			System.out.print(node.has(node)+" ,");
		}
		System.out.println("\nCostpath: "+CostPath+" \nExtensions "+extensions);
	}
	//function which create a list with the reversed elemenets 
	public static List<Integer> T(int N,List<Integer> List)
	{
		int i=0;
		int j=N-1;
		int temp;
		List<Integer>NewList=new ArrayList<Integer>();
		//we copy the given list to the new list
		for(int a=0;a<List.size();a++){//deep copy
			NewList.add(List.get(a));
		}
		//make reversion
		while(i!=j && i<j){
			temp= NewList.get(i);
			NewList.set(i,NewList.get(j));
			NewList.set(j,temp);
			i++;
			j--;
		}
		return NewList;
	}
	//function to create the children(type Node) of the given parent  Node
	public static void MakeEdges(Node node){
		int x=N;
		for(int i=N;i>1;i--){// N-1 children		
			Node edge=new Node(node.getList());
			edge.setParent(node);
			List<Integer>temp=T(x,node.has(node));
			edge.setList(temp,edge);
			edge.setCost(sum+heuristicCost(FS,edge));    //*********heuristic cost
			edge.setfamily(edge);
			Edges.add(edge);//child node	
			x--;
			extensions++;
		}
		sum+=1;
	}
	//we create the final state of the given list 
	public static void createFS(int N){
		for(int i=1;i<=N;i++)FS.add(i);
	}
	//here we calculate the heuristic cost
	public static int heuristicCost(List<Integer> FinalState,Node state) //our heuristic function ,that returns h(n)
	{
		if(FinalState.equals(state.has(state)))
		{
			return 0;
		}
		int max_cost=0;
		for(int i=0; i<state.has(state).size(); i++)
		{
			int local_cost=0;
			for(int j=0; j<state.has(state).size(); j++)
			{
				if(FinalState.get(j)!= state.has(state).get(j))
				{
					local_cost++;
				}
			}
			if(local_cost>max_cost) {
				max_cost=local_cost;
			}
			
		}
		return max_cost;
	}
	public static void main(String[] args)
	{
		System.out.println("!! A* !!");
		System.out.print("Give the length of the list:\n");
		input1 = new Scanner (System.in);
		N= input1.nextInt();
		int i=0;
		System.out.print("Give the number of original state:\n");
		
		while(i<N)
		{	
			input2 = new Scanner (System.in);
			int number= input2.nextInt();
			OS.add(number);
			i++;
		}
		
		createFS(N);
		UCSsearch(OS);		
		System.out.println("!! A* !!");
	}
}