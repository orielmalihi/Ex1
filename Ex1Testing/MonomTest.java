package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.function;

class MonomTest {

	@Test
	void testMonomMonom() {
		Monom m1 = new Monom(3,2);
		Monom m2 = new Monom(m1);
		assertEquals(m1, m2);
	}

	@Test
	void testDerivative() {
		Monom m1 = new Monom(3,3);
		Monom m2 = m1.derivative();
		Monom m3 = new Monom(9,2);
		assertEquals(m2, m3);
	}

	@Test
	void testF() {
		Monom m = new Monom(-3,2);
		double ans = m.f(3);
		assertEquals(ans, -27);
	}

	@Test
	void testIsZero() {
		Monom m1 = Monom.ZERO;
		Monom m2 = new Monom("0");
		Monom m3 = new Monom("0x^2");
		assertEquals(m1, m2);
		assertEquals(m1, m3);
	}

	@Test
	void testMonomString() {
		Monom m1 = new Monom(5,3);
		Monom m2 = new Monom("5x^3");
		assertEquals(m1, m2);
	}

	@Test
	void testAdd() {
		Monom m1 = new Monom("4x^5");
		Monom m2 = new Monom("-3x^5");
		m1.add(m2);
		Monom ans = new Monom("x^5");
		assertEquals(ans, m1);
		
	}

	@Test
	void testMultiply() {
		Monom m1 = new Monom("4x^3");
		Monom m2 = new Monom("3x^4");
		m1.multiply(m2);
		Monom ans = new Monom("12x^7");
		assertEquals(ans, m1);
		
	}

	@Test
	void testToString() {
		Monom m1 = new Monom(4,9);
		String s = m1.toString();
		Monom m2 = new Monom(s);
		assertEquals(m2, m1);
	}

	@Test
	void testEqualsObject() {
		Monom m1 = new Monom("3x^5");
		Monom m2 = new Monom(3,5);
		assertEquals(m1.toString(), m2.toString());
		if(m1.equals(m2)!=true)
			fail("not equals");
	}

	@Test
	void testCopy() {
		Monom m1 = new Monom("5x^6");
		function f1 = m1.initFromString("5x^6");
		function f2 = m1.copy();
		assertEquals(f1, f2);
	}
	
	@Test
	void testInitFromString() {
		Monom m1 = new Monom(3,4);
		function f = m1.initFromString("3x^4");
		assertEquals(f.toString(), m1.toString());
	}
}
