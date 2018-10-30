package com.ankit.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class Graph {

	ArrayList<Integer>[] list;
	int vert;
	@SuppressWarnings("unchecked")
	public Graph(int vertices){
		this.vert = vertices;
		list = new ArrayList[vertices];
		for(int i=0;i<vertices;i++){
			list[i] = new ArrayList<Integer>();
		}
	}
	public void addEdge(int src,int dest){
		list[src].add(dest);
		list[dest].add(src);
	}
	public void printGraph(){
		int index = 0;
		
		for(ArrayList<Integer> eachList : list){
			System.out.print(index + ":");
			for(Integer i : eachList){
				System.out.print(i+"-->");
			}
			index++;
			System.out.println();
		}
	}
	public void BFS(int start){
		boolean visited[] = new boolean[vert];
		Arrays.fill(visited, false);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		System.out.print("BFS:");
		while(queue.size() != 0){
			int item = queue.remove();
			System.out.print(item+"--->");
			for(Integer adjacent : list[item]){
				if(!visited[adjacent]){
					queue.add(adjacent);
					visited[adjacent] = true;
				}
			}
		}
	}
	public void BFSTwo(int start){
		boolean visited[] = new boolean[vert];
		Arrays.fill(visited,false);
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		System.out.print("BFS:");
		while(queue.size() != 0){
			int vertex = queue.poll();
			System.out.print(vertex+" ");
			Iterator<Integer> iter = list[vertex].iterator();
			while(iter.hasNext()){
				Integer adj = (Integer)iter.next();
				if(!visited[adj]){
					queue.add(adj);
					visited[adj] = true;
				}
			}
		}
		System.out.println();
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
   		g.BFS(1);
		g.BFSTwo(1);
	}

}
