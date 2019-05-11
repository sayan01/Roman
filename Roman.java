/*
	Roman
	Converts between Roman and Western Arabic Numerals
	- by Sayan Ghosh (2019)
*/
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
				if(n<0 || n>=4000){	// Maximum of 3999 can be represented in proper subtractive Roman numerals
					System.err.print("Number cannot be converted to Roman.");
					System.exit(1);
				}
				System.out.println("Roman: "+roman(n));
				break;
			default:
				System.err.println("Invalid choice.");
				System.exit(1);
		}
	}
	/*
		Converts Roman numeral into Arabic numbers.
		Parameter - String roman - Roman numeral to convert.
		Returns - int - Arabic equivalent.
	*/
	public static int arabic(String roman){
		int rv = 0;										// Value to return at end
		for( int i = roman.length()-1 ; i >= 0 ; i--){	// Iterate over all symbols of roman number (from right to left)
			int ct = r_d(roman.charAt(i));				// get value of current symbol
			if(i==roman.length()-1){					// if is the rightmost symbol then directly add it and continue
				rv = ct;
			}
			else{										// Otherwise, see if the symbol to its right was bigger
				int nt = r_d(roman.charAt(i+1));		
				if(ct<nt)	rv += -ct;					// if yes, then subtract current value
				else 		rv += +ct;					// otherwise add it
			}
		}
		return rv;									// Return value
	}
	/*
		Converts Arabic numeral into Roman numbers.
		Parameter - int arabic - Arabic numeral to convert.
		Returns - String - Roman equivalent.
	*/
	public static String roman(int arabic){
		String rv = "";								// Value to return at end
		int[] val = {1,5,10,50,100,500,1000};		// values which are represented by roman symbols. In order
		String vel = "IVXLCDM";						// symbols which represent those values. In order
		int k = val.length-1;						// start from biggest symbol. ie. M
		while(arabic > 0){							// iterate till whole number has been expressed
			if(arabic/val[k] != 0){					// if current symbol is smaller than the number, 
				int t = arabic/val[k];				// it can be used to express the number
				arabic %= val[k];					// new number = old number without the parts expressed by current symbol
				for(int i = 0; i < t; i++)			// add as many symbols as required to express number
					rv += vel.charAt(k);			
			}
			else if(k != 0 && (arabic+val[k-1])/val[k] != 0 && val[k-1] == 0.2 * val[k]){	
				// if the symbol could be just used, if number was one time more by smaller symbol's value, but smaller is
				// 1/5th of current symbol (not with 2x pairs, like V and X, only with 5x pairs, eg I-V,X-L,C-D) then
				arabic = (arabic + val[k-1])%val[k];
				rv += vel.charAt(k-1) + "" + vel.charAt(k);		// add the smaller symbol + this symbol (eg. IV to represent 4)
			}													
			else{
				k--;		// If current symbol cannot be used to represent the number, go to next smaller symbol
			}
		}
		return rv;				// Return value
	}
	/*
		Converts Arabic single symbol into Roman equivalent.
		Parameter - char c - Roman symbol to convert
		Returns - String - Roman equivalent.
	*/
	private static int r_d(char c){
		c = Character.toUpperCase(c);	// Converts lowercase to uppercase for convenience
		if(!"IVXLCDM".contains(c+"")){	// If symbol is not a valid roman numeral symbol
			System.err.println("Invalid Syntax.");	// Print error message and terminate
			System.exit(1);
		}										// Otherwise
		int[] val = {1,5,10,50,100,500,1000};	// values represented by symbols, in order
		return val["IVXLCDM".indexOf(c)];		// get index of symbol, then return element in val[] in that index
												// Thus return the corresponding value for the symbol
	}
}