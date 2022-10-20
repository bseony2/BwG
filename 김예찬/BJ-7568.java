package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_7568 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int num;
	public static void main(String[] args) throws IOException {
		num = Integer.parseInt(br.readLine());		

		person [] p = new person[num];
		for (int i=0; i<num;i++) {
			st = new StringTokenizer(br.readLine());
			p[i]= new person(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<p.length;i++) {
			for(int j=0; j<p.length;j++) {
				if(i==j) continue;
				
				if(p[i].height<p[j].height&&p[i].weight<p[j].weight) {
					p[i].rank+=1;
				}
				
			}			
		}
		
		for(int i=0;i<p.length;i++) {
			System.out.print(p[i].rank+" ");
		}
		
	}
}

class person {
		int weight;
		int height;
		int rank = 1;
		person(int weight, int height) { 
			this.weight = weight;
			this.height = height;
		}
		
	}
