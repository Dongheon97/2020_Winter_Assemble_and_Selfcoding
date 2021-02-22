import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @packageName : BaekJoon_2020_12_23__30
 * @fileName : BJ_1916_�ּҺ�뱸�ϱ�.java
 * @author : Mingeon
 * @date : 2020. 12. 28.
 * @language : JAVA
 * @classification : Dijkstra
 * @time_limit : 0.5sec
 * @required_time : 07:20 ~ 08:11
 * @submissions : 1
 * @description 
 * 1. �����ϴٺ��� ������ ����� ������ ������.
 * 2. dist�� �ʱ�ȭ�� �߿�.
 * 3. ��������Ʈ�� ����, �ּҰŸ��� ���������� Ž��
 */

public class BJ_1916_�ּҺ�뱸�ϱ� {

	static int N; // ������ ����
	static int M; // ������ ����
	static int start; // ���� ����
	static int arrive; // ���� ����
	static int[] dist; // �ִ� �Ÿ�
	static boolean[] visited; // �湮 ����
	static ArrayList<BusNode>[] Node; // ���� ���� Ȯ�� ( ��������Ʈ)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1]; // �湮 ���� �迭
		dist = new int[N + 1]; // �ִܰŸ� �迭
		Arrays.fill(dist, Integer.MAX_VALUE); // �߿�! �ݵ�� ���Ѵ밪�� �����ϴ� ���� �ʱ�ȭ ���־���Ѵ�.

		// ��� �ʱ�ȭ
		Node = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			Node[i] = new ArrayList<>();
		}

		// �ܹ��� ��������Ʈ ����
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// ����ġ �Է�
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

			// dist[����]���� ����ġ�� ���� ��� ��κ��� �� ���� ���
			if (dist[node.num] < node.cost) {
				continue;
			}

			// ���� �ε����� ����� ��� ���� Ȯ��
			for (int i = 0; i < Node[node.num].size(); i++) {
				BusNode temp = Node[node.num].get(i);

				// temp.num�� ���ļ� ���� ��ΰ� �� ª�� ��� ���� �� pq�� ����.
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