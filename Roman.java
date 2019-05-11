import java.util.*;
class Roman{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Choice:\n\t1.Roman to Arabic\n\t2.Arabic to Roman\n");
		int ch = sc.nextInt();
		switch(ch){
			case 1:
				System.out.print("Enter Roman Number: ");
				String r = sc.next();
				System.out.println("Arabic: "+arabic(r));
				break;
			case 2:
				System.out.print("Enter Arabic Number: ");
				int n = sc.nextInt();
				if(n<0 || n>=4000){
					System.err.print("Number cannot be converted to Roman.");
					System.exit(1);
				}
				// System.out.println("Roman: "+roman(n));
				break;
			default:
				System.err.println("Invalid choice.");
				System.exit(1);
		}
	}
	public static int arabic(String roman){
		int rv = 0;
		for( int i = roman.length()-1 ; i >= 0 ; i--){
			int ct = r_d(roman.charAt(i));
			if(i==roman.length()-1){
				rv = ct;
			}
			else{
				int nt = r_d(roman.charAt(i+1));
				if(ct<nt)	rv += -ct;
				else 		rv += +ct;
			}
		}
		return rv;
	}
	
	private static int r_d(char c){
		c = Character.toUpperCase(c);
		if(!"IVXLCDM".contains(c+"")){
			System.err.println("Invalid Syntax.");
			System.exit(1);
		}
		int[] val = {1,5,10,50,100,500,1000};
		return val["IVXLCDM".indexOf(c)];
	}
}