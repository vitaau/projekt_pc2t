package knihovna_projekt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Databaze {
	private Scanner sc;
	public static ArrayList<Kniha> prvkyDatabaze;
	
	public Databaze() {
		prvkyDatabaze = new ArrayList<>();
		sc = new Scanner(System.in);
	}
	
	public static int lenCeleCisla(Scanner sc) {
		int cislo = 0;
		try {
			cislo = sc.nextInt();
		} catch(Exception e) {
			System.out.println("Zadajte prosim cele cislo!");
			sc.nextLine();
			cislo = lenCeleCisla(sc);
		}
		return cislo;
	}
	
	public static boolean lenAnoNie(Scanner sc) {
		sc = new Scanner(System.in);
		boolean dostupnost = false;
		String odpoved = sc.next();
		if (odpoved.equalsIgnoreCase("ano")) {
			dostupnost = true;				
		} else if (odpoved.equalsIgnoreCase("nie")) {
			dostupnost = false;
		} else {
			System.out.println("Zadajte prosim iba ano alebo nie!");
			dostupnost = lenAnoNie(sc);
		}
		return dostupnost;
	}
	
	private Kniha najdiKnihu(String nazov) {
		for (Kniha kniha : prvkyDatabaze) {
			if (kniha.getNazov().equalsIgnoreCase(nazov)) {
				return kniha;}
		} return null;
	}
	
	public void pridajKnihu() {
		sc = new Scanner(System.in);
		System.out.print("\nZadajte nazov knihy: ");
		String nazov = sc.nextLine();
		System.out.print("Zadajte autora/autorov knihy (oddelene ciarkou): ");
		String autorInput = sc.nextLine();
		String [] autor = autorInput.split(", ");
		System.out.print("Zadajte rok vydania knihy: ");
		int rok = lenCeleCisla(sc);
		System.out.print("Je kniha dostupna? (ano/nie): ");
		boolean dostupnost = lenAnoNie(sc);
		sc.nextLine();
		System.out.print("Zadajte typ knihy (roman/ucebnica): ");
		String typ = sc.nextLine();
		if (typ.equalsIgnoreCase("roman")) {
			System.out.print("Zadajte zaner romanu: ");
			String zaner = sc.nextLine();
			prvkyDatabaze.add(new Roman(nazov, autor, zaner, rok, dostupnost));
			System.out.println("\nKniha bola uspesne pridana (〃￣︶￣)人(￣︶￣〃)");
		} 
		else if (typ.equalsIgnoreCase("ucebnica")) {
			System.out.print("Zadajte pre aky rocnik je ucebnica vhodna: ");
			int rocnik = lenCeleCisla(sc);
			prvkyDatabaze.add(new Ucebnica(nazov, autor, rocnik, rok, dostupnost));
			System.out.println("\nKniha bola uspesne pridana (〃￣︶￣)人(￣︶￣〃)");
		}
		else {
			System.out.println("Nevhodny vstup ᕦ(ò_óˇ)ᕤ");
		}
	}

	public void upravKnihu() {
		sc = new Scanner(System.in);
		System.out.print("\nZadajte nazov knihy, ktoru chcete upravit: ");
		String nazov = sc.nextLine();
		Kniha najdenaKniha = najdiKnihu(nazov);
		if (najdenaKniha != null) {
			System.out.println("Vyberte co chcete upravit: ");
			System.out.println("1 Autor/i");
			System.out.println("2 Rok vydania");
			System.out.println("3 Dostupnost");
			
			int volba = lenCeleCisla(sc);
			sc.nextLine();
			switch (volba) {
			case 1: 
				System.out.print("Zadajte autora/autorov: ");
				String[] autor = sc.nextLine().split(", ");
				najdenaKniha.setAutor(autor);
				System.out.println("Kniha bola uspesne upravena (●^◡^●)");
				break;
			case 2:
				System.out.print("Zadajte rok vydania: ");
				int rok = lenCeleCisla(sc);
				najdenaKniha.setRok(rok);
				System.out.println("Kniha bola uspesne upravena (●^◡^●)");
				break;
			case 3:
				System.out.print("Je kniha dostupna? (ano/nie): ");
				boolean dostupnost = lenAnoNie(sc);
				najdenaKniha.setDostupnost(dostupnost);
				System.out.println("Kniha bola uspesne upravena (●^◡^●)");
				break;
			default:
				System.out.println("Neplatna volba ╰（‵□′）╯");
				break;
			}
		} else {
			System.out.println("Kniha s tymto nazvom sa nenasla (っ °Д °;)っ");
		}
	}
	
	public void vymazKnihu() {
		sc = new Scanner(System.in);
		System.out.print("Zadajte nazov knihy, ktoru chcete zmazat: ");
		String nazov = sc.nextLine();
		Kniha najdenaKniha = najdiKnihu(nazov);
		if (najdenaKniha != null) {
			prvkyDatabaze.remove(najdenaKniha);
			System.out.println("Kniha bola zmazana ヾ(￣▽￣) Bye~Bye~");
		} else {
			System.out.println("Kniha s tymto nazvom sa nenasla (っ °Д °;)っ");
		}
	}
	
	public void oznacKnihu() {
		sc = new Scanner(System.in);
		System.out.println("Zadajte nazov knihy, ktoru chcete oznacit: ");
		String nazov = sc.nextLine();
		Kniha najdenaKniha = najdiKnihu(nazov);
		if (najdenaKniha != null) {
			System.out.print("\nJe kniha dostupna? (ano/nie): ");
			boolean dostupnost = lenAnoNie(sc);
			najdenaKniha.setDostupnost(dostupnost);
			System.out.println("Stav knihy bol aktualizovaný (*^▽^*)");
		}
		else {
			System.out.println("Kniha s tymto nazvom sa nenasla (っ °Д °;)っ");
		}
	}
	
	public void vypisKnih() {
		Collections.sort(prvkyDatabaze, Comparator.comparing(Kniha::getNazov));
		for (Kniha kniha : prvkyDatabaze) {
			System.out.println("\nNazov: "+ kniha.getNazov());
			System.out.println("Autor/i: "+ String.join(", ", kniha.getAutor()));
			System.out.println("Druh: "+(kniha instanceof Roman ? "Roman" : "Ucebnica"));
			if (kniha instanceof Roman) {
				System.out.println("Zaner: "+ ((Roman)kniha).getZaner());
			}
			else if (kniha instanceof Ucebnica) {
				System.out.println("Rocnik: "+ ((Ucebnica)kniha).getRocnik());
			}
			System.out.println("Rok vydania: "+ kniha.getRok());
			System.out.println("Stav dostupnosti: "+ (kniha.isDostupnost()? "Dostupna （＾∀＾●）ﾉｼ":"Vypozicana ( ´･.･)ﾉ(._.`)"));
			System.out.println("__________________________________________");
		}
		if(prvkyDatabaze.isEmpty()) {
			System.out.println("V databaze nemas ulozene ziadne knihy <@_@>");
		}
	}
	
	public void vyhladajKnihu() {
		sc = new Scanner(System.in);
		System.out.println("Zadajte nazov knihy: ");
		String nazov = sc.nextLine();
		Kniha najdenaKniha = najdiKnihu(nazov);
		if(najdenaKniha != null) {
			System.out.println("\nNazov: "+ najdenaKniha.getNazov());
			System.out.println("Autor/i: "+ String.join(", ", najdenaKniha.getAutor()));
			System.out.println("Druh: "+(najdenaKniha instanceof Roman ? "Roman" : "Ucebnica"));
			if (najdenaKniha instanceof Roman) {
				System.out.println("Zaner: "+ ((Roman)najdenaKniha).getZaner());
			}
			else if (najdenaKniha instanceof Ucebnica) {
				System.out.println("Rocnik: "+ ((Ucebnica)najdenaKniha).getRocnik());
			}
			System.out.println("Rok vydania: "+ najdenaKniha.getRok());
			System.out.println("Stav dostupnosti: "+ (najdenaKniha.isDostupnost()? "Dostupna （＾∀＾●）ﾉｼ":"Vypozicana ( ´･.･)ﾉ(._.`)"));
		}
		else {
			System.out.println("Kniha s tymto nazvom sa nenasla (っ °Д °;)っ");
		}
	}
	
	public void vypisKnihyAutor() {
		sc = new Scanner(System.in);
		System.out.println("Zadajte meno autora: ");
		String menoAutora = sc.nextLine();
		ArrayList<Kniha> autoroveKnihy = new ArrayList<>();
		for (Kniha kniha : prvkyDatabaze) {
			for (String autor : kniha.getAutor()) {
				if (autor.equalsIgnoreCase(menoAutora)) {
					autoroveKnihy.add(kniha);
					break;
				}
			}
		}
		if (!autoroveKnihy.isEmpty()) {
			Collections.sort(autoroveKnihy, Comparator.comparing(Kniha::getRok));
			System.out.println("\nKnihy autora v chronologickom poradí: ");
			for (Kniha kniha : autoroveKnihy) {
				System.out.println("Nazov: "+kniha.getNazov()+ " -> Rok vydania: "+kniha.getRok());
			}
		} else {
			System.out.println("Autor sa nenasiel ㄟ(▔v ▔)ㄏ");
		}
	}
	
	public void vypisKnihyZaner() {
		sc = new Scanner(System.in);
		System.out.println("Zadajte zaner: ");
		String zaner = sc.nextLine();
		boolean existujeZaner = false;
		System.out.println("Knihy v tomto zanri ( ﾉ ﾟ3ﾟ)ﾉ: ");
		for (Kniha kniha : prvkyDatabaze) {
			if (kniha instanceof Roman &&((Roman)kniha).getZaner().equalsIgnoreCase(zaner)) {
				System.out.println(kniha.getNazov());
				existujeZaner = true;
			}
		}
		if (!existujeZaner) {
			System.out.println("Zaner sa nenasiel ㄟ(▔v ▔)ㄏ");
		}
	}
	
	public void ulozKnihu() {
		sc = new Scanner(System.in);
		System.out.println("Zadajte nazov knihy, ktoru chcete ulozit do textoveho suboru: ");
		String nazov = sc.nextLine();
		Kniha kniha = najdiKnihu(nazov);
		if (kniha != null) {
			try {
				FileWriter subor = new FileWriter(nazov + ".txt");
				subor.write("Nazov: "+ kniha.getNazov());
				subor.write("\nAutor/i: "+ String.join(", ", kniha.getAutor()));
				if (kniha instanceof Roman) {
					subor.write("\nZaner: "+ ((Roman)kniha).getZaner());
				}
				else if (kniha instanceof Ucebnica) {
					subor.write("\nRocnik: "+ ((Ucebnica)kniha).getRocnik());
				}
				subor.write("\nRok vydania: "+ kniha.getRok());
				subor.write("\nStav dostupnosti: "+ (kniha.isDostupnost()? "Dostupna":"Vypozicana"));
				subor.close();
				System.out.println("Informacie o knihe boli uspesne ulozene do suboru q(≧▽≦q)");
			}
			catch (IOException e) {
				System.out.println("Nastala chyba pri ukladani suboru (。_。)");
			}
		}
		else {
			System.out.println("Kniha s tymto nazvom sa nenasla (っ °Д °;)っ");
		}
	}
	
	public void nacitajKnihu () {
		sc = new Scanner(System.in);
		System.out.println("Zadajte nazov suboru, ktory chcete nacitat: ");
		String nazovSuboru = sc.nextLine();
		try {
			File subor = new File(nazovSuboru +".txt");
			Scanner fl = new Scanner(subor);
			while(fl.hasNextLine()) {
			String nazov = fl.nextLine().split(": ")[1];
			String autor = fl.nextLine().split(": ")[1];
			String[] autori = autor.split(", ");
			String ralebou = fl.nextLine().split(": ")[1];
			int rok = Integer.parseInt(fl.nextLine().split(": ")[1]);
			String dostupnoststr = fl.nextLine().split(": ")[1];
			boolean dostupnost = dostupnoststr(dostupnoststr);
			
			if (jeCislo(ralebou)) {
				int rocnik = Integer.parseInt(ralebou);
				prvkyDatabaze.add(new Ucebnica(nazov, autori, rocnik, rok, dostupnost));
			} else {
				String zaner = ralebou;
				prvkyDatabaze.add(new Roman(nazov, autori, zaner, rok, dostupnost));
			}}
			System.out.println("Kniha bola uspesne nacitana zo suboru q(≧▽≦q)");
			fl.close();
		}
		catch (IOException e) {
			System.out.println("Nastala chyba pri nacitani suboru (。_。)");
		}
	}
	
	private static boolean jeCislo(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	private static boolean dostupnoststr(String dostupnoststr) {
		boolean dostupnost = true;
		if (dostupnoststr.equalsIgnoreCase("Vypozicana")) {
			dostupnost = false;
		} 
		else if (dostupnoststr.equalsIgnoreCase("Dostupna")){
			dostupnost = true;
		}
		return dostupnost;
	}
	
	
}