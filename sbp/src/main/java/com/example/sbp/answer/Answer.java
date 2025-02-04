package com.example.sbp.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.sbp.question.Question;
import com.example.sbp.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	
	@ManyToMany
	Set<SiteUser> voter;		// 추천
	
	@ManyToOne
	private SiteUser author;	// 05.30) 글쓴이
	
	private LocalDateTime modifyDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition="TEXT")
	private String content;
	
	private LocalDateTime createDate;		// 카멜표기법 -> DB 에서는 스네이크 표기법으로 바뀜 create_date
	
//	질문 엔티티를 참조하기 위해 question 속성을 추가함
	@ManyToOne
	private Question question;
}
/*
	여러 답변이(Many) 하나의(One) 질문에 달림
		N	:	1
	@ManyToOne
	-----------------------------------
	질문(One)	답변(Many)
		1	:	N
	@OnetoMany
	
*/