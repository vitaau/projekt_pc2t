package knihovna_projekt;

import java.util.Scanner;
 
public class Knihovna {
	
	public static int lenCeleCisla(Scanner sc) {
		int cislo = 0;
		try {
			cislo = sc.nextInt();
		} catch(Exception e) {
			System.out.println("Nastala vynimka typu "+e.toString());
			System.out.println("Zadajte prosim cele cislo!");
			sc.nextLine();
			cislo = lenCeleCisla(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		Databaze mojeDatabaze = new Databaze();
		SQL mojeDatabazeSQL = new SQL();
		int volba;
		System.out.println("\n(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ Vitajte vo vašej kniznej databaze ");
		mojeDatabazeSQL.nacitajKnihySQL();
		System.out.println("Tu su dostupne akcie:");
		
		
		while (true) 
		{
			System.out.println("__________________________________________");
			System.out.println("1 Pridanie novej knihy");
			System.out.println("2 Uprava knihy");
			System.out.println("3 Zmazanie knihy");
			System.out.println("4 Oznacenie za vypozicane/vratene");
			System.out.println("5 Vypis knih v abecednom poradi");
			System.out.println("6 Vyhladanie knihy");
			System.out.println("7 Vypis knih vybraneho autora");
			System.out.println("8 Vypis knih vybraneho zanru");
			System.out.println("9 Ulozenie informacii o knihe do suboru");
			System.out.println("10 Nacitanie informacii o knihe zo suboru");
			System.out.println("11 Koniec");
			System.out.println("__________________________________________");
			System.out.print("Aku akciu si zelate vykonat? --> ");
			
			volba = lenCeleCisla(sc);
			switch (volba) 
			{
			case 1:
				mojeDatabaze.pridajKnihu();
				break;
			case 2:
				mojeDatabaze.upravKnihu();
				break;
			case 3:
				mojeDatabaze.vymazKnihu();
				break;
			case 4:
				mojeDatabaze.oznacKnihu();
				break;
			case 5:
				mojeDatabaze.vypisKnih();
				break;
			case 6:
				mojeDatabaze.vyhladajKnihu();
				break;
			case 7:
				mojeDatabaze.vypisKnihyAutor();
				break;
			case 8:
				mojeDatabaze.vypisKnihyZaner();
				break;
			case 9:
				mojeDatabaze.ulozKnihu();
				break;
			case 10:
				mojeDatabaze.nacitajKnihu();
				break;
			case 11:
				mojeDatabazeSQL.vymazSQL();
				mojeDatabazeSQL.ulozKnihySQL();
				System.out.println("ヾ(￣▽￣) Bye~Bye~ Koniec programu");
				System.exit(0);
				break;
			default:
				System.out.println("Neplatna volba (* ￣︿￣)");
				break;
			}
		}
	}
}
