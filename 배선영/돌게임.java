import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 돌게임 {
    public static void main(String...args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Integer.parseInt(br.readLine()) % 2 == 0 ? "CY" : "SK");
    }
}
