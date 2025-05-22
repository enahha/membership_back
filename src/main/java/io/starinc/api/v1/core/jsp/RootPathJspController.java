package io.starinc.api.v1.core.jsp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.util.StringUtil;
import io.starinc.api.v1.project.mapper.ProjectMapper;
import io.starinc.api.v1.project.vo.ProjectVo;

@Transactional
@RequestMapping(value = "/")
@Controller
public class RootPathJspController implements ErrorController {
	
	// private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ProjectMapper projectMapper;
	
//	/**
//	 * 도메인 접속시 URL의 projectId로 페이지 분기
//	 * 
//	 * @param String projectId
//	 * @param l('en' or 'ko')
//	 * @return
//	 * @throws Exception
//	 */
//	@GetMapping("/{projectId}")
//	public String projectId(
//			@PathVariable String projectId
//			, HttpServletRequest request
//			, HttpSession session
//			, Model model
//			) throws Exception {
//		
//		// String requestUri = request.getRequestURI();
//		
//		// System.out.println("requestUri : " + requestUri);
//		System.out.println("projectId : " + projectId);
//		
//		
//		if ("error".equals(projectId) || StringUtil.isEmpty(projectId)) {
//			return null;
//		}
//		
//		
////		String seq = request.getParameter("s"); // seq
////		int intSeq = 0;
////		if (!StringUtil.isEmpty(seq)) {
////			intSeq = Integer.parseInt(seq);
////		}
//		String locale = request.getParameter("l"); // locale en/ko
//		if (StringUtil.isEmpty(locale)) {
//			locale = "ko";
//		}
//		
//		// 프로젝트 정보 조회
//		ProjectVo projectVo = new ProjectVo();
//		projectVo.setOld_project_id(projectId);
//		try {
//			projectVo = this.projectMapper.selectProjectByOldProjectId(projectVo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// og 정보 설정
//		String ogTitle = "";
//		String ogDescription = "";
//		String ogImage = "";
//		// SEO - 검색용 본문 설정
//		String description = "";
//		if (projectVo != null) {
//			if ("ko".equals(locale)) {
//				ogTitle = projectVo.getOld_project_name_ko();
//				ogDescription = projectVo.getDescription_ko();
//			} else {
//				ogTitle = projectVo.getOld_project_name();
//				ogDescription = projectVo.getOld_project_name();
//			}
//			ogImage = projectVo.getOld_project_image();
//			if (StringUtil.isEmpty(ogImage)) {
//				// 메인 이미지가 없으면 디폴트 이미지 설정
//				ogImage = Constant.SERVER_DOMAIN + "/images/og_image.png";
//			}
//			if ("ko".equals(locale)) {
//				description = projectVo.getDescription_ko();
//			} else {
//				description = projectVo.getDescription();
//			}
//		}
//		
//		String projectPath = "migration";
//		
//		String frontPath = Constant.SERVER_DOMAIN + "/#/" + projectPath + "?id=" + projectId + "&l=" + locale;
//		String ogUrl = Constant.SERVER_DOMAIN + "/=" + projectPath + "?id=" + projectId + "&l=" + locale;
//		model.addAttribute("frontPath", frontPath);
//		
//		model.addAttribute("ogUrl", ogUrl);
//		model.addAttribute("ogTitle", ogTitle);
//		model.addAttribute("ogDescription", ogDescription);
//		model.addAttribute("ogImage", ogImage);
//		
//		// SEO - 검색용 본문 설정
//		model.addAttribute("description", description);
//		
//		return "project/info";
//	}
}
