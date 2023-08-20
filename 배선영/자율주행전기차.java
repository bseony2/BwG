import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class 자율주행전기차 {
    static int ans = 0;
    static int n, m, C;
    static int[][] map;
    static int[][] customerInfo;
    static int carR, carC;
    static int[] dr = new int[]{-1, 0, 0, 1};
    static int[] dc = new int[]{0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        init();

        drive();
        
        System.out.println(ans == -1 ? -1 : C);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        customerInfo = new int[m+1][2];
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 1) {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        carR = Integer.parseInt(st.nextToken())-1;
        carC = Integer.parseInt(st.nextToken())-1;

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int rs = Integer.parseInt(st.nextToken())-1;
            int cs = Integer.parseInt(st.nextToken())-1;
            int re = Integer.parseInt(st.nextToken())-1;
            int ce = Integer.parseInt(st.nextToken())-1;

            map[rs][cs] = i;
            customerInfo[i][0] = re;
            customerInfo[i][1] = ce;
        }

    }

    static void drive() {

        while(m >= 1) {
            int[] customerLocationInfo = findCustomer();

            int customerR = customerLocationInfo[0];
            int customerC = customerLocationInfo[1];
            int distanceToCustomer = customerLocationInfo[2];
            int customer = map[customerR][customerC];

            if(C <= distanceToCustomer || distanceToCustomer == -1) {
                ans = -1;
                break;
            }

            moveCar(customerR, customerC, distanceToCustomer);
            map[customerR][customerC] = 0;
            m -= 1;

            int distanceToDestination = findShortRoute(customer);

            if(C < distanceToDestination || distanceToDestination == -1) {
                ans = -1;
                break;
            }

            moveCar(customerInfo[customer][0], customerInfo[customer][1], distanceToDestination);
            C += distanceToDestination*2;
        }
    }

    static int[] findCustomer() {
        int[] result = new int[3];
        ArrayList<int[]> customerList = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];

        if(isCustomer(carR, carC)) {
            result[0] = carR;
            result[1] = carC;
            result[2] = 0;
            return result;
        }

        isVisited[carR][carC] = true;
        queue.add(new int[]{carR, carC});

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!isValidPoint(nr, nc) || isBlockPoint(nr, nc) || isVisited[nr][nc]) continue;

                if(isCustomer(nr, nc)) {
                    customerList.add(new int[]{nr, nc, distance[r][c]+1});
                    if(customerList.size() == m) {
                        queue.clear();
                        break;
                    }
                }

                isVisited[nr][nc] = true;
                distance[nr][nc] = distance[r][c]+1;
                queue.add(new int[]{nr, nc});
            }
        }

        Collections.sort(customerList, (a, b) -> a[2]-b[2]);

        if(customerList.size() == 0) {
            return new int[]{-1, -1, -1};
        }

        result[0] = customerList.get(0)[0];
        result[1] = customerList.get(0)[1];
        result[2] = customerList.get(0)[2];
        for(int[] customer : customerList) {
            if(result[2] < customer[2]) break;

            if(customer[0] < result[0]) {
                result[0] = customer[0];
                result[1] = customer[1];
                result[2] = customer[2];
            }
            else if(customer[0] == result[0] && customer[1] < result[1]) {
                result[0] = customer[0];
                result[1] = customer[1];
                result[2] = customer[2];
            }
        }

        return result;
    }

    static boolean isValidPoint(int nr, int nc) {
        return 0<=nr && nr<n && 0<=nc && nc<n;
    }

    static boolean isBlockPoint(int nr, int nc) {
        return map[nr][nc] == -1;
    }

    static boolean isCustomer(int nr, int nc) {
        return map[nr][nc] > 0;
    }

    static void moveCar(int nr, int nc, int energy) {
        carR = nr;
        carC = nc;
        C -= energy;
    }

    static int findShortRoute(int customer) {
        int result = -1;
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];

        int[] destination = customerInfo[customer];
        isVisited[carR][carC] = true;
        queue.add(new int[]{carR, carC});

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!isValidPoint(nr, nc) || isBlockPoint(nr, nc) || isVisited[nr][nc]) continue;

                if(nr == destination[0] && nc == destination[1]) {
                    result = distance[r][c]+1;
                    queue.clear();
                    break;
                }

                isVisited[nr][nc] = true;
                distance[nr][nc] = distance[r][c]+1;
                queue.add(new int[]{nr, nc});
            }
        }

        return result;
    }
}   