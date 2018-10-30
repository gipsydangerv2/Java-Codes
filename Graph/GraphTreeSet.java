package com.ankit.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Set;
import java.util.TreeSet;
public class Graph {

	Set<Integer>[] list;
	int vert;
	@SuppressWarnings("unchecked")
	public Graph(int vertices){
		this.vert = vertices;
		list = new TreeSet[vertices];
		for(int i=0;i<vertices;i++){
			list[i] = new TreeSet<Integer>();
		}
	}
	public void addEdge(int src,int dest){
		/*Time Complexity: O(log n) for inserting an edge.*/
		list[src].add(dest);
		list[dest].add(src);
	}
	public void printGraph(){
		int index = 0;
		
		for(Set<Integer> eachList : list){
			System.out.print(index + ":");
			for(Integer i : eachList){
				System.out.print(i+"-->");
			}
			index++;
			System.out.println();
		}
	}
	
	public boolean isEdgePresent(int src,int dest){
		/* Now searching for an edge will take O(log n) time.*/
		if(list[src].contains(dest))
			return true;
		return false;
	}
	public static void main(String[] args) throws Exception{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader buff = new BufferedReader(reader);
		
		String data [] = buff.readLine().split(" ");
		int vert = Integer.parseInt(data[0]);
		int edges = Integer.parseInt(data[1]);
		Graph g = new Graph(vert+1);
		for(int i=0;i<edges;i++){
			data = buff.readLine().split(" ");
			int src = Integer.parseInt(data[0]);
			int dest = Integer.parseInt(data[1]);
			g.addEdge(src, dest);
		}
		g.printGraph();
	}

}
