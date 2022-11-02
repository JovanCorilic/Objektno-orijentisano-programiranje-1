package zadataci;

public class Zadatak3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]A = {3,6,25,7,2,44,2,-15,-27,-14,-6};
		System.out.println(najveciEI(A));
		System.out.println(najmanjiEI(A));
		System.out.println(srednjaVrednost(A));
		System.out.println(promeniSve(A, srednjaVrednost(A)));
	}
	
	static public int najveciEI(int[] A) {
		int max=0;
		for(int i = 0;i<A.length;i++) {
			if(i==0) {
				max = A[i];
				continue;
			}
			if(max<A[i]) {
				max = A[i];
			}
		}
		return max;
	}
	
	static public int najmanjiEI(int[]A) {
		
		int max=0;
		for(int i = 0;i<A.length;i++) {
			if(i==0) {
				max = A[i];
				continue;
			}
			if(max>A[i]) {
				max = A[i];
			}
		}
		return max;
	}
	
	static public int srednjaVrednost(int[]A) {
		int srednja = 0;
		for(int temp : A) {
			srednja += temp;
		}
		return srednja/A.length;
	}
	
	static public String promeniSve(int[]A,int srednja) {
		for(int i = 0;i<A.length;i++) {
			if(A[i]<0) {
				A[i]+=srednja;
			}else
				A[i]-=srednja;
		}
		String temp = "";
		for(int neko : A)
			temp +=neko + " ";
		return temp;
	}

}
