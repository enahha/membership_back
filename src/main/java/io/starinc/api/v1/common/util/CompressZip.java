package io.starinc.api.v1.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressZip {
	
	/**
	 * 압축 진행 == GOGO ==!
	 * @param path
	 * @param outputPath
	 * @param outputFileName
	 * @param type
	 * @return 파일 압축 성공 여부
	 * @throws Exception
	 */
	public static boolean compress(String path, String outputPath, String outputFileName, String type) throws Exception {
		
		System.out.println(" compress() - path : " + path);  //압축할 폴더 경로
		System.out.println(" compress() - outputPath : " + outputPath);  //압축파일을 저장할 경로
		System.out.println(" compress() - outputFileName : " + outputFileName);
		
		//파일 압축 성공 여부 
		boolean isChk = false; 
		
		File file = new File(path);
		File outputFile = new File(outputPath);
		
		//파일의 .zip이 없는 경우, .zip 을 붙여준다.
//		int pos = outputFileName.lastIndexOf(".") == -1 ? outputFileName.length() : outputFileName.lastIndexOf(".");
//		if (!outputFileName.substring(pos).equalsIgnoreCase(type)) {
//			outputFileName += type;
//		} 
		System.out.println(" compress() - outputFileName : " + outputFileName);
		
		//압축할 해당 디렉토리가 없을경우 디렉토리 생성
		try {
			
			//압축할 폴더 경로 체크 
			if (!file.exists()) { 
				file.mkdirs(); //폴더 생성
				System.out.println(" compress() - file --------- 폴더 생성 완료 ---------");
			}
			
			//압축파일을 저장할 경로 체크 
			if (!outputFile.exists()) { 
				outputFile.mkdirs(); //폴더 생성
				System.out.println(" compress() - outputFile --------- 폴더 생성 완료 ---------");
			}
			
		} catch(Exception e) {
			throw e;
		}
		
		FileOutputStream fos = null;  //출력 스트림
		ZipOutputStream zos = null;   //압축 스트림
		
		try {
			fos = new FileOutputStream(new File(outputPath + outputFileName));
			zos = new ZipOutputStream(fos);
			
			//디렉토리 검색를 통한 하위 파일과 폴더 검색
			if(type.equals("zip")) {
				compressZip(file, file.getPath(), zos);
			} else if(type.equals("tar")) {
				compressTar(file, file.getPath(), zos);
			}
			
			//압축 성공.
			isChk = true;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		
		return isChk;
		
	}
	
	/**
	 * zip 으로 압축하기
	 * @param file
	 * @param root
	 * @param zos
	 * @throws Exception
	 */
	private static void compressZip(File file, String root, ZipOutputStream zos) throws Exception {
		System.out.println(" compressZip() - file : " +file.getName());
		System.out.println(" compressZip() - root : " +root);
		
		//지정된 파일이 디렉토리인지 파일인지 검색
		if (file.isDirectory()) {
			
			//디렉토리일 경우 재탐색(재귀)
			File[] files = file.listFiles();
			for(int i=0; i<files.length; i++) {
				System.out.println(" compressZip() - file = > " + files[i]);
				compressZip(files[i], root, zos);
			}
			
		} else {
			
			// json 파일만 포함
			if (file.getName().indexOf(".json") < 0) {
				return;
			}
			
			//파일일 경우 압축 진행
			FileInputStream fis = null;
			
			try { 
				String zipName = file.getPath().replace(root + "\\", "");  //파일경로를 포함하여 압축
				// String zipName = file.getName();  //해당 파일만 압축
				System.out.println(" compressZip() - zipName : " +zipName);
				
				fis = new FileInputStream(file);  //파일을 읽어드림
				ZipEntry zipentry = new ZipEntry(zipName);  //Zip엔트리 생성(한글 깨짐 버그)
				zos.putNextEntry(zipentry);  //스트림에 밀어넣기(자동 오픈)
				
				int length = (int) file.length();
				byte[] buffer = new byte[length];
				
				fis.read(buffer, 0, length);  //스트림 읽어드리기
				zos.write(buffer, 0, length);  //스트림 작성
				zos.closeEntry();  // 스트림 닫기
				
			} catch (Throwable e) {
				throw e;
				
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						throw e;
					} 
				}
			}
		}
	}
	
	
	/**
	 * tar 로 압축하기
	 * @param file
	 * @param root
	 * @param zos
	 * @throws Exception
	 */
	private static void compressTar(File file, String root, ZipOutputStream zos) throws Exception {
		
		//지정된 파일이 디렉토리인지 파일인지 검색
		if (file.isDirectory()) {
			
			//디렉토리일 경우 재탐색(재귀) 
			File[] files = file.listFiles();
			for(int i=0; i<files.length; i++) {
				System.out.println(" compressTar() - file = > " + files[i]);
				compressTar(files[i], root, zos);
			}
			
		} else {
			//파일일 경우 압축 진행
			
			// json 파일만 포함
			if (file.getName().indexOf(".json") < 0) {
				return;
			}
			
			try {
				FileInputStream fis = null;
				
				try {
					String zipName = file.getPath().replace(root + "\\", "");  //파일경로를 포함하여 압축
					// String zipName = file.getName();  //해당 파일만 압축
					
					fis = new FileInputStream(file);  //파일을 읽어드림
					ZipEntry zipentry = new ZipEntry(zipName);  //Zip엔트리 생성(한글 깨짐 버그)
					zos.putNextEntry(zipentry);  //스트림에 밀어넣기(자동 오픈)
					
					int length = (int) file.length();
					byte[] buffer = new byte[length];
					
					fis.read(buffer, 0, length);  //스트림 읽어드리기
					zos.write(buffer, 0, length);  // 스트림 작성
					zos.closeEntry();  // 스트림 닫기
					
				} catch (Throwable e) {
					throw e;
				} finally {
					if (fis != null) fis.close();
				}
				
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
