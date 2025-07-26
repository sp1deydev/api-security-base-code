package com.thientdk.be_auth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.Math.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoordinateUtils {
	public static Double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double dist = 0;

		if (lat1 == lat2 && lon1 == lon2) {
			return dist;
		}

		double radLat1 = toRadians(lat1);
		double radLat2 = toRadians(lat2);
		double theta = lon1 - lon2;
		double radTheta = toRadians(theta);

		dist = sin(radLat1) * sin(radLat2) + cos(radLat1) * cos(radLat2) * cos(radTheta);

		if (dist > 1) {
			dist = 1;
		}

		dist = acos(dist);
		dist = toDegrees(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return dist;
	}
}
