package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.complex_function;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() {
		ComplexFunction c = new ComplexFunction();
		complex_function c1 = new ComplexFunction(c.toString());
		assertEquals(c1, c);
		ComplexFunction c2 = new ComplexFunction("Divid(4x^2,3+x)");
		complex_function c3 = new ComplexFunction(c2.toString());
		assertEquals(c3, c2);
		ComplexFunction c4 = new ComplexFunction("Plus(Divid(4x^2,3+x),-2)");
		System.out.println(c4);
		complex_function c5 = new ComplexFunction(c.toString());
		System.out.println(c5);
		assertEquals(c4, c5);
		
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testPlus() {
		fail("Not yet implemented");
	}

	@Test
	void testMul() {
		fail("Not yet implemented");
	}

	@Test
	void testDiv() {
		fail("Not yet implemented");
	}

	@Test
	void testMax() {
		fail("Not yet implemented");
	}

	@Test
	void testMin() {
		fail("Not yet implemented");
	}

	@Test
	void testComp() {
		fail("Not yet implemented");
	}

	@Test
	void testLeft() {
		fail("Not yet implemented");
	}

	@Test
	void testRight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOp() {
		fail("Not yet implemented");
	}

}
