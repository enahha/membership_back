/**
 * 
 */
package io.starinc.api.v1.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ahn
 *
 */
public class DateUtil {
	
	public static String getYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd");
		Date time = new Date();
		return format.format(time);
	}
	
	public static String getYYYYMMDDHHMM() {
		SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmm");
		Date time = new Date();
		return format.format(time);
	}
	
	public static String getYYYYMMDDHHMMSS() {
		SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
		Date time = new Date();
		return format.format(time);
	}
}
