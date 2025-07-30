package com.mysite.sbb;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.Answer.AnswerRepository;
import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa1_1() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	void testJpa1_2(){
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa1_3(){
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}
	@Test
	void TestJpa1_4(){
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}
	@Test
	void TestJpa1_5(){
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void TestJpa1_6(){
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
	@Test
	void TestJpa1_7(){
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);

		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}
	@Test
	void TestJpa2_1(){
		Optional<Question> oq = this.questionRepository.findById(2);

		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	void TestJpa2_2(){
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}
	@Transactional
	@Rollback(false)
	//@Transactional는 기본적으로 코드가 실행된후에 데이터를 롤백시킨다. @RollBack(true)
	//만약 롤백을 원하지 않는다면, @RollBack(false) 로 바꿔주면 데이터가 롤백되지 않는다.
	@Test
	void TestJpa2_3(){
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다", answerList.get(0).getContent());
	}

}

