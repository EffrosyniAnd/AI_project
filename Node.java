/*
AM:4315 EFFROSYNI ANDREOU
AM:4370 DAMIANOS KALAITSIDIS
AM:4438 FOTINI BOKOGIANNI
*/
import java.util.*;
public class Node{
	private HashMap<Node,List<Integer>> it = new HashMap<Node,List<Integer>>();
	private HashMap<Node,Node> parentNode = new HashMap<Node,Node>(); //for the path
	private static List<Integer> List;
	private static Node parent;
	private int cost;
	
	public Node(){
		this.cost=0;
		this.List=null;
		this.parent=null;

	}
	public Node(List<Integer>list){
		this.List=list;
		int cost;
		Node parent;
		HashMap<Node,Node> parentNode;// for the path
		List<List<Integer>>List;
	}
	public void setfamily(Node child){//we use this method to find the parent... of the node 
		parentNode.put(child,parent);
	} 
	public Node getfamily(Node node){//for the path
		return parentNode.get(node);
	}
	public List<Integer>has(Node node){//we use this method to return the list of every node 
		return it.get(node);
	}
	public int getCost()
	{
		return cost;
	}
	public void setCost(int pathcost)
	{
		cost=(getCost()+pathcost);
	}
	
	public void setList(List<Integer> list,Node node){
			this.List=list;
			it.put(node,list);
	}

	public List<Integer> getList(){
		return List;
	}
	
	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node parent)
	{
		this.parent=parent;
	}
	public static Comparator<Node> costComparator=new Comparator<Node>(){
		public int compare(Node node1,Node node2){
			return node1.getCost()-node2.getCost();
		}
	};
}