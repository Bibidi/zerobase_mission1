package test;

import java.util.List;

import domain.Wifi;
import repository.WifiInfoRepository;

public class WifiInfoRepositoryTest {
	public static void main(String[] args) {
		
		// insert test
		WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();
		Wifi wifi = new Wifi();
		wifi.setManagementNumber("test1");
		wifi.setLatitude(1.0);
		wifi.setLongitude(1.0);
		
		boolean result = wifiInfoRepository.insert(wifi);
		
		System.out.println(result);
		
		
		// delete test
		result = wifiInfoRepository.delete(wifi);
		
		System.out.println(result);
		
		
		// read all
		List<Wifi> wifiList = wifiInfoRepository.readAll();
		
		System.out.println(wifiList.size());
		for (int i = 0; i < 5; i++) {
			System.out.println(wifiList.get(i));		}
	}
}
