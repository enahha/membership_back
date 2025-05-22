package io.starinc.api.v1.common.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.starinc.api.v1.common.mapper.FileMapper;
import io.starinc.api.v1.common.util.FileUtil;
import io.starinc.api.v1.common.vo.FileVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private FileMapper fileMapper;
	
//	@Autowired
//	private FileMstMapper fileMstMapper;

	@Value("${file.server-ip}")
	private String fileServerIp;
	
	@Value("${file.server-ip-local}")
	private String fileServerIpLocal;
	
	@Value("${file.upload-path}")
	private String fileUploadPath;
	
	@Value("${file.upload-path-local}")
	private String fileUploadPathLocal;
	
	@Value("${file.uploaded-path}")
	private String fileUploadedPath;
	
	@Value("${file.upload-path-nft}")
	private String fileUploadPathNft;
	
	@Value("${file.upload-path-nft-local}")
	private String fileUploadPathNftLocal;
	
	@Value("${file.uploaded-path-nft}")
	private String fileUploadedPathNft;
	
	@Value("${file.upload-path-solana-collection-json}")
	private String fileUploadPathSolanaCollectionJson;
	
	@Value("${file.upload-path-solana-collection-json-local}")
	private String fileUploadPathSolanaCollectionJsonLocal;
	
	@Value("${file.uploaded-path-solana-collection-json}")
	private String fileUploadedPathSolanaCollectionJson;
	
	
	/**
	 * 이미지를 업로드 한다.
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/common/uploadImage")
	public String uploadImage(
			@RequestPart(value = "file") final MultipartFile file
			, HttpServletRequest request
			, HttpServletResponse response
			, HttpSession session
			, Model model) throws Exception {
		logger.debug("####### uploadImage called!");
		logger.debug("####### file.getOriginalFilename(): " + file.getOriginalFilename());
		logger.debug("####### file.getSize(): " + file.getSize());
		logger.debug("####### file.getContentType(): " + file.getContentType());
		
		int seqFileMst = 0;
		String uid = "SYSTEM"; // 세션 uid
		String fileUrl = this.saveFile(file, seqFileMst, uid);
		
		return fileUrl; // 파일 URL
	}
	
	/**
	 * 파일을 저장한다.
	 * 
	 * @param file
	 * @param seqFileMst
	 * @param uid
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private String saveFile(MultipartFile file, int seqFileMst, String uid) throws IOException, Exception {
//		if (seqFileMst == null || "".equals(seqFileMst)) {
//			seqFileMst = "0";
//		}
		// 1. 파일 저장
		String sourceFileName = file.getOriginalFilename();
		String sourceFileNameExtension = FileUtil.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		
		String fileUploadRealPath = ""; // 서버상에 저장되는 패스
		String folderName = FileUtil.getYYYYMMDDFolderName();
		
		do {
			destinationFileName = FileUtil.getNewFileName() + "." + sourceFileNameExtension;
			fileUploadRealPath = this.fileUploadPath + "/" + folderName + "/" + destinationFileName;
			destinationFile = new File(fileUploadRealPath);
		} while (destinationFile.exists());
		destinationFile.getParentFile().mkdirs();
		
		// 운영 패스가 예외처리 될 경우 로컬 패스로 실행 - 로컬 테스트용 - 상수값 계속 수정하기 귀찮아서..
		boolean localFlag = false;
		try {
			file.transferTo(destinationFile);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.info("■■■■■■■ file.transferTo(destinationFile) failed. Set to local path....");
			
			// 로컬 패스로 파일 저장
			fileUploadRealPath = this.fileUploadPathLocal + "/" + folderName + "/" + destinationFileName;
			destinationFile = new File(fileUploadRealPath);
			destinationFile.getParentFile().mkdirs();
			file.transferTo(destinationFile);
			
			localFlag = true;
		}
		
		// 2. DB에 등록
		// String fileUrl = Constant.SERVER_DOMAIN + Constant.UPLOADED_FILE_PATH + "/" + destinationFileName;
		// InetAddress inet = InetAddress.getLocalHost();
		// String serverIp = Constant.SERVER_DOMAIN;
		// serverIp = inet.getHostAddress(); // 테스트용
		String fileUrl = "http://" + this.fileServerIp + this.fileUploadedPath + "/" + folderName + "/" + destinationFileName;
		if (localFlag) {
			fileUrl = "http://" + this.fileServerIpLocal + this.fileUploadedPath + "/" + folderName + "/" + destinationFileName;
		}
		
		FileVo fileVo = new FileVo();
		fileVo.setName(destinationFileName);
		fileVo.setName_original(sourceFileName);
		fileVo.setDir_path(fileUploadRealPath);
		fileVo.setUrl_path(fileUrl);
		fileVo.setSize(String.valueOf(file.getSize()));
		fileVo.setExtension(sourceFileNameExtension);
		fileVo.setContent_type(file.getContentType());
		fileVo.setUid(uid);
		fileVo.setSeq_file_mst(seqFileMst);
		this.fileMapper.insertFile(fileVo);
		logger.debug("### uploaded file SEQ: " + fileVo.getSeq());
		return fileUrl;
	}
	
	/**
	 * NFT 파일을 저장한다.
	 * image, json : folderName 구분
	 * 
	 * @param file
	 * @param folderName
	 * @param newFileName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String saveNftFile(MultipartFile file, String folderName, String newFileName) throws IOException, Exception {
		// 1. 파일 저장
		String sourceFileName = file.getOriginalFilename();
		String sourceFileNameExtension = FileUtil.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		
		String fileUploadRealPath = ""; // 서버상에 저장되는 패스
		
		// do {
		destinationFileName = newFileName + "." + sourceFileNameExtension; // newFileName = 1, 2, 3 순으로 호출됨.
		fileUploadRealPath = this.fileUploadPathNft + "/" + folderName + "/" + destinationFileName;
		destinationFile = new File(fileUploadRealPath);
		// } while (destinationFile.exists());
		destinationFile.getParentFile().mkdirs();
		
		// 운영 패스가 예외처리 될 경우 로컬 패스로 실행 - 로컬 테스트용 - 상수값 계속 수정하기 귀찮아서..
		boolean localFlag = false;
		try {
			file.transferTo(destinationFile);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.info("■■■■■■■ NFT file.transferTo(destinationFile) failed. Set to local path....");
			
			// 로컬 패스로 파일 저장
			fileUploadRealPath = this.fileUploadPathNftLocal + "/" + folderName + "/" + destinationFileName;
			destinationFile = new File(fileUploadRealPath);
			destinationFile.getParentFile().mkdirs();
			file.transferTo(destinationFile);
			
			localFlag = true;
		}
		
		String fileUrl = "http://" + this.fileServerIp + this.fileUploadedPathNft + "/" + folderName + "/" + destinationFileName;
		if (localFlag) {
			fileUrl = "http://" + this.fileServerIpLocal + this.fileUploadedPathNft + "/" + folderName + "/" + destinationFileName;
		}
		
		return fileUrl;
	}
	
	/**
	 * Solana Collection Metadata JSON 파일을 저장한다.
	 * 
	 * @param projectId
	 * @param jsonObject
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String saveSolanaCollectionJsonFile(String projectId, JSONObject jsonObject) throws IOException, Exception {
//		if (seqFileMst == null || "".equals(seqFileMst)) {
//			seqFileMst = "0";
//		}
		
		// 1. 파일 저장
		String sourceFileNameExtension = "json";
		String fileName = projectId + "_collection." + sourceFileNameExtension; // ex) bravekongz_collection.json
		
		// Define the directory and file path
		String filePath = this.fileUploadPathSolanaCollectionJson + "/" + projectId + "/" + fileName;
		
		// 운영 패스가 예외처리 될 경우 로컬 패스로 실행 - 로컬 테스트용 - 상수값 계속 수정하기 귀찮아서..
		boolean localFlag = false;
		
		// Ensure the directory exists
		Path path = Paths.get(this.fileUploadPathSolanaCollectionJson + "/" + projectId);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Directory created: " + this.fileUploadPathSolanaCollectionJson + "/" + projectId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Write the JSON object to the specified file
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write(jsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			System.out.println("JSON file created: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			
//			// 로컬 환경으로 설정 
//			filePath = this.fileUploadPathSolanaCollectionJsonLocal + "/" + projectId + "/"  + fileName;
//			path = Paths.get(this.fileUploadPathSolanaCollectionJsonLocal);
//			
//			if (!Files.exists(path)) {
//				try {
//					Files.createDirectories(path);
//					System.out.println("Local Directory created: " + this.fileUploadPathSolanaCollectionJsonLocal);
//				} catch (IOException e2) {
//					e2.printStackTrace();
//				}
//			}
//			localFlag = true;
//			
//			FileWriter fileWriter = new FileWriter(filePath);
//			fileWriter.write(jsonObject.toJSONString());
//			fileWriter.flush();
//			fileWriter.close();
		}
		
		// file URL 작성
		String fileUrl = "https://" + this.fileServerIp + this.fileUploadedPathSolanaCollectionJson + "/" + projectId + "/" + fileName;
		if (localFlag) {
			fileUrl = "http://" + this.fileServerIpLocal + this.fileUploadedPathSolanaCollectionJson + "/" + projectId + "/" + fileName;
		}
		
		
		// file 테이블에 로그 등록
		FileVo fileVo = new FileVo();
		fileVo.setName(fileName);
		fileVo.setName_original(fileName);
		fileVo.setDir_path(filePath);
		fileVo.setUrl_path(fileUrl);
		// fileVo.setSize(String.valueOf(file.getSize()));
		fileVo.setExtension(sourceFileNameExtension);
		// fileVo.setContent_type(file.getContentType());
		fileVo.setUid("SYSTEM");
		fileVo.setSeq_file_mst(0);
		this.fileMapper.insertFile(fileVo);
		logger.debug("### uploaded file SEQ: " + fileVo.getSeq());
		
		return fileUrl;
	}
}
