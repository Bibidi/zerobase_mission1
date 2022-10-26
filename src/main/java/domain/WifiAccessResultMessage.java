package domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiAccessResultMessage {
	@SerializedName("CODE")
	private String code;
	
	@SerializedName("MESSAGE")
	private String message;
}
