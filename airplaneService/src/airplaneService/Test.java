package airplaneService;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		ArrayList<String> sl = new ArrayList<String>();
		
		for(int i = 0 ;i<10;i++) {
			for(int j = 1 ;j<20; j++) {
				char x =(char)('A'+i);
				String y = String.format("%02d",j);
				sl.add(x+y);
				
			}
		}
		int count = 0;
		for(String data : sl) {
			System.out.print("|"+data+"|");
			count++;
			if(count%5==0) {
				System.out.print("   ");
			}
			if(count%10==0) {
				System.out.println("\n");
			}
		}
		

	}

}
