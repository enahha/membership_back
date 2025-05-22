/**
 * 
 */
package io.starinc.api.v1.common.util;

/**
 * @author ahn
 *
 */
public class NumberUtil {
	
	/**
	 * 지정된 범위의 정수 1개를 램덤하게 반환하는 메서드
	 * 
	 * @param n1 하한값
	 * @param n2 상한값
	 * @return
	 */
	public static int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
}
