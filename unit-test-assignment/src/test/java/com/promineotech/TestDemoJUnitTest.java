package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void asserThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
	    if (!expectException) {
	        assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
	    }
	}

	

	public void testAddPositive_NegativeInput() {
		TestDemo testDemo = new TestDemo();
		int a = -5;
		int b = 7;

		assertThatThrownBy(() -> {
			testDemo.addPositive(a, b);
		}).isInstanceOf(IllegalArgumentException.class).hasMessage("Both parameters must be positive!");

	}

	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
				arguments(2, 4, 6, false), 
				arguments(-1, 5, 4, true), 
				arguments(1, 2, 3, false),
				arguments(0, 5, 5, true));
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {

		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);

		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);

		assertThat(testDemo.addPositive(4, 3)).isEqualTo(7);

		assertThat(testDemo.addPositive(17, 5)).isEqualTo(22);

	}

	@Test
	public void testThisNumberIsOdd_OddNumber() {

		TestDemo testDemo = new TestDemo();
		int oddNumber = 7;
		assertTrue(testDemo.thisNumberIsOdd(oddNumber));
	}

	@Test
	public void testThisNumberIsOdd_EvenNumber() {
		TestDemo testDemo = new TestDemo();
		int evenNumber = 6;

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			testDemo.thisNumberIsOdd(evenNumber);
		});

		assertEquals("Sorry, that number is not odd.", exception.getMessage());
	}
	
	@Test
	private int assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
		return fiveSquared;
	}
}
