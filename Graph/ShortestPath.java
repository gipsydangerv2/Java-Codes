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
	public void shortestDistance(int start){
		boolean visited[] = new boolean[vert];
		Arrays.fill(visited, false);
		int dist[] = new int[vert];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		dist[start] = 0;
		visited[start] = true;
		while(queue.size() != 0){
			int v = queue.remove();
			for(Integer w : list[v]){
				if(!visited[w]){
					if(dist[w] > (dist[v]+1)){
						dist[w] = dist[v] + 1;
					}
					queue.add(w);
					visited[w] = true;
				}
			}
		}
		System.out.println("Distance Array:");
		for(Integer d : dist){
			System.out.print(d+" ");
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
		g.shortestDistance(1);
	}

}
