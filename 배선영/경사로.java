import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {

    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        int[][] matrix = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	check(arr, L);
        	for(int j=0; j<N; j++) {
        		matrix[j][i] = arr[j];
        	}
        }
        for(int[] arr : matrix) {
        	check(arr, L);
        }
        
        System.out.println(answer);

    }

    static void check(int[] arr, int L) {
    	boolean[] isRoad = new boolean[arr.length];
    	for(int i=0; i<arr.length-1; i++) {
    		

            if(arr[i] == arr[i+1]) {
    			continue;
    		} else if(arr[i] == arr[i+1] + 1) { // 경사가 낮아짐
    			if(!canMakeRoad(arr, i + 1, L, isRoad)) return;
    		} else if(arr[i] == arr[i+1] - 1) { // 경사가 높아짐
    			if(!canMakeRoad(arr, i - L + 1, L, isRoad)) return;
    		} else {
    			return;
    		}
    	}
    	answer++;
    }
    
    static boolean canMakeRoad(int[] arr, int start, int L, boolean[] isRoad) {
        if(start < 0 || start + L -1 >= arr.length) return false;
    	int height = arr[start];
    	for(int i = start; i < start+L; i++) {
    		
    		if(isRoad[i]) {
    			return false;
    		}
            if(arr[i] != height) {
    			return false;
    		}
    		isRoad[i] = true;
    	}
    	return true;
    }
}
