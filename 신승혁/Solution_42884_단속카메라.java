import java.util.PriorityQueue;

public class Solution_42884_단속카메라 {
    private final int MIN_VALUE = -30001;

    public static void main(String[] args) {
        Solution_42884_단속카메라 sol = new Solution_42884_단속카메라();
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int result = sol.solution(routes);
        System.out.println(result);
    }

    public int solution(int[][] routes) {
        PriorityQueue<Car> cars = new PriorityQueue<>((a, b) -> a.end - b.end);

        for (int[] route : routes) {
            cars.offer(new Car(route[0], route[1]));
        }


        int count = 0; // 카메라 갯수

        int curEnd = MIN_VALUE;
        while (!cars.isEmpty()) {
            Car car = cars.poll();

            if (car.start > curEnd) {
                count++;
                curEnd = car.end;

            }
            // System.out.println(car);
            // System.out.println(curEnd);
        }


        return count;
    }

    private class Car {
        int start;
        int end;

        public Car(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Bridge{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
