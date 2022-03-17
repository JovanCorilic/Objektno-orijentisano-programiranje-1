package zadaci;

public class Zadatak3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int niz[] = {5,6,7,8,9,34343,20};
		for(int i = 0;i<niz.length;i++) {
			if(i==0) {
				niz[i]=1;
			}else {
				niz[i]+=10;
			}
		}
		for(int temp : niz) {
			System.out.println(temp);
		}
		
		
	}

}
