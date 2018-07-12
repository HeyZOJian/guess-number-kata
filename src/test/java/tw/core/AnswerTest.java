package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.exception.OutOfGuessCountException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jxzhong on 2017/9/23.
 */
public class AnswerTest {
	private Answer actualAnswer;
	private Game game;

	@BeforeEach
	public void setUp() throws AnswerFormatIncorrectException {
		actualAnswer = Answer.createAnswer("1 2 3 4");
		AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
		when(answerGenerator.generate()).thenReturn(actualAnswer);
		game = new Game(answerGenerator);
	}

	@Test
	public void should_return_0A0B_when_given_answer_are_totally_wrong() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("5 6 7 8"));

		assertThat(guess.getResult(), is("0A0B"));
	}

	@Test
	public void should_return_1A0B_when_given_answer_one_right_and_other_are_wrong() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("1 6 7 8"));

		assertThat(guess.getResult(), is("1A0B"));
	}

	@Test
	public void should_return_0A1B_when_given_answer_are_one_wrong_postion_and_other_totally_wrong() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("5 1 7 8"));

		assertThat(guess.getResult(), is("0A1B"));
	}

	@Test
	public void should_return_1A1B_when_given_answer_are_one_right_and_one_wrong_position_and_other_totally_wrong() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("1 3 5 8"));

		assertThat(guess.getResult(), is("1A1B"));
	}

	@Test
	public void should_return_4A0B_when_given_answer_are_totally_right() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("1 2 3 4"));

		assertThat(guess.getResult(), is("4A0B"));
	}

	@Test
	public void should_return_0A4B_when_given_answer_are_totally_wrong_position() throws OutOfGuessCountException {
		GuessResult guess = game.guess(Answer.createAnswer("4 3 2 1"));

		assertThat(guess.getResult(), is("0A4B"));
	}

	@Test
	public void should_throw_excpetion_when_the_answer_are_unvalidate() throws AnswerFormatIncorrectException {
		Answer answer = Answer.createAnswer("4 3 2 1 2");
		try {
			answer.validate();
			fail("should throw AnswerFormatIncorrectException");
		} catch (AnswerFormatIncorrectException e) {

		}
	}

	@Test
	public void should_return_the_string_of_answer_when_use_toString(){
	    Answer answer = Answer.createAnswer("1 2 3 4");
	    assertThat(answer.toString(),is("1 2 3 4"));
	}


}