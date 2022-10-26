package domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicWifiAccessObject {
	@SerializedName("list_total_count")
	private Integer totalWifiCount;
	
	@SerializedName("RESULT")
	private WifiAccessResultMessage resultMessage;
	
	@SerializedName("row")
	private List<Wifi> wifis;
}
