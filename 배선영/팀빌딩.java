import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.lang.Math;

public class 팀빌딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int answer = 0;
        int left = 0;
        int right = N - 1;

        while(left <= right) {
            answer = Math.max(Math.min(arr[left], arr[right]) * (right - left - 1), answer);

            if(arr[left] < arr[right]) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.println(answer);
    }
}


// 능력치가 다 다른 개발자 
// $N$명이 팀 빌딩을 위해 한 줄로 서있다.
// 하나의 팀을 만들기 위해서는 개발자 2명이 반드시 모여야 한다.
// 개발자 A와 개발자 B가 팀을 만들 때 팀의 능력치는 아래와 같이 계산이 된다.
// (개발자 A와 개발자 B 사이에 존재하는 다른 개발자 수) × min(개발자 A의 능력치, 개발자 B의 능력치)
// 예를 들어, 4명의 개발자가 존재할 때, 각 개발자의 능력치를 1 4 2 5라고 하자. 이때 능력치가 1인 개발자와 능력치가 5인 개발자가 한 팀을 이뤘다고 가정하자. 그러면 이 팀의 능력치는 $2×min(1, 5) = 2$가 된다.
// 팀 빌딩에서 나올 수 있는 팀 중 능력치의 최대값을 구해보자.


// 4
// 1 4 2 5

//4