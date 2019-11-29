package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.complex_function;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() {
		String[] goodExamples = {"Divid(4x^2,3+x)", "Plus(Divid(4x^2,3+x),-2)", "Plus(3x,Divid(x^2,x))" };
		for(int i = 0; i<goodExamples.length; i++) {
			ComplexFunction f1 = new ComplexFunction(goodExamples[i]);
			ComplexFunction f2 = new ComplexFunction(f1.toString());
			assertEquals(f1, f2);
			
		}
		String[] badExamples = { "plus(x,2)", "Comp((x^2, x)", "Comp(x^2, x))", "Min(x^2 x))"};	
		int error = 0;
		for(int i = 0; i<badExamples.length; i++ ) {
			try {
				ComplexFunction f3 = new ComplexFunction(badExamples[i]);
			} catch (Exception e){
				error++;
			}
		}
		assertEquals(badExamples.length, error);
		
	}

	@Test
	void testF() {
		ComplexFunction cof = new ComplexFunction("Plus(Divid(3x+x^2,x+2),x^3)");
		double x = 2;
		double expected = 10.5;
		assertEquals(expected, cof.f(x));
		ComplexFunction cof1 = new ComplexFunction("Min(Min(3x+x^2,x+2),x^3)");
		expected = 4;
		assertEquals(expected, cof1.f(x));
		ComplexFunction cof2 = new ComplexFunction("Max(Max(3x+x^2,x+2),x^3)");
		expected = 10;
		assertEquals(expected, cof2.f(x));
		ComplexFunction cof3 = new ComplexFunction("Max(Times(3x+x^2,x+2),x^5)");
		expected = 40;
		assertEquals(expected, cof3.f(x));
		x = 3;
		ComplexFunction cof4 = new ComplexFunction("Comp(Comp(x^2,x+2),x)");
		expected = 25;
		assertEquals(expected, cof4.f(x));
		int error = 0;
		String[] badExamples = { "Divid(Plus(3x+x^2,x),0)", "Error(Divid(3x+x^2,x),x^3)"};																													
		for(int i = 0; i<badExamples.length; i++ ) {
			try {
				ComplexFunction cof5 = new ComplexFunction(badExamples[i]);
				cof5.f(x);
				
			} catch (Exception e){
				error++;
			}
		}
		assertEquals(badExamples.length, error);
			
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
