import java.util.*;
import java.io.*;

class Node {
	int count;
	int id;
	HashSet<Integer> childs;
	
	Node(int id) {
		this.id = id;
		childs = new HashSet<>();
	}
	
	void append(int k) {
		this.childs.add(k);
	}
	
}

public class Main {

	static int[] p;
	static Node[] nodes;
	static boolean[] scc;
	static Stack<Integer> stack; 
	static int N, M, count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = 1;
		
		p = new int[2*N+1];
		nodes = new Node[2*N+1];
		scc = new boolean[2*N+1];
		stack = new Stack<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int al = a > 0 ? N+a : -a;
			int bl = b > 0 ? N+b : -b;
			
			
			if (nodes[al] == null) nodes[al] = new Node(al);
			if (nodes[bl] == null) nodes[bl] = new Node(bl);

			nodes[al].append(b > 0 ? b : N-b);
			nodes[bl].append(a > 0 ? a : N-a);
		}

		for (int i=1; i<=N; i++) {
			count = 1;
			if (!scc[i]) doSAT(i);
			
			count = 1;
			if (!scc[i+N]) doSAT(i+N);
			
			if (p[i] == p[i+N]) {
				answer = 0;
				break;
			}
		}

		sb.append(answer);
		System.out.print(sb);
	}

	private static int doSAT(int curr) {
		// 더이상 갈 수 있는 경로가 없을경우.
		p[curr] = curr;
		if (nodes[curr] == null) {
			scc[curr] = true;
			return Integer.MAX_VALUE;
		}
		
		int parent = count;
		nodes[curr].count = count;
		stack.push(curr);
		
		for (int next : nodes[curr].childs) {
			if (scc[next]) continue;
			count += 1;
			if (p[next] != 0) {
				parent = Math.min(parent, nodes[next] == null ? count : nodes[next].count);
				continue;
			}
			parent = Math.min(doSAT(next), parent);
		}
		if (parent == nodes[curr].count) {
			while (stack.peek() != curr) {
				scc[stack.peek()] = true;
				p[stack.pop()] = curr;
			}
			scc[stack.peek()] = true;
			p[stack.pop()] = curr;
		}
		return parent;
	}

}