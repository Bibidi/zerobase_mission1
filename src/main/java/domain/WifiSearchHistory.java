package domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiSearchHistory {
	private Integer id;
	private Double latitude;
	private Double longitude;
	private Date lookupDate;
}
