package domain;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wifi {
	@SerializedName("X_SWIFI_MGR_NO")
	private String managementNumber;
	
	@SerializedName("X_SWIFI_WRDOFC")
	private String district;
	
	@SerializedName("X_SWIFI_MAIN_NM")
	private String name;
	
	@SerializedName("X_SWIFI_ADRES1")
	private String address1;
	
	@SerializedName("X_SWIFI_ADRES2")
	private String address2;
	
	@SerializedName("X_SWIFI_INSTL_FLOOR")
	private String installedFloor;
	
	@SerializedName("X_SWIFI_INSTL_TY")
	private String installationType;
	
	@SerializedName("X_SWIFI_INSTL_MBY")
	private String installationAgency;
	
	@SerializedName("X_SWIFI_SVC_SE")
	private String serviceClassification;
	
	@SerializedName("X_SWIFI_CMCWR")
	private String meshType;
	
	@SerializedName("X_SWIFI_CNSTC_YEAR")
	private String installationYear;
	
	@SerializedName("X_SWIFI_INOUT_DOOR")
	private String inOutDoor;
	
	@SerializedName("X_SWIFI_REMARS3")
	private String connectionEnvironment;
	
	@SerializedName("LAT")
	private Double latitude;
	
	@SerializedName("LNT")
	private Double longitude;
	
	private Date workDate;
	
	@SerializedName("WORK_DTTM")
	private String WorkDateStr;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Object[] varArr = new Object[] {
				managementNumber, district, name, address1, address2, 
				installedFloor, installationType, installationAgency, serviceClassification, meshType,
				installationYear, inOutDoor, connectionEnvironment, latitude, longitude,
				workDate
				};
		String[] strArr = new String[] {
				"managementNumber", "district", "name", "address1", "address2", 
				"installedFloor", "installationType", "installationAgency", "serviceClassification", "meshType",
				"installationYear", "inOutDoor", "connectionEnvironment", "latitude", "longitude",
				"workDate"
				};
		for (int i = 0; i < varArr.length; i++) {
			appendMsg(sb, strArr[i], varArr[i]);
		}
		return sb.toString();
	}
	
	private void appendMsg(StringBuilder sb, String msg, Object obj) {
		if (obj == null) return;
		if (obj instanceof String && "".equals(obj)) return;
		sb.append(msg).append(" : ").append(obj).append("\n");
	}
}
