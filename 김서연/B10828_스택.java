package baekjoon_automata_basic;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B10828_스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int oprtnNum = Integer.parseInt(br.readLine());

        // 스택 - ArrayList로 구현
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < oprtnNum; i++) {
            process(br.readLine(), list, bw);
        }

        bw.flush();
        bw.close();
    }
//  1. 결과값만 반환하면 될거 같은데?
    static void process(String operation, ArrayList<Integer> list, BufferedWriter bw) throws IOException {
        StringTokenizer st = new StringTokenizer(operation);

        switch (st.nextToken()) {
            case "push" :
                list.add(Integer.parseInt(st.nextToken()));
                break;
            case "pop" :
                pop(list, bw);
                break;
            case "size" :
                size(list, bw);
                break;
            case "empty" :
                empty(list, bw);
                break;
            case "top" :
                top(list, bw);
                break;
            default :
        }
    }

    private static void top(ArrayList<Integer> list, BufferedWriter bw) throws IOException {
        if (list.size() > 0) {
            bw.write(list.get(list.size() - 1) + "\n");
        } else {
            bw.write("-1" + "\n");
        }
    }

    private static void empty(ArrayList<Integer> list, BufferedWriter bw) throws IOException {
        if (list.size() > 0) {
            bw.write("0" + "\n");
        } else {
            bw.write("1" + "\n");
        }
    }

    private static void size(ArrayList<Integer> list, BufferedWriter bw) throws IOException {
        bw.write(list.size() + "\n");
    }

    private static void pop(ArrayList<Integer> list, BufferedWriter bw) throws IOException {
        if (list.size() > 0) {
            bw.write(list.get(list.size() - 1) + "\n");
            list.remove(list.size() - 1);
        } else {
            bw.write("-1" + "\n");
        }
    }
}
