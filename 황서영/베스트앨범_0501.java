import java.util.*;

public class 베스트앨범_0501 {

//    static HashSet<String> set;
//    static boolean[] visited;

    public static void main(String[] args) {
//        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
//        int[] plays = {500, 600, 150, 800, 2500};

//        String[] genres = {"pop", "pop", "pop", "rap", "rap"};
//        int[] plays = {45, 50, 40, 60, 70};

        String[] genres = {"a", "b", "b", "c", "c"};
        int[] plays = {5, 5, 40, 5, 5};

        System.out.println(Arrays.toString(solution(genres,plays)));
    }

    public static int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        //key값만 가져와서 genre에 넣어준다
        ArrayList<String> genre = new ArrayList<>();
        for(String key : map.keySet()) {
            genre.add(key);
        }
        Collections.sort(genre, (o1, o2) -> map.get(o2) - map.get(o1)); //key값에 해당하는 value를 내림차순으로 정렬한다.

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < genre.size(); i++) {
            String g = genre.get(i);

            //해당 장르의 음악 중에서 play횟수가 가장 큰 인덱스를 찾는다
            int max = 0;
            int firstIdx = -1;
            for(int j = 0; j < genres.length; j++) {
                if(g.equals(genres[j]) && max < plays[j]) {
                    max = plays[j];
                    firstIdx = j;
                }
            }

            //해당 장르의 음악 중에서 play횟수가 두번째로 큰 인덱스를 찾는다.
            max = 0;
            int secondIdx = -1;
            for(int j = 0; j < genres.length; j++) {
                if(g.equals(genres[j]) && max < plays[j] && j != firstIdx) {
                    max = plays[j];
                    secondIdx = j;
                }
            }

            list.add(firstIdx);
            if(secondIdx >= 0) list.add(secondIdx); //secondIdx는 존재 할수도, 안할수도 있다.
        }

        int[] answer = list.stream().mapToInt(i->i).toArray();
        return answer;


//        HashMap<String, Integer> gMap = new HashMap<>();
//        for (int i = 0; i < genres.length; i++){
//            gMap.put(genres[i], gMap.getOrDefault(genres[i],0) + plays[i]);
//        }
//
//        List<String> genres_ordered = new ArrayList<>();
//        while (gMap.size() != 0){
//            int max = -1;
//            String maxKey = "";
//            for (String key : gMap.keySet()){
//                int tmp = gMap.get(key);
//                if (tmp > max){
//                    max = tmp;
//                    maxKey = key;
//                }
//            }
//            genres_ordered.add(maxKey);
//            gMap.remove(maxKey);
//        }
//
//        HashMap<Integer,Integer> map = new HashMap<>();
//        for (int i = 0; i < plays.length; i++){
//            map.put(i, plays[i]);
//        }
//
//        int [] arr = Arrays.stream(plays).boxed().sorted(Comparator.reverseOrder()).mapToInt(i->i).toArray();
//
//        List<Integer> list = new ArrayList<>();
//        set = new HashSet<>();
//        visited = new boolean[plays.length];
//
//        for(String gen : genres_ordered){
//            for (int i = 0; i < plays.length ; i++){
//                int index = _getKey(map, arr, genres);
//                if(index < 0) break;
//
//                if(gen.equals(genres[index])){
//                    list.add(index);
//                    map.remove(index);
//
//                    for (int j = i+1 ; j < plays.length-1; j++){
//                        int index2 = _getKey(map,arr,genres);
//                        if(index2 < 0) break;
//                        if(gen.equals(genres[index2])){
//                            list.add(index2);
//                            set.add(genres[index2]);
//                            map.remove(index2);
//                            visited = new boolean[plays.length];
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
//
//        int[] answer = list.stream().mapToInt(i->i).toArray();
//        return answer;
    }

//    private static int _getKey (HashMap<Integer,Integer> map, int [] arr, String[] genres){
//
//        for(int i = 0; i < arr.length; i++){
//            if(!visited[i]){
//                visited[i] = true;
//
//                for(int k : map.keySet()){
//                    if(map.get(k) == arr[i] && !set.contains(genres[k])){
//                        return k;
//                    }
//                }
//            }
//        }
//
//        return -1 ;
//    }
}
