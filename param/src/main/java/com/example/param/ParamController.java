package com.example.param;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/param/*") // http://localhost:8080/param/~
@Log4j2
public class ParamController {

	@GetMapping("form")
	public String form() {
		log.trace("[trace] url-/param/form");
		log.debug("[debug] url-/param/form");
		log.info("[info] url-/param/form");
		log.warn("[warn] url-/param/form");
		log.error("[error] url-/param/form");
		log.fatal("[fatal] url-/param/form");

		return "form";
	}

	@PostMapping("ex01")
	public String ex01(Member member) { // ${member.~} (대문자x, 소문자o)
		log.info("ex01 -- parameter id : " + member.getId());
		log.info("ex01 -- parameter pw : " + member.getPw());
		return "ex01";
	}

	@PostMapping("ex02")
	public String ex02(Model model, @RequestParam("id") String id, @RequestParam("pw") String pw) {
//		@RequestParam - 지정해서 보냄. 한두개 보낼때 유용하게 쓰임
//		request.getParameter("id") 와 같은 역할

		log.info("ex02 -- parameter id : " + id);
		log.info("ex02 -- parameter pw : " + pw);

		model.addAttribute("id", id);
		model.addAttribute("pw", pw);

		return "ex02";
	}

	@PostMapping("ex03")
	public String ex03(Model model, @RequestParam("hobbies") List<String> hobbies,
			@RequestParam("hobbies") String[] arr) {
		log.info("ex03 -- parameter  hobbies" + hobbies);
		log.info("ex03 -- parameter  hobbies" + Arrays.asList(arr));

		model.addAttribute("hobbies", hobbies);
		model.addAttribute("arr", arr);

		return "ex03";
	}

	@PostMapping("ex04")
	public String ex04(Member member, @ModelAttribute("id") String id) {
//		@ModelAttribute - parameter 를 view 까지 전달가능	/ @RequestParam 은 못보냄
//		view 에서 ${id} 사용 가능
//		@RequestParam 과 Model 이 합쳐진 기능

		log.info("ex04 -- parameter id : " + id);

		return "ex04";
	}

	@PostMapping("ex05")
	public String ex05(@RequestParam("id") String id, RedirectAttributes rttr) {
//		RedirectAttributes - redirect 는 페이지 이동만, 이것은 속성들 까지 보내줌
//		일회성 데이터를 전달 - 브라우저에 한 번만 사용된다. (새로고침 하면 id 값이 출력되지 않음)

		log.info("ex05 -- parameter id : " + id);
		rttr.addFlashAttribute("id", id);
		rttr.addFlashAttribute("pw", "1234");

//		redirect 로 이동 시 뷰로 가는것이 아니라, 해당 매핑으로 GET 방식으로 이동한다.
		return "redirect:ex05"; // 경로 아님. @Mapping 의 URI 로 보낸다는 의미
								// GetMapping 의 ex05 로 이동한다.
	}

	@GetMapping("ex05")
	public String ex05() {

		return "ex05";
	}

	@GetMapping("ex06")
	public @ResponseBody String ex06() {
//		@ResponseBody - view 를 거치지 않고 바로 보여줌
//		http://localhost:8080/param/ex06

		return "Hello Param";
	}

	@GetMapping("ex07")
	public @ResponseBody Member ex07() {
//		@ResponseBody 객체도 전달 가능하다.
		Member dto = new Member();
		dto.setId("java");
		dto.setPw("boot");

		return dto;
	}

	@GetMapping("ex08")
	public ResponseEntity<String> ex08() {
//		ResponseEntity - HTTP 의 응답을 표현하는 클래스
//		상태 코드, 헤더 를 포함하고있음		상태코드 200 - 성공 (정상실행)

		String msg = "{\"name\":\"박주완\"}";
//				"{ \" name \"  : \" 박주완 \"  }"
//				"{	" name  "  :  " 박주완  "  }"
//		Json 방식에서는 "" / 이스케이프 방식으로 문자를 표현해야함

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");

		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
/*
	application.propertires 파일에 설정해줘야함.
	
	#upload
	spring.servlet.multipart.max-file-size=100MB
	spring.servlet.multipart.max-request-size=100MB
*/
	@PostMapping("ex09")
	public String ex09(@RequestParam("save") MultipartFile mf, @ModelAttribute("id") String id, Model model) {

		model.addAttribute("contentType", mf.getContentType());
		model.addAttribute("orgName", mf.getOriginalFilename());
		model.addAttribute("fileSize", mf.getSize());

//		파일 업로드 시 MultipartFile mf 객체 사용 -> 업로드 직접 해줘야한다.
//		파일 이름 중복처리 기능이 없다.
//		c 드라이브에 upload 폴더 생성

		File copy = new File("c:/upload/" + mf.getOriginalFilename());
		try {
			mf.transferTo(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ex09";
	}

	@PostMapping("ex10")
	public String ex10(@RequestParam("save") List<MultipartFile> files, @ModelAttribute("id") String id, Model model) {
//		다중 업로드 시 List 로 받아서 처리할 수 있다.
		for (MultipartFile mf : files) {
			File copy = new File("c:/upload/" + mf.getOriginalFilename());
			try {
				if (mf.getContentType().split("/")[0].equals("image")) {
					mf.transferTo(copy);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("fileCount", files.size());

		return "ex10";
	}
	
/*
		@PostMapping("ex10")
	public String ex10(@RequestParam("save") List<MultipartFile> files, @ModelAttribute("id") String id, Model model) {
//		다중 업로드 시 List 로 받아서 처리할 수 있다.
		for (MultipartFile mf : files) {
			String uploadDir = "C:/upload/";
			String orgName = mf.getOriginalFilename();
			if (orgName != null) {
				String fileName = Paths.get(orgName).getFileName().toString();
				File copy = new File(uploadDir + fileName);
				
				try {
					if (mf.getContentType().split("/")[0].equals("image")) {
						mf.transferTo(copy);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		model.addAttribute("fileCount", files.size());
		
		return "ex10";
	}
*/
	@PostMapping("ex11")
	public String ex11(@RequestParam("save") MultipartFile mf, Model model) {
		
//		날짜와 랜덤 메서드를 활용하여 파일이름 중복 방지
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Random random = new Random();
		String rn = Integer.toString(random.nextInt(Integer.MAX_VALUE));
//		.nextInt() - 랜덤값이 int로 나오게 하는 메서드. int 최대 범위 사이의 랜덤감
		
		String ts = df.format(new Date());
//		현재 시간을 df(포맷) 적용
		
		String uploadFileName = ts + rn + mf.getOriginalFilename();
		File copy = new File("c:/upload/" + uploadFileName);
		
		try {
			mf.transferTo(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("orgName", mf.getOriginalFilename());
		model.addAttribute("uploadFileName", uploadFileName);
		
		return "ex11";
	}
	
	@PostMapping("ex12")
	public String ex12(@RequestParam("save") MultipartFile mf, Model model, HttpServletRequest request) {
//		네트워크 UUID 를 활용한 파일 이름 설정
//		UUID(Universally Unique Identifier) 네트워크에서 고유한 식별자를 생성하는 표준 방식
//		ex) 5550e8400-e29d0343djdk-294935kjl
//		파일명에 - 있으면 읽지 못하는 경우가 있으니 처리
		String sysName = UUID.randomUUID().toString().replace("-", "") + mf.getOriginalFilename();
		String uploadPath = new File("").getAbsolutePath() + "\\src\\main\\resources\\static\\upload\\";
//									  ^ 비어있다면 현재 작업중인 폴더를 의미함
		log.info(uploadPath);
		
		File copy = new File(uploadPath + sysName);
		
		try {
			mf.transferTo(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("sysName", sysName);
		return "ex12";
	}
	
	@GetMapping("display")
	public ResponseEntity<Resource> display(@RequestParam("fileName") String fileName) {
		String path = new File("").getAbsolutePath() + "\\src\\main\\resources\\static\\upload\\";
		
//		내 현재 파일을 읽어오기
		Resource re = new FileSystemResource(path + fileName);
		if (!re.exists()) {		// 존재하지 않는다면
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
//		응답 이미지 정보를 설정하기 위한 객체
		HttpHeaders head = new HttpHeaders();
		Path filePath = null;
		
//		파일의 MIME 타입을 결정하기 위해 Path 클래스로 변환
//		웹에서 사용하는 모든 파일 타입을 통틀어서 MIME 타입 이라고 한다.
		try {
			filePath = Paths.get(path + fileName);
			head.add("Content-type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(re, head, HttpStatus.OK);
	}
}
/*
 * 로그 레벨 (TRACE < DEBUG < INFO(기본) < WARN < ERROR < FATAL)
 * 
 * TRACE - 일반적으로 사용하지 않음 / 모든것을 기록 DEBUG - 일반적으로 사용하지 않음 / 문제를 진단할 때 (디버깅) INFO
 * - 기본값 WARN - 경고 / 어플리케이션이 동작하는데에 문제는 없음 ERROR - 오류 / 해결해야 함 FATAL - 치명적인 오류 /
 * 문제가 큼
 */