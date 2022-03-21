package zadaci;

public class Zadatak11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A [] = {1,2,3,4,5,6,7};
		int B [] = {11,12,13,14,15,16,17};
		
		int C [] = new int[A.length];
		
		int br = 0;
		for(int i =0;i<C.length;i++) {
			br++;
			if(br%2!=0) {
				C[i] = A[br-1] + B[B.length-br];
			}
			else
				C[i] = A[br-1]* B[B.length-br];
		}
		
		for(int temp : C)
			System.out.println(temp);
	}

}
