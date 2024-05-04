package knihovna_projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL extends Databaze {
	private static Connection conn;
	
	public SQL() throws ClassNotFoundException {
		String jdbcURL = "jdbc:sqlite:knihovna.db";
		Class.forName("org.sqlite.JDBC");
		try {
			conn = DriverManager.getConnection(jdbcURL);
			System.out.println("Uspesne pripojenie k SQLite databaze");
		} catch (SQLException e) {
			System.out.println("Chyba pri spojeni s SQLite databazou");
			e.printStackTrace();
		}
	}
	
	public void nacitajKnihySQL() {
		String query;
		query = "SELECT * FROM roman";
		try(PreparedStatement s = conn.prepareStatement(query)) {
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String[] autori = rs.getString(2).split(", ");
				Roman r = new Roman(rs.getString(1), autori, rs.getString(3), rs.getInt(4), rs.getInt(5)==1);
				prvkyDatabaze.add(r);
			}
		} catch (SQLException e) {
			System.out.println("Chyba pri nacitavani knih z tabulky roman (。_。)");
			e.printStackTrace();
		}
		query = "SELECT * FROM ucebnica";
		try(PreparedStatement s = conn.prepareStatement(query)) {
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String[] autori = rs.getString(2).split(", ");
				Ucebnica u = new Ucebnica(rs.getString(1), autori, rs.getInt(3), rs.getInt(4), rs.getInt(5)==1);
				prvkyDatabaze.add(u);
			}
		} catch (SQLException e) {
			System.out.println("Chyba pri nacitavani knih z tabulky ucebnica (。_。)");
			e.printStackTrace();
		}
	}
	
	public void ulozKnihySQL() {
		for (Kniha k : prvkyDatabaze) {
			String query;
			if (k instanceof Roman) {
				query = "INSERT INTO roman (nazov, autor, zaner, rok, dostupnost) VALUES (?, ?, ?, ?, ?)";
			} 
			else {
				query = "INSERT INTO ucebnica (nazov, autor, rocnik, rok, dostupnost) VALUES (?, ?, ?, ?, ?)";
			}
			try (PreparedStatement s = conn.prepareStatement(query)) {
				s.setString(1, k.getNazov());
				String[] autori = k.getAutor();
				String autorStr = String.join(", ", autori);
				s.setString(2, autorStr);
				if (k instanceof Roman) {
					s.setString(3, ((Roman) k).getZaner());
				} 
				else if (k instanceof Ucebnica) {
					s.setInt(3, ((Ucebnica) k).getRocnik());
				}
				s.setInt(4, k.getRok());
				s.setInt(5, k.isDostupnost()? 1:0);
				s.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Chyba pri ukladani knihy do SQL databazy (。_。)");
				e.printStackTrace();
			}
		}
		System.out.println("Knihy boli uspesne ulozene do databazy ♪(￣y▽￣)╭");
	}
	
	public void vymazSQL() {
		try(Statement s = conn.createStatement()) {
			s.executeUpdate("DELETE FROM roman");
			s.executeUpdate("DELETE FROM ucebnica");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
