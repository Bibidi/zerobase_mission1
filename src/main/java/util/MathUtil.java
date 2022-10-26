package util;

public class MathUtil {
	public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double ydiff = (lat1 - lat2) * 111;
		double xdiff = (lon1 - lon2) * 111;
		double dist = Math.sqrt(ydiff * ydiff + xdiff * xdiff);
		return dist;
	}
}
