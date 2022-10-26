package service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import domain.PublicWifiAccessObject;
import domain.Wifi;
import domain.WifiSearchHistory;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import repository.WifiInfoRepository;
import repository.WifiSearchHistoryRepository;
import util.MathUtil;

public class WifiInfoService {
	private static String authKey = "4c6f654b426b697338395772496f6c";
	private static String urlHomePart = "http://openapi.seoul.go.kr:8088/";
	private static String urlRequestPart = "/json/TbPublicWifiInfo/";
	
	@Getter 
	@Setter
	private class PublicWifiAccessObjectWrapper {
		@SerializedName("TbPublicWifiInfo")
		private PublicWifiAccessObject publicWifiAccessObject;
	}
	
	private String run(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public int getWifiCountFromPublicData() {
		String startToEnd = "1/1";
		String url = urlHomePart + authKey + urlRequestPart + startToEnd;
		String json = null;
		PublicWifiAccessObjectWrapper publicWifiAccessObjectWrapper = null;
		int count = -1;
		
		try {
			json = run(url);
			Gson gson = new Gson();
			publicWifiAccessObjectWrapper = gson.fromJson(json, PublicWifiAccessObjectWrapper.class);
			count = publicWifiAccessObjectWrapper.getPublicWifiAccessObject().getTotalWifiCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public List<Wifi> getWifiListFromPublicData(int start, int end) {
		String url = urlHomePart + authKey + urlRequestPart + start + "/" + end;
		String json = null;
		PublicWifiAccessObjectWrapper publicWifiAccessObjectWrapper = null;
		List<Wifi> wifis = null;
		
		try {
			json = run(url);
			Gson gson = new Gson();
			publicWifiAccessObjectWrapper = gson.fromJson(json, PublicWifiAccessObjectWrapper.class);
			wifis = publicWifiAccessObjectWrapper.getPublicWifiAccessObject().getWifis();
			
			for (Wifi wifi : wifis) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					wifi.setWorkDate(simpleDateFormat.parse(wifi.getWorkDateStr()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wifis;
	}
	
	public int saveAllWifiListFromPublicData() {
		WifiInfoRepository wifiInfoRepository = new WifiInfoRepository();
		int totalCount = getWifiCountFromPublicData();
		
		for (int i = 0; i < totalCount; i += 1000) {
			List<Wifi> wifiList = getWifiListFromPublicData(i + 1, i + 1000);
			for (Wifi wifi : wifiList) {
				wifiInfoRepository.insert(wifi);
			}
		}
		
		return totalCount;
	}
	
	public List<Wifi> getNearbyWifiList(double latitude, double longitude) {
		WifiInfoRepository  wifiInfoRepository = new WifiInfoRepository();
		List<Wifi> wifiList = wifiInfoRepository.readAll();
		List<Wifi> result = new ArrayList<>();
		
		Collections.sort(wifiList, new Comparator<Wifi>() {
            @Override
            public int compare(Wifi w1, Wifi w2) {
            	double dist1 = MathUtil.calculateDistance(latitude, longitude, w1.getLatitude(), w1.getLongitude());
            	double dist2 = MathUtil.calculateDistance(latitude, longitude, w2.getLatitude(), w2.getLongitude());
            	
                if (dist1 < dist2) return -1;
                else if (dist1 > dist2) return 1;
                else return 0;
            }
        });
		
		for (int i = 0; i < 20; i++) {
			result.add(wifiList.get(i));
		}
		
		WifiSearchHistoryRepository wifiSearchHistoryRepository = new WifiSearchHistoryRepository();
		WifiSearchHistory wifiSearchHistory = new WifiSearchHistory();
		wifiSearchHistory.setLatitude(latitude);
		wifiSearchHistory.setLongitude(longitude);
		wifiSearchHistoryRepository.insert(wifiSearchHistory);
		
		return result;
	}
	
	public List<WifiSearchHistory> getAllWifiSearchHistory() {
		WifiSearchHistoryRepository wifiSearchHistoryRepository = new WifiSearchHistoryRepository();
		return wifiSearchHistoryRepository.getAll();
	}
}
