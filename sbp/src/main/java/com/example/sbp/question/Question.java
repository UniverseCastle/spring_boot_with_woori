package com.example.sbp.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.sbp.answer.Answer;
import com.example.sbp.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
	
	@ManyToMany						// 추천
	Set<SiteUser> voter;			// 중복 안되게 하기 위해 Set 사용
	
	@ManyToOne
	private SiteUser author;		// 5.30)
	
	private LocalDateTime modifyDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	// 별도로 고유번호를 정해줌. 1씩 자동 증가함
	private Integer id;

	@Column(length=200)						// 200자 length : 열의 '길이'
	private String subject;

	@Column(columnDefinition="TEXT")		// 문자열 데이터 저장. 글자 제한이 없음
	private String content;
   
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question", cascade=CascadeType.REMOVE)
	private List<Answer> answerList;		// 답변 리스트
	
}