package zadaci;

public class Zadatak6 {
	
	public static void main(String[] args) {
		int br = 0;
		for(int i = 0;i<12;i++) {
			for(int j = 0;j<30;j++) {
				br++;
			}
		}
		System.out.println(br*16);
		
		br = 0;
		for(int i = 0;i<12;i++) {
			int broj;
			if((i+1)%2==0)
				broj = 30;
			else
				broj = 31;
			for(int j = 0;j<broj;j++) {
				br++;
			}
		}
		System.out.println(br*16);
		
		br = 0;
		int stanje = 0;
		for(int i = 0;i<12;i++) {
			int broj;
			if((i+1)%2==0)
				broj = 30;
			else
				broj = 31;
			for(int j = 0;j<broj;j++) {
				br++;
				if(i==3 && j==10)
					stanje = br;
			}
		}
		System.out.println(br*15 + stanje);
	}
	
	
	
}
