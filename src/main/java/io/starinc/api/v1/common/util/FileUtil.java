/**
 * 
 */
package io.starinc.api.v1.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ahn
 *
 */
public class FileUtil {
	
	// private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 파일 확장자명 추출
	 * @param fileName
	 * @return extension
	 */
	public static String getExtension(String fileName) {
		String[] nameArray = fileName.split("\\.");
		System.out.println(nameArray);
		int nameLength = nameArray.length;
		String extension = nameArray[nameLength-1];
		return extension;
	}
	
	/**
	 * 파일명 생성
	 */
	public static String getNewFileName() {
		long time = System.currentTimeMillis(); 
		SimpleDateFormat currentTime = new SimpleDateFormat("yyyyMMdd_HHmm_ss_SSS_");
		String str = currentTime.format(new Date(time));
		str += UUID.randomUUID().toString().substring(0, 6);
		return str;
	}
	
	/**
	 * 유니크 명칭 생성
	 */
	public static String getUniqueName(int count) {
		String uniquefolderName = UUID.randomUUID().toString().substring(0, count);
		return uniquefolderName;
	}
	
	/**
	 * YYYYMMDD 폴더명 생성
	 */
	public static String getYYYYMMDDFolderName() {
		long time = System.currentTimeMillis(); 
		SimpleDateFormat currentDate = new SimpleDateFormat("yyyyMMdd");
		String str = currentDate.format(new Date(time));
		return str;
	}
	
	/**
	 * 파일을 복사하는 메소드
	 * 
	 * @param inFileName
	 * @param outFileName
	 */
	public static void fileCopy(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);
			
			int data = 0;
			while ((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
