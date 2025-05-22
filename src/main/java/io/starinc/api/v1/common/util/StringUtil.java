/**
 * 
 */
package io.starinc.api.v1.common.util;

/**
 * @author ahn
 *
 */
public class StringUtil {
	
	/**
	 * 빈값 체크
	 * 
	 * @param String str
	 * @return boolean true(빈값) false(빈값 아님)
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 빈값 체크
	 * 
	 * @param String str
	 * @return boolean true(빈값 아님) false(빈값)
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null || "".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

}
