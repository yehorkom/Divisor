package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DivisorTest {

	Divisor division = new Divisor();

	@Test
	void testDoesDivisionWithRemainderWork() {
		String expected = """
			_125846│3
			 12    │-----
			 --    │41948
			  _5
			   3
			   -
			  _28
			   27
			   --
			   _14
			    12
			    --
			    _26
			     24
			     --
			      2
			""";

		assertEquals(expected, division.makeDivision(125846, 3));
	}

}
