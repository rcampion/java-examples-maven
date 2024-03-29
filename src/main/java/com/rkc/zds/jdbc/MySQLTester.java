package com.rkc.zds.jdbc;

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

import com.rkc.zds.enums.BookDto;
import com.rkc.zds.enums.BookCategoryEnum;
import com.rkc.zds.linkedlist.SinglyLinkedList;
import com.rkc.zds.linkedlist.impl.SinglyLinkedListImpl;

public class MySQLTester {
	
	public static void main(String[] args) {
		
		MySQLTester app = new MySQLTester();
		
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<BookDto> list = new ArrayList<BookDto>();
		SinglyLinkedList linkedList = new SinglyLinkedListImpl();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth?createDatabaseIfNotExist=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "auth_user", "ChangeIt");
			statement = connect.createStatement();

			String sql = "SELECT * FROM Book";

			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				BookDto dto = new BookDto();
				dto.setId(resultSet.getString("id"));
				dto.setTitle(resultSet.getString("Title"));
				dto.setAuthor(resultSet.getString("Author"));
				int category = resultSet.getInt("Category");

				BookCategoryEnum catEnum = BookCategoryEnum.fromInt(category);

				dto.setCategory(catEnum);

				list.add(dto);
				linkedList.append(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list);
		linkedList.display();

		Map<BookCategoryEnum, List<BookDto>> newMap = new HashMap<BookCategoryEnum, List<BookDto>>();
		newMap = list.stream().collect(Collectors.groupingBy(BookDto::getCategory));
//				.collect(Collectors.toList());
		System.out.println("original list");
		System.out.println(list);
		System.out.println("streamed map");
		System.out.println(newMap);

		for (Entry<BookCategoryEnum, List<BookDto>> entry : newMap.entrySet()) {
			System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
		}
		
		BookCategoryEnum key = BookCategoryEnum.fromInt(2);
		
		List<BookDto> entry =  newMap.get(key);

		System.out.println(entry);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth?createDatabaseIfNotExist=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "auth_user", "ChangeIt");
			statement = connect.createStatement();
			list.clear();

			String search = "efactor";
			PreparedStatement ps = null;
			
			String sql = "SELECT * FROM Book WHERE Title LIKE ?";

			ps = connect.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				BookDto dto = new BookDto();
				dto.setId(resultSet.getString("id"));
				dto.setTitle(resultSet.getString("Title"));
				dto.setAuthor(resultSet.getString("Author"));
				int category = resultSet.getInt("Category");

				BookCategoryEnum catEnum = BookCategoryEnum.fromInt(category);

				dto.setCategory(catEnum);

				list.add(dto);
				//linkedList.append(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list);
		
		//app.updateTest(list);
		
	}

	private void updateTest(List<BookDto> list) {
		//      String sql = "UPDATE Registration " +
        // "SET age = 30 WHERE id in (100, 101)";
		for(BookDto entry:list) {
			
			Connection connect = null;
			PreparedStatement ps = null;

			int result = 0;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth?createDatabaseIfNotExist=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "auth_user", "ChangeIt");
				//statement = connect.createStatement();

				int i = 0;
				String title = entry.getTitle() + i;

				
				String sql = "UPDATE Book SET Title = ? WHERE id = ?";

				ps = connect.prepareStatement(sql);
				ps.setString(1, title);
				ps.setString(2, entry.getId());
				result = ps.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (connect != null)
						connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
			
		}
		
	}

}
