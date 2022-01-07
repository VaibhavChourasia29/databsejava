import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		
		String ch; int n;
		Scanner sc = new Scanner(System.in);
	
		do {	
		System.out.print("SELECT OPERATION TO PERFORM\n 1.CONNECT TO DATABASE"
				+ "\n2.CREATE DATABASE \n3. CREATE TABLE \n4.INSERT INTO TABLE \n5.SHOW ALL DETAIL OF TABLE \n6.SHOW SPECFIC ROW ");
		n=sc.nextInt();
			
			switch(n) {
			case 1: Database.connect(); break;
			case 2: {	System.out.println("ENTER DATABASE NAME WITH EXTENSION(.db)");
					String db = sc.nextLine();
					Database.createNewDatabase(db);
					Database.connectToNewDatabase(db);
					break;
			}
			case 3: Database.createNewTable(); break;
			case 4:	{ String movie,actor,actress,director,releasedate;
					System.out.println("ENTER MOVIE NAME :-");
						movie = sc.nextLine();
						System.out.println("ENTER ACTOR NAME :-");
						actor = sc.nextLine();
						System.out.println("ENTER ACTRESS NAME :-");
						actress = sc.nextLine();
						System.out.println("ENTER DIRECTOR NAME :-");
						director= sc.nextLine();
						System.out.println("ENTER RELEASE DATE NAME :-");
						releasedate= sc.nextLine();
				Database.insert(movie,actor,actress,director,releasedate); break;
			}
			case 5: Database.readAllData(); break;
			case 6: Database.readSpecificRow(); break;
			
			
			}
			
			System.out.println("press y to perform other operation");
			ch = sc.next();
			
		}while(ch=="y");
		
		System.out.println("THANK YOU FOR USING ....");
		
}
}
