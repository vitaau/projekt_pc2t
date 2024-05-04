package knihovna_projekt;

public class Kniha {
	private String nazov;
	private String[] autor;
	private int rok;
	private boolean dostupnost;
	private String typ;
	
	public Kniha(String nazov, String[] autor, int rok, boolean dostupnost) {
		this.nazov = nazov;
		this.setAutor(autor);
		this.setRok(rok);
		this.setDostupnost(dostupnost);
	}

	public String getNazov() {
		return nazov;
	}
	
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public String[] getAutor() {
		return autor;
	}

	public void setAutor(String[] autor) {
		this.autor = autor;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public boolean isDostupnost() {
		return dostupnost;
	}

	public void setDostupnost(boolean dostupnost) {
		this.dostupnost = dostupnost;
	}
	
	public String getTyp() {
		return typ;
	}
	
	public void setTyp(String typ) {
		this.typ = typ;
	}

	public static void add(Roman roman) {
		roman.setTyp("roman");
	}

	public static void add(Ucebnica ucebnica) {
		ucebnica.setTyp("ucebnica");
	}
}

class Roman extends Kniha {
	private String zaner;
	
	public Roman(String nazov, String[] autori, String zaner, int rok, boolean dostupnost) {
		super(nazov, autori, rok, dostupnost);
		this.setZaner(zaner);
	}

	public String getZaner() {
		return zaner;
	}

	public void setZaner(String zaner) {
		this.zaner = zaner;
	}
}

class Ucebnica extends Kniha {
	private int rocnik;
	
	public Ucebnica(String nazov, String[] autori, int rocnik, int rok, boolean dostupnost) {
		super(nazov, autori, rok, dostupnost);
		this.setRocnik(rocnik);
	}

	public int getRocnik() {
		return rocnik;
	}

	public void setRocnik(int rocnik) {
		this.rocnik = rocnik;
	}
}
