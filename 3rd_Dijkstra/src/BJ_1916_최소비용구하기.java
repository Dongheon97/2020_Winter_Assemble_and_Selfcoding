import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_2020_12_23__30
 * @fileName : BJ_1916_최소비용구하기.java
 * @author : Mingeon
 * @date : 2020. 12. 28.
 * @language : JAVA
 * @classification : Dijkstra
 * @time_limit : 0.5sec
 * @required_time : 07:20 ~ 08:11
 * @submissions : 1
 * @description 
 * 1. 구현하다보니 프림과 비슷한 구조를 가졌다.
 * 2. dist값 초기화가 중요.
 * 3. 인접리스트를 구현, 최소거리를 지속적으로 탐색
 */

public class BJ_1916_최소비용구하기 {

	static int N; // 도시의 개수
	static int M; // 버스의 개수
	static int start; // 시작 도시
	static int arrive; // 도착 도시
	static int[] dist; // 최단 거리
	static boolean[] visited; // 방문 관리
	static ArrayList<BusNode>[] Node; // 버스 정보 확인 ( 인접리스트)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1]; // 방문 관리 배열
		dist = new int[N + 1]; // 최단거리 배열
		Arrays.fill(dist, Integer.MAX_VALUE); // 중요! 반드시 무한대값에 수렴하는 값을 초기화 해주어야한다.

		// 노드 초기화
		Node = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			Node[i] = new ArrayList<>();
		}

		// 단방향 인접리스트 구현
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 가중치 입력
			Node[from].add(new BusNode(to, weight));

		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		arrive = Integer.parseInt(st.nextToken());

		// End of input

		System.out.println(dijkstra(start));
		br.close();
	}

	private static int dijkstra(int start) {
		PriorityQueue<BusNode> pq = new PriorityQueue<BusNode>();
		pq.offer(new BusNode(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			BusNode node = pq.poll();

			// dist[도시]값이 가중치를 넣을 당시 경로보다 더 작은 경우
			if (dist[node.num] < node.cost) {
				continue;
			}

			// 현재 인덱스와 연결된 모든 버스 확인
			for (int i = 0; i < Node[node.num].size(); i++) {
				BusNode temp = Node[node.num].get(i);

				// temp.num을 거쳐서 가는 경로가 더 짧은 경우 갱신 후 pq에 삽입.
				if (dist[temp.num] > dist[node.num] + temp.cost) {
					dist[temp.num] = dist[node.num] + temp.cost;
					pq.add(new BusNode(temp.num, temp.cost));
				}

			}

		}

		return dist[arrive];
	}

}

class BusNode implements Comparable<BusNode> {
	int num;
	int cost;

	public BusNode(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}

	@Override
	public int compareTo(BusNode o) {
		return Integer.compare(this.cost, o.cost);
	}

}