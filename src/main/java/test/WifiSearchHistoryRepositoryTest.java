package test;

import java.text.SimpleDateFormat;
import java.util.List;

import domain.WifiSearchHistory;
import repository.WifiSearchHistoryRepository;

public class WifiSearchHistoryRepositoryTest {
	public static void main(String[] args) {
		// insert test
		WifiSearchHistory wifiSearchHistory = new WifiSearchHistory();
		wifiSearchHistory.setLatitude(0.0);
		wifiSearchHistory.setLongitude(0.0);
		
		WifiSearchHistoryRepository wifiSearchHistoryRepository = new WifiSearchHistoryRepository();
		boolean result = wifiSearchHistoryRepository.insert(wifiSearchHistory);
		
		System.out.println(result);
		
		
		// readAll test
		List<WifiSearchHistory> wifiSearchHistoryList = wifiSearchHistoryRepository.getAll();
		
		for (WifiSearchHistory history : wifiSearchHistoryList) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			System.out.println("id : " + history.getId());
			System.out.println("latitude : " + history.getLatitude());
			System.out.println("longitude : " + history.getLongitude());
			System.out.println("lookup date : " + simpleDateFormat.format(history.getLookupDate()));
		}
		
		
		// delete test
		wifiSearchHistory.setId(2);
		result = wifiSearchHistoryRepository.delete(wifiSearchHistory);
		System.out.println(result);
	}
}
