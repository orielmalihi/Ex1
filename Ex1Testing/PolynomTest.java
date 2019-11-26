package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

class PolynomTest {

	@Test
	void testF() {
		Polynom p = new Polynom("-3x+2x^2+x^3");
		double ans = p.f(2);
		double expected = 10;
		assertEquals(expected, ans);
	}

	@Test
	void testAddPolynom_able() {
		Polynom p = new Polynom("2x+5x^4");
		Polynom_able pCopy = p.copy();
		pCopy.multiply(new Monom("-1"));
		p.add(pCopy);
		assertEquals(p.toString(), "0");


	}

	@Test
	void testSubstractPolynom_able() {
		Polynom p1 = new Polynom("3x+4x^2+5x^3");
		p1.substract(p1);
		Polynom p2 = new Polynom("0");
		assertEquals(p1, p2);
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom p = new Polynom("3+x");
		Polynom p1 = new Polynom("4+x");
		p.multiply(p1);
		Polynom expected = new Polynom("12+7x+x^2");
		assertEquals(p, expected);
	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p = new Polynom("3x+4x^2");
		Polynom_able pa = p.copy();
		if(p.equals(pa)!=true)
			fail("not Equals!");
		assertEquals(p, pa);
	}

	@Test
	void testIsZero() {
		Polynom p = new Polynom("4-x^6+x^10");
		p.multiply(Monom.ZERO);
		Polynom expected = new Polynom("0");
		assertEquals(expected, p);
	}

	@Test
	void testRoot() {
		Polynom p = new Polynom("-2+2x");
		double ans = p.root(0, 2, 0.0000001);
		double expected = 1;
		assertEquals(expected, ans);

	}

	@Test
	void testCopy() {
		Polynom p = new Polynom("4-x^6+x^10");
		Polynom_able pcop = p.copy();
		assertEquals(p, pcop);
	}

	@Test
	void testDerivative() {
		Polynom p = new Polynom("5-2x+3x^3");
		Polynom_able pDerivative = p.derivative();
		Polynom expected = new Polynom("-2+9x^2");
		assertEquals(expected, pDerivative);
	}

	@Test
	void testArea() {
		Polynom p = new Polynom("-2+2x");
		double area = p.area(2, 4, 0.000001);
		double expected = 8;
		double diff = expected-area;
		if(diff<0)
			diff *= -1;
		if(diff>0.0001)
			fail("Error in calculating the Area function");
		
		
	}

	@Test
	void testToString() {
		Polynom p1 = new Polynom("-3+4x+6x^7");
		String s = p1.toString();
		Polynom p2 = new Polynom(s);
		assertEquals(p1, p2);
	}

	@Test
	void testInitFromString() {
		Polynom p = new Polynom("-3 + 4x + 6x^7");
		function f = p.initFromString("-3+4x+6x^7");
		function f2 = (function)p;
		assertEquals(f, f2);
	}

}
