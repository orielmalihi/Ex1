package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.complex_function;
import Ex1.function;

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
		ComplexFunction f1 = new ComplexFunction("Plus(Divid(4x^2,3+x),-2)");
		function f2 = f1.initFromString("Plus(Divid(4x^2,3+x),-2)");
		assertEquals(f1, f2);
	}

	@Test
	void testCopy() {
		ComplexFunction f1 = new ComplexFunction("Plus(Divid(4x^2,3+x),-2)");
		function f2 = f1.copy();
		assertEquals(f1, f2);
	}

	@Test
	void testPlus() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.plus(p);
		f1.plus(f2);
		p.multiply(new Monom("2"));
		f1.plus(p);
		ComplexFunction expected = new ComplexFunction("Plus(Plus(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testMul() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.mul(p);
		f1.mul(f2);
		p.multiply(new Monom("2"));
		f1.mul(p);
		ComplexFunction expected = new ComplexFunction("Times(Times(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testDiv() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.div(p);
		f1.div(f2);
		p.multiply(new Monom("2"));
		f1.div(p);
		ComplexFunction expected = new ComplexFunction("Divid(Divid(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testMax() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.max(p);
		f1.max(f2);
		p.multiply(new Monom("2"));
		f1.max(p);
		ComplexFunction expected = new ComplexFunction("Max(Max(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testMin() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.min(p);
		f1.min(f2);
		p.multiply(new Monom("2"));
		f1.min(p);
		ComplexFunction expected = new ComplexFunction("Min(Min(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testComp() {
		ComplexFunction f1 = new ComplexFunction();
		ComplexFunction f2 = new ComplexFunction("Times(3x+x^2, 2x)");
		Polynom p = new Polynom("1+x");
		f1.comp(p);
		f1.comp(f2);
		p.multiply(new Monom("2"));
		f1.comp(p);
		ComplexFunction expected = new ComplexFunction("Comp(Comp(1+x,Times(3x + x^2, 2x)),2+2x)");
		assertEquals(expected, f1);
	}

	@Test
	void testLeft() {
		ComplexFunction f1 = new ComplexFunction("Plus(2+x,x^2)");
		ComplexFunction f2 = new ComplexFunction("Comp(Plus(2+x,x^2),4x)");
		function f1Left = f1.left();
		function f2Left = f2.left();
		Polynom f1Expected = new Polynom("2+x");
		ComplexFunction f2Expected = new ComplexFunction("Plus(2+x,x^2)");
		assertEquals(f1Expected, f1Left);
		assertEquals(f2Expected, f2Left);
	}

	@Test
	void testRight() {
		ComplexFunction f1 = new ComplexFunction("Plus(2+x,x^2)");
		ComplexFunction f2 = new ComplexFunction("Comp(Plus(2+x,x^2),4x)");
		function f1Right = f1.right();
		function f2Right = f2.right();
		Polynom f1Expected = new Polynom("x^2");
		Polynom f2Expected = new Polynom("4x");
		assertEquals(f1Expected, f1Right);
		assertEquals(f2Expected, f2Right);
	}
	

	@Test
	void testGetOp() {
		ComplexFunction f1 = new ComplexFunction();
		f1.plus(new ComplexFunction("Times(3x,x)"));
		assertEquals("None", f1.getOp().toString());
		f1.plus(new Polynom("2"));
		assertEquals("Plus", f1.getOp().toString());
		f1.mul(new ComplexFunction("Comp(x,x^2"));
		assertEquals("Times", f1.getOp().toString());
		
		
	}

}
