package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Wifi;

public class WifiInfoRepository {
	private static String dbName = "/Users/byungdeukkim/eclipse-workspace/mission/wifi-info.db";
	
	public boolean insert(Wifi wifi) {
		Connection connection = null;
		int result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "INSERT INTO WIFI_INFO "
					+ "(`management_number`, `district`, `name`, `address1`, `address2`,"
					+ "`installed_floor`,`installation_type`,`installation_agency`,`service_classification`, `mesh_type`, "
					+ "`installation_year`, `in_out_door`, `connection_environment`, `latitude`, `longitude`, "
					+ "`work_date`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, wifi.getManagementNumber());
			ps.setString(2, wifi.getDistrict());
			ps.setString(3, wifi.getName());
			ps.setString(4, wifi.getAddress1());
			ps.setString(5, wifi.getAddress2());
	
			ps.setString(6, wifi.getInstalledFloor());
			ps.setString(7, wifi.getInstallationType());
			ps.setString(8, wifi.getInstallationAgency());
			ps.setString(9, wifi.getServiceClassification());
			ps.setString(10, wifi.getMeshType());
			
			ps.setString(11, wifi.getInstallationYear());
			ps.setString(12, wifi.getInOutDoor());
			ps.setString(13, wifi.getConnectionEnvironment());
			ps.setDouble(14, wifi.getLatitude());
			ps.setDouble(15, wifi.getLongitude());
			
			ps.setDate(16, new Date(wifi.getWorkDate().getTime()));
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
	
	public boolean delete(Wifi wifi) {
		Connection connection = null;
		int result = 0;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "DELETE FROM WIFI_INFO WHERE management_number = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, wifi.getManagementNumber());

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
	
	public List<Wifi> readAll() {
		Connection connection = null;
		List<Wifi> result = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

			String sql = "SELECT * FROM WIFI_INFO";
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Wifi wifi = new Wifi();
				wifi.setManagementNumber(rs.getString("management_number"));
				wifi.setDistrict(rs.getString("district"));
				wifi.setName(rs.getString("name"));
				wifi.setAddress1(rs.getString("address1"));
				wifi.setAddress2(rs.getString("address2"));
				
				wifi.setInstalledFloor(rs.getString("installed_floor"));
				wifi.setInstallationType(rs.getString("installation_type"));
				wifi.setInstallationAgency(rs.getString("installation_agency"));
				wifi.setServiceClassification(rs.getString("service_classification"));
				wifi.setMeshType(rs.getString("mesh_type"));
				
				wifi.setInstallationYear(rs.getString("installation_year"));
				wifi.setInOutDoor(rs.getString("in_out_door"));
				wifi.setConnectionEnvironment(rs.getString("connection_environment"));
				wifi.setLatitude(rs.getDouble("latitude"));
				wifi.setLongitude(rs.getDouble("longitude"));
				
				wifi.setWorkDate(rs.getDate("work_date"));
				
				result.add(wifi);
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
}
