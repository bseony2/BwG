public class BaekJoon_올림픽 {

	static ArrayList<medal> list; // 국가별 정보 리스트 저장
	static int N,target; // 국가의 개수, 등수를 원하는 국가의 num
	static int cnt; // 나보다 순위가 높을 국가의 수를 저장 할 int
	static int index; // target의 인덱스를 저장 할 int
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		list = new ArrayList<medal>();
		cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			list.add(new medal(num, gold, silver, bronze));
			if(num==target) {
				index = i;
			}
		}
		for(int i=0; i<N; i++) {
			if(list.get(index).gold < list.get(i).gold) {
				cnt++;
			}
			else if(index != i && list.get(index).gold == list.get(i).gold) {
				if(list.get(index).silver < list.get(i).silver) {
					cnt++;
				}
				else if(index != i && list.get(index).silver == list.get(i).silver) {
					if(list.get(index).bronze < list.get(i).bronze) {
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt+1);
	}

	static class medal {
		int num;
		int gold;
		int silver;
		int bronze;

		public medal(int num, int gold, int silver, int bronze) {
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

	}
}
