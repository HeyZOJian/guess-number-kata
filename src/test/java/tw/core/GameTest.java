package tw.core;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.OutOfGuessCountException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.core.model.Record;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

	private final Answer actualAnswer = Answer.createAnswer("1 2 3 4");
	private Game game;

	@BeforeEach
	public void setUp() throws Exception {
		AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
		when(answerGenerator.generate()).thenReturn(actualAnswer);
		game = new Game(answerGenerator);
	}


	@Test
	public void should_get_the_success_status_when_guess_input_is_correct() throws Exception {

		//given
//        excuteSuccessGuess();
		GuessResult guess = game.guess(Answer.createAnswer("1 2 3 4"));
		//when
		//then
		assertThat(guess.getResult(), is("4A0B"));

	}

	@Test
	public void should_get_the_success_status_when_guess_twice_and_the_last_one_is_correct() throws Exception {
		// given

		// when
		GuessResult guessResult = game.guess(Answer.createAnswer("1 2 4 3"));
		guessResult = game.guess(Answer.createAnswer("1 2 3 4"));
		//then
		assertThat(guessResult.getResult(), is("4A0B"));
	}

	@Test
	public void should_get_the_fail_status_when_guess_sextic_and_all_answer_are_wrong() throws Exception {
		// given

		// when
		try {
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			fail("should return fail");
		} catch (OutOfGuessCountException e) {
			//then
		}
	}

	@Test
	public void should_get_the_fail_status_when_guess_more_than_sextic_and_all_answer_are_wrong() throws Exception {
		// given

		// when
		try {
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			fail("should return fail");
		} catch (OutOfGuessCountException e) {
			//then
		}
	}

	@Test
	public void should_get_the_success_status_when_guess_less_than_6_time_and_have_not_right_answer() throws Exception {
		// given

		// when

			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("2 2 4 3"));
			game.guess(Answer.createAnswer("1 2 3 4"));
			//then
			assertThat(game.checkStatus(),is("success"));
	}

	@Test
	public void should_return_guess_history_when_get_guess_history_given_one_guess() throws OutOfGuessCountException {
		// given
		// when
		GuessResult guessResult = game.guess(Answer.createAnswer("2 2 4 3"));
		//then
		assertThat(game.guessHistory().get(0), is(guessResult));
	}

	@Test
	public void should_return_guess_history_when_get_guess_history_given_twice_guess() throws OutOfGuessCountException {
		// given
		// when
		game.guess(Answer.createAnswer("4 3 2 1"));
		GuessResult guessResult = game.guess(Answer.createAnswer("2 2 4 3"));
		//then
		assertThat(game.guessHistory().get(1), is(guessResult));
	}

}
