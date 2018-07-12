package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.exception.OutOfGuessCountException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

}