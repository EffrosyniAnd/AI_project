/*
AM:4315 EFFROSYNI ANDREOU
AM:4370 DAMIANOS KALAITSIDIS
AM:4438 FOTINI BOKOGIANNI
*/
import java.util.*;
import java.io.*;
public final class MiniMax{
	
	private LinkedList<Integer>graph;
	
	private int step;
	private String pos;
	public MiniMax(){}
	
	public void Desicion(State state){
		
		int u;
		this.graph=state.getAction();
		if(!graph.isEmpty()){
			if(state.getUtility()==1){
				u=maxValue(state);
				getValues(state.getMap(),u);
				state.set(step,pos);
				state.MarkMatrix();
				state.setFlag(false,true);
				state.printmatrix();
			}	
			else{
				u=minValue(state);
				getValues(state.getMap(),u);
				state.set(step,pos);
				state.MarkMatrix();
				state.setFlag(true,false);
				state.printmatrix();
			}
			Desicion(state);
		}
		else{
			if(!(state.getUtility() == 1))System.out.println("Player A is the winner!!!");
			else System.out.println("Player B is the winner!!!");
		}
	}
	public int maxValue(State state)
	{
		if(state.isTerminal()){
			return state.getUtility();
		}
		int max=-1000;
		for(int i=0;i<this.graph.size();i++)
		{
			if (max<this.graph.get(i))
			{
				max=this.graph.get(i);
			}
			else continue;
		}
		System.out.println("Max="+max);
		return max;
	}
	public int minValue(State state)
	{
		if(state.isTerminal())return state.getUtility();
		int min=1000;
		for(int i=0;i<this.graph.size();i++)
		{
			if (min>this.graph.get(i))
			{
				min=this.graph.get(i);
			}
			else continue;
		}
		System.out.println("Min="+min);
		return min;
	}
	public void getValues(HashMap<Integer,String> map,int key){
		String[] b=map.get(key).split(" ",2);
		step=Integer.parseInt(b[0]);
		pos=b[1];
		System.out.println("step:"+step+"position:"+pos);
	}	
}	

	
