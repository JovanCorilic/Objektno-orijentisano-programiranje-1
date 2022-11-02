package zadataci;

public class Zadatak1 {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String text = "Norveanin Kjetil Jansrud osvojio je zlatnu medalju u superveleslalomu na Zimskim \r\n"
				+ "olimpijskim igrama u Soèiju pošto je za 30 stotih delova sekunde bio bri od \r\n"
				+ "drugoplasiranog Amerikanca Endrjua Vajbrehta. ";
		System.out.println(obrnutoSlova(text));
		System.out.println(obrnutoReci(text));
	}
	
	
	static public String obrnutoSlova(String text) {
		String temp = "";
		for(int i = text.length()-1;i>-1;i--) {
			temp += text.charAt(i);
		}
		return temp;
	}
	
	public static String obrnutoReci(String text) {
		String temp = "";
		String lista[] = text.split(" ");
		for(int i = lista.length-1;i>-1;i--) {
			temp += lista[i]+"\n";
		}
		
		
		return temp;
		
	}

}
