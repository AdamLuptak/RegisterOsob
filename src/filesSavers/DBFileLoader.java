package filesSavers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.ListRegister;
import core.Person;
import core.Register;

public class DBFileLoader implements RegisterLoader {

	private Register register = new ListRegister(20);
	private String QUERY = null;
	private Connection con = new DBConnection().connect();
	private Statement stmt = null;
	private ResultSet rs = null;

	@Override
	public void save(Register register) {

		QUERY = "INSERT INTO person (id, name, phoneNumber) VALUES (?, ?, ?)";
		try (Connection con = new DBConnection().connect(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.execute("delete from person");
			for (int count = 0; count < register.getCount(); count++) {
				stmt.setInt(1, count + 1);
				stmt.setString(2, register.getPerson(count).getName());
				stmt.setString(3, register.getPerson(count).getPhoneNumber());
				stmt.executeUpdate();
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * ALTER TABLE person CHANGE COLUMN surname phoneNumber varchar(32);
	 */
	@Override
	public Register load() {
		try {
			QUERY = "SELECT id, name, phoneNumber FROM person";
			stmt = con.createStatement();
			rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				// System.out.printf("%4d %-32s %-32s%n", rs.getInt(1),
				// rs.getString(2), rs.getString(3));
				register.addPerson(new Person(rs.getString(2), rs.getString(3)));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return register;
	}

	@Override
	public void fill(Register register) {
		try {
			Connection con = new DBConnection().connect();
			QUERY = "INSERT INTO person (id, name, phoneNumber) VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(QUERY);
			stmt.execute("delete from person");
			stmt.setInt(1, 1);
			stmt.setString(2, "Fero");
			stmt.setString(3, "090554581");
			stmt.executeUpdate();
			stmt.setInt(1, 2);
			stmt.setString(2, "Ferasdso");
			stmt.setString(3, "0925854411");
			stmt.executeUpdate();
			stmt.setInt(1, 3);
			stmt.setString(2, "peter");
			stmt.setString(3, "0925854411");
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
