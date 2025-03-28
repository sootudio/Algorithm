import java.util.*;
 
public class Main {    
 
    static int n, m;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        n = scan.nextInt();
        m = scan.nextInt();
        
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            int cost = scan.nextInt();
            list[s].add(new Node(e, cost));
            list[e].add(new Node(s, cost));
        }
        
        visited = new boolean[n + 1];
        System.out.println(prim());
    }
    
    public static int prim() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        int dist = 0;
        int max = 0;
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            if(visited[current.n] == false) visited[current.n] = true;
            else continue;
            
            max = Math.max(max, current.cost);
            dist += current.cost;
            
            for(int i = 0; i < list[current.n].size(); i++) {
                Node next = list[current.n].get(i);
                if(visited[next.n] == false) q.offer(new Node(next.n, next.cost));
            }
        }
        return dist - max;
    }
    
    public static class Node implements Comparable<Node> {
        int n;
        int cost;
        
        public Node(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n1) {
            return this.cost - n1.cost;
        }
    }
}
