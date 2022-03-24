package zadataci;

public class Zadatak4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]A = {1,5,10,15};
		int[]B = {20,2,3,17};
		System.out.println(gradjenjeC(A, B));
	}
	
	static public String gradjenjeC(int[]A,int[]B) {
		String temp = "";
		int[]C = new int [A.length];
		for(int i = 0;i<A.length;i++) {
			C[i] = A[i]+B[i];
		}
		
		for(int neko : C)
			temp += neko + " ";
		return temp;
	}

}
