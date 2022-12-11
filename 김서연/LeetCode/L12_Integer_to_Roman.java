package baekjoon_automata_basic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L12_Integer_to_Roman {
    public static void main(String[] args) {
        intToRoman(13);
    }

    public static String intToRoman(int num) {
        int[] romanNum = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L","XL", "X", "IX", "V", "IV", "I"};
        ArrayList<String> romanNumSymbol = new ArrayList<>(Stream.of(symbol)
                .collect(Collectors.toList()));

        StringBuilder rslt = new StringBuilder();
        int i = 0;

        while (num > 0) {
            int number = num / romanNum[i];
            for (int j = 0; j < number; ++j) {
                rslt.append(romanNumSymbol.get(i));
            }
            num -= number * romanNum[i];
            ++i;
        }

        return rslt.toString();
    }
}



