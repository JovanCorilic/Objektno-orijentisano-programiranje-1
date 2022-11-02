package zadaci;

public class Zadatak7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = { -10, 3, 16, 1, 4, -2};
		int max =0;
		int min =0;
		for(int i = 0;i<A.length;i++) {
			if(i==0) {
				max = A[i];
				min = A[i];
			}else {
				if(max<A[i])
					max=A[i];
				if(min>A[i])
					min=A[i];
			}
			
		}
		System.out.println(max + " "+ min);
		int srednja = 0;
		for(int temp : A) {
			srednja +=temp;
		}
		System.out.println(srednja/A.length);
		
		for(int temp : A) {
			if((srednja/A.length)>temp) {
				if(temp >=0)
					System.out.println(temp);
			}
		}
	}

}
