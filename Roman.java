class Roman{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Choice:\n\t1.Roman to Arabic\n\t2.Arabic to Roman\n");
		int ch = sc.nextInt();
		switch(ch){
			case 1:
				System.out.print("Enter Roman Number: ");
				String r = sc.next();
				System.out.println("Arabic :"+arabic(r));
				break;
			case 2:
				System.out.print("Enter Arabic Number: ");
				int n = sc.nextInt();
				System.out.println("Roman: "+roman(n));
				break;
			default:
				System.out.println("Invalid choice.");
				System.exit(1);
		}
	}
}