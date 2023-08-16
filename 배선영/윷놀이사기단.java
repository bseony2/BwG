import java.util.Scanner;
import java.util.ArrayList;

public class 윷놀이사기단 {

    static class Unit {
        int[] route;
        int location;
        boolean isDone;
        int[] nextRoute;
        int nextLocation;
        
        public Unit() {
            route = mainRoute;
            nextRoute = mainRoute;
            location = 0;
            nextLocation = 0;
            isDone = false;
        }

        public void move() {
            this.route = this.nextRoute;
            this.location = this.nextLocation;
            isDone = this.route == arrive ? true : false;
        }

        public void setNextLocation(int dis) {
            this.nextLocation = this.location + dis;

            if(this.location + dis >= this.route.length) {
                this.nextRoute = arrive;
                this.nextLocation = unitList.indexOf(this);
                return;
            }

            if(this.route == mainRoute) {
                if(this.nextLocation == 5) {
                    this.nextRoute = firstShortcut;
                    this.nextLocation = 0;
                }
                else if(this.nextLocation == 10) {
                    this.nextRoute = secondShortcut;
                    this.nextLocation = 1;
                }
                else if(this.nextLocation == 15) {
                    this.nextRoute = thirdShortcut;
                    this.nextLocation = 0;
                }
                else {
                    this.nextRoute = mainRoute;
                }
            }
            else {
                this.nextRoute = this.route;
            }
        }
    }

    static int[] mainRoute = new int[21];
    static int[] firstShortcut = new int[8];
    static int[] secondShortcut = new int[8];
    static int[] thirdShortcut = new int[8];
    static int[] arrive = new int[4];
    static ArrayList<Unit> unitList = new ArrayList<>();
    static int[] moves = new int[10];
    static int ans = Integer.MIN_VALUE;
    static int[] track = new int[10];
    public static void main(String...args) {
        initialize();

        play(0, 0);

        System.out.println(ans);
    }

    static void initialize() {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<10; i++) {
            moves[i] = sc.nextInt();
        }
        sc.close();
        for(int i=1; i<mainRoute.length; i++) {
            mainRoute[i] = i*2;
        }

        for(int i=0; i<4; i++) {
            firstShortcut[i] = 10 + i*3;
        }

        for(int i=1; i<4; i++) {
            secondShortcut[i] = 18 + i*2;
        }

        thirdShortcut[0] = 30;
        for(int i=1; i<4; i++) {
            thirdShortcut[i] = 29 - i;
        }

        for(int i=1; i<5; i++) {
            firstShortcut[i+3] = 20 + 5* i;
            secondShortcut[i+3] = 20 + 5* i;
            thirdShortcut[i+3] = 20 + 5* i;
        }

        for(int i=0; i<4; i++) {
            unitList.add(new Unit());
        }
    }

    static void play(int depth, int score) {
        if(depth == 10) {
            if(score == 228) {
                System.out.println();
            }
            ans = ans > score ? ans : score;
            return;
        }

        for(int i=0; i<4; i++) {
            Unit unit = unitList.get(i);
            int dis = moves[depth];
            if(unitMoveable(unit, dis)) {
                int[] routeBackup = unit.route;
                int locationBackup = unit.location;
                int scoreBackup = score;
                track[depth] = i;
                unit.move();

                try{
                score = unit.isDone ? score : score + unit.route[unit.location];
                }
                catch(Exception e) {
                    throw e;
                }
                
                play(depth+1, score);
                unit.route = routeBackup;
                unit.location = locationBackup;
                unit.isDone = false;
                score = scoreBackup;
            }
        }
    }

    static boolean unitMoveable(Unit unit, int dis) {
        
        if(unit.isDone) {
            return false;
        }
        unit.setNextLocation(dis);
        boolean result = !isAlreadyExist(unit.nextRoute, unit.nextLocation);
        return result;
    }

    static boolean isAlreadyExist(int[] nextRoute, int nextLocation) {
        boolean result = false;

        for(Unit currentUnit : unitList) {
            if(currentUnit.route == nextRoute && currentUnit.location == nextLocation) {
                result = true;
                break;
            }
            if(nextRoute != mainRoute && currentUnit.route != mainRoute && currentUnit.location >= 4 && nextLocation == currentUnit.location) {
                result = true;
                break;
            }
            if(nextRoute != mainRoute && currentUnit.route == mainRoute && nextLocation == 7 && currentUnit.location == 20) {
                result = true;
                break;
            }
            if(nextRoute == mainRoute && currentUnit.route != mainRoute && nextLocation == 20 && currentUnit.location == 7) {
                result = true;
                break;
            }
        }
        return result;
    }
}
