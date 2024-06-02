package com.example.sbp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sbp.question.QuestionService;


@SpringBootTest		// 테스트 클래스라는걸 알려줌
class SbpApplicationTests {
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void testJpa() {
		for (int i=1; i<=300; i++) {
			String subject = String.format("테스트 데이터 입니다:[%03d]", i);
			String content = "내용";
			this.questionService.create(subject, content, null);
			// 5.30) create 메서드 siteUser 매개변수 추가 에 따른 null 추가
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Autowired		// 자동 연결 / 의존성 주입(DI - Dependency Injection) / 스프링이 객체를 대신 생성하여 주입하는 방식
	private QuestionRepository questionRepository;
	
	@Transactional	// 임포트 -> 스프링 프레임워크
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
//		지연(Lazy) 방식 <-> 즉시(Eager) 방식
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네, 자동으로 생성됩니다!", answerList.get(0).getContent());
	}
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}
	
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		Answer a = new Answer();
		a.setContent("네, 자동으로 생성됩니다!");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	
	@Test
	void testJpa() {			// 데이터 삭제
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());		// 삭제 후 레코드 개수 1개됨 -> 1로 비교
	}
	
	@Test
	void testJpa() {			// 수정
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
	
	@Test
	void testJpa() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbp%");
		Question q = qList.get(0);
		assertEquals("sbp가 무엇인가요?", q.getSubject());
//		aaa% : 시작 / %aaa : 끝 / %aaa% 사이
//		조건절 LIKE를 사용하여 데이터에 '%'가 포함된 데이터를 찾으려고 한다.
//		하지만 LIKE문에서 '%'는 실제문자 '%'가 아닌, 조건검색 기능에 해당하는 문자로 쓰이게된다.
	}
	
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubjectAndContent("sbp가 무엇인가요?", "sbp에 대해서 알고싶습니다.");
		assertEquals(1, q.getId());
	}
	
	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubject("sbp가 무엇인가요?");	// 리포지토리 에 선언한 메서드를 사용할 수 있다. JPA의 기능
		assertEquals(1, q.getId());
	}
//	findBy + 엔티티의 속성명 : 메서드 선언
	
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(1);	// 리턴타입 Optional
		if (oq.isPresent()) {											// Optional : null 값이면 유연하게 처리하기 위해 사용
			Question q = oq.get();
			assertEquals("sbp가 무엇인가요?", q.getSubject());
		}
	}
	
	@Test
	void testJpa() {
		List<Question> all = this.questionRepository.findAll();		// 모든 데이터 조회하는 메서드
		assertEquals(2, all.size());								// 전체 개수, 리스트 사이즈 같아야함
		
		Question q = all.get(0);
		assertEquals("sbp가 무엇인가요?", q.getSubject());
//		assertEquals(기댓값, 실제값);
//		테스트에서 예상한 결과와 실제 데이터가 동일한지 확인하는 목적
//		JPA 또는 데이터베이스에서 데이터를 올바르게 가져오는지 확인
	}
	@Test
	void testJpa() {							// 첫 번째 행. 첫 번째 레코드
		Question q1 = new Question();
		q1.setSubject("sbp가 무엇인가요?");
		q1.setContent("sbp에 대해서 알고싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();			// 두 번째 행. 두 번째 레코드
		q2.setSubject("스프링부트 모델 질문입니다!");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
*/
/*
//	@Test 		// 메서드는 여러개 생성 가능하지만, 어노테이션은 1개만 사용가능하다.
	void testJpa2() {
		
	}
}
*/
}