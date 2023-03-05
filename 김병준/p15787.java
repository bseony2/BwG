    package W0;

    import java.util.HashSet;
    import java.util.Scanner;

    public class p15787 {
        static int n, m;
        static int train[];
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            m = sc.nextInt();
            train = new int[n];
            for(int command = 0; command < m; command++){
                int c, i, x, k;
                c = sc.nextInt();
                i = sc.nextInt();
                if(c == 1){
                    x = sc.nextInt();
                    train[i-1]|= (1<<(x-1));
                } else if(c == 2){  // 하차하게 만든다.
                    x = sc.nextInt();
                    train[i-1] &= (~(1<<(x-1)));
                }else if(c == 3){
                    train[i-1] <<= 1;
                    train[i-1] &= ((1<<20) - 1);
                }else{
                    train[i-1] >>= 1;
                    train[i-1] &= ((1<<20) - 1);
                }
            }
            HashSet<Integer> hs = new HashSet<>();
            for(int num : train){
                hs.add(num& (num));
            }
            System.out.println(hs.size());

        }
    }
