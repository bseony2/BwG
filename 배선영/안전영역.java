import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

class 안전영역 {

    static int N;
    static int[][] matrix;
    static boolean[][] isVisited;
    static int maxHeight;
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(matrix[i][j] > maxHeight) {
					maxHeight = matrix[i][j];
				}
			}
        }

        int answer = 0;

        for(int height=0; height<maxHeight + 1; height++) {
            int result = 0;
            isVisited = new boolean[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!isVisited[i][j] && matrix[i][j] > height) {
                        result += bfs(i, j, height);
                    }
                }
            }
            answer = Math.max(answer, result);
        }
        
        System.out.println(answer);
    }

    static int bfs(int y, int x, int height) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(y, x));
        isVisited[y][x] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int i=0; i<4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if(!isValid(ny, nx)) continue;
                if(!isSafe(ny, nx, height)) continue;
                if(!isVisited[ny][nx]) {
                    isVisited[ny][nx] = true;
                    queue.add(new Node(ny, nx));
                }
            }
        }
        return 1;
    }

    static boolean isSafe(int y, int x, int height) {
        return matrix[y][x] > height;
    }

    static boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}

class Node {
    int x;
    int y;

    public Node(int y, int x) {
        this.x = x;
        this.y = y;
    }
}