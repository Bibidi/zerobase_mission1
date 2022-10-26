package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.WifiSearchHistory;

public class WifiSearchHistoryRepository {
	private static String dbName = "/Users/byungdeukkim/eclipse-workspace/mission/wifi-info.db";
			
	public boolean insert(WifiSearchHistory wifiSearchHistory) {
		Connection connection = null;
		int result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "INSERT INTO WIFI_SEARCH_HISTORY " 
					+ "(latitude, longitude, lookup_date) "
					+ "VALUES (?, ?, datetime('now'));";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, wifiSearchHistory.getLatitude());
			ps.setDouble(2,  wifiSearchHistory.getLongitude());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		return result > 0;
	}
	
	public List<WifiSearchHistory> getAll() {
		Connection connection = null;
		List<WifiSearchHistory> result = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "SELECT * FROM WIFI_SEARCH_HISTORY;";
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				WifiSearchHistory wifiSearchHistory = new WifiSearchHistory();
				wifiSearchHistory.setId(rs.getInt("history_id"));
				wifiSearchHistory.setLatitude(rs.getDouble("latitude"));
				wifiSearchHistory.setLongitude(rs.getDouble("longitude"));
				wifiSearchHistory.setLookupDate(rs.getDate("lookup_date"));
				
				result.add(wifiSearchHistory);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		return result;
	}
	
	public boolean delete(WifiSearchHistory wifiSearchHistory) {
		Connection connection = null;
		int result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "DELETE FROM WIFI_SEARCH_HISTORY WHERE history_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, wifiSearchHistory.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		return result > 0;
	}
}
