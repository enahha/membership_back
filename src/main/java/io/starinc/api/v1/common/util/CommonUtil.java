/**
 * 
 */
package io.starinc.api.v1.common.util;

/**
 * @author ahn
 *
 */
public class CommonUtil {
	
	/**
	 * 페이징 시작행 반환
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return startRow
	 */
	public static int pagingStartRow(int pageNum, int pageSize) {
		return (pageNum - 1) * pageSize;
	}
	
	/**
	 * 페이징 종료행 반환
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return endRow
	 */
	public static int pagingEndRow(int pageNum, int pageSize) {
		return pageNum * pageSize;
	}

}
