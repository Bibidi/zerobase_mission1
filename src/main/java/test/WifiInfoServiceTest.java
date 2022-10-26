package test;

import java.util.List;

import domain.Wifi;
import service.WifiInfoService;
import util.MathUtil;

public class WifiInfoServiceTest {
	public static void main(String[] agrs) {
		
		// 총 와이파이 개수 가져오기 테스트
		WifiInfoService wifiInfoService = new WifiInfoService();
		int count = wifiInfoService.getWifiCountFromPublicData();
		
		System.out.println("total count : " + count + "\n");
		
		// 와이파이 정보 가져오기 테스트
		List<Wifi> wifis = wifiInfoService.getWifiListFromPublicData(1, 1);
		
		for (Wifi wifi : wifis) {
			System.out.println(wifi);
		}
		
		// 외부에서 모든 와이파이 정보 가져와서 저장하기
		int totalCount = wifiInfoService.saveAllWifiListFromPublicData();
		
		System.out.println(totalCount);
		
		
		// 근처 와이파이 정보 받아오기
		double curLat = 0.0;
		double curLon = 0.0;
		wifis = wifiInfoService.getNearbyWifiList(curLat, curLon);
		
		System.out.println(wifis.size() + "\n");
		for (Wifi wifi : wifis) {
			double dist = MathUtil.calculateDistance(curLat, curLon, wifi.getLatitude(), wifi.getLongitude());
			System.out.println(dist);
			System.out.println(wifi);
		}
	}
}
