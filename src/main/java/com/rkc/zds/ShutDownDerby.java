package com.rkc.zds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class ShutDownDerby {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {

		ShutDownDerby app = new ShutDownDerby();

		app.updateFullName();

	}

	private void updateFullName() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connect = DriverManager.getConnection("jdbc:derby:/_/data/pcm/derbyDB", "PCM", "PCM");
			statement = connect.createStatement();

			String sql = "SELECT CONTACT_ID, FIRSTNAME, LASTNAME FROM PCM_CONTACTS";

			resultSet = statement.executeQuery(sql);

			long rowCount = 0;
			int contactId = 0;
			String firstName = "";
			String lastName = "";
			String fullName = "";
			String test = "";
			boolean isAscii = false;

			while (resultSet.next()) {
				contactId = resultSet.getInt("CONTACT_ID");
				firstName = resultSet.getString("FIRSTNAME");
				lastName = resultSet.getString("LASTNAME");

				fullName = firstName + " " + lastName;

				System.out.println(fullName);

				rowCount++;
			}

			System.out.println("Done. RowCount:" + rowCount);

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect = DriverManager.getConnection("jdbc:derby:/_/data/pcm/derbyDB", "PCM", "PCM;shutdown=true");
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
