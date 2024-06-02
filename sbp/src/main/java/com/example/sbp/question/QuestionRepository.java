package com.example.sbp.question;

import java.util.List;

import org.springframework.data.domain.Page;			// 페이지를 위한 클래스
import org.springframework.data.domain.Pageable;		// 페이징 처리를 하는 인터페이스
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
//	Junit 테스트
	Question findBySubject(String subject);
	
//	subject 와 content 두 개의 엔티티 속성(컬럼) 조회하기 위한 메서드
	Question findBySubjectAndContent(String subject, String content);
	
//	like 포함하는게 하나가 아닐수도있으므로 List 로 받음
	List<Question> findBySubjectLike(String subject);
	
	Page<Question> findAll(Pageable pageable);
//	+PageRequest : 현재 페이지와 한 페이지에 보여줄 게시물 수 등을 설정하여 페이징을 요청하는 클래스
	
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);		// 5/31) 추가
}