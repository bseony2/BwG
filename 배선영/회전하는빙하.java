import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;

public class 회전하는빙하 {
    static int n, q;
    static int len;
    static int[][] map, copy;
    static int[] signs;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int ans = 0;
    static int bigSize = 0;
    public static void main(String[] args) throws IOException {
        init();
        
        simulate();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        len = (int)Math.pow(2, n);
        map = new int[len][len];
		copy = new int[len][len];
        signs = new int[q];

        for(int i=0; i<len; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<len; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<q; i++) {
            signs[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void simulate() {
        for(int level : signs) {

            int size = (int) Math.pow(2, level);
		
            for(int r = 0; r < len; r += size) {
                for(int c = 0; c < len; c += size) {
                    spin(r, c, size);
                }
            }
		
            for(int r = 0; r < len; r++) {
                for(int c =0; c < len; c++) {
                    map[r][c] = copy[r][c];
                }
            }

            melt();
        }

        print();
    }

    private static void spin(int r, int c, int size) {
		int half = size / 2;
		
        //좌상
		for(int i = r; i < r + half; i++) {
			for(int j = c; j < c + half; j++) {
				copy[i][j] = map[i + half][j];
			}
		}
		
        //우상
		for(int i = r; i < r + half; i++) {
			for(int j = c + half; j < c + size; j++) {
				copy[i][j] = map[i][j - half];
			}
		}
		
        //좌하
		for(int i = r + half; i < r + size; i++) {
			for(int j = c; j < c + half; j++) {
				copy[i][j] = map[i][j + half];
			}
		}
		
        //우하
		for(int i = r + half; i < r + size; i++) {
			for(int j = c + half; j < c + size; j++) {
				copy[i][j] = map[i - half][j];
			}
		}
	}

    static void melt() {
        boolean[][] meltArea = getMeltArea();

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(meltArea[i][j]) map[i][j] -= 1;
            }
        }
    }

    static boolean[][] getMeltArea() {
        boolean[][] result = new boolean[len][len];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(map[i][j] <= 0) continue;
                if(haveMeltDown(i, j)) result[i][j] = true;
            }
        }
        return result;
    }

    static boolean haveMeltDown(int r, int c) {
        int emptySide = 0;
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!isValidPoint(nr, nc) || map[nr][nc] <= 0) emptySide += 1;
        }
        return emptySide >=2 ? true : false;
    }

    static boolean isValidPoint(int nr, int nc) {
        return 0<=nr && nr<len && 0<=nc && nc<len;
    }

    static void print() {
        boolean[][] isVisited = new boolean[len][len];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(map[i][j] <= 0 || isVisited[i][j]) continue;

                queue.add(new int[]{i, j});
                isVisited[i][j] = true;
                bfs(queue, isVisited);
            }
        }

        System.out.println(ans + "\n" + bigSize);
    }

    static void bfs(Queue<int[]> queue, boolean[][] isVisited) {
        int size = 0;
        
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];
            ans += map[r][c];
            size += 1;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!isValidPoint(nr, nc) || map[nr][nc] <= 0 || isVisited[nr][nc]) continue;

                isVisited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }

        bigSize = bigSize > size ? bigSize : size;
    }
}