package fraction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleFractionTest {

	double delta;

	@BeforeEach
	public void setUp() {
		this.delta = 0.000001;
	}

	@Test
	void testAdd() {
		Fraction fraction1 = new Fraction(4, 16);
		Fraction fraction2 = new Fraction(5, 16);
		Fraction newFraction = fraction1.add(fraction2);
		assertEquals(9, newFraction.numerator);
		assertEquals(16, newFraction.denominator);
		assertNotSame(newFraction, fraction1);
		assertNotSame(newFraction, fraction2);
		
		fraction1 = new Fraction(3, 7);
		fraction2 = new Fraction(2, 7);
		newFraction = fraction1.add(fraction2);
		assertEquals(5, newFraction.numerator);
		assertEquals(7, newFraction.denominator);
		assertNotSame(newFraction, fraction1);
		assertNotSame(newFraction, fraction2);
		
	}

	@Test
	void testAverageFraction() {
		Fraction fraction1 = new Fraction(1, 2);
		Fraction otherFraction = new Fraction(3, 4);
		Fraction avgFraction = fraction1.average(otherFraction);
		
		assertEquals(5, avgFraction.numerator);
		assertEquals(8, avgFraction.denominator);
		
	}
	
	@Test
	void testAverageFractionArray() {

		Fraction fraction1 = new Fraction(3, 4);
		Fraction fraction2 = new Fraction(3, 5);
		Fraction fraction3 = new Fraction(3, 6);
		
		Fraction[] fractions = new Fraction[] {fraction1, fraction2, fraction3};
		Fraction avgFraction = Fraction.average(fractions);
		
		assertEquals(37, avgFraction.numerator);
		assertEquals(60, avgFraction.denominator);
				
	}

	@Test
	void testAverageIntArray() {

		int[] ints = new int[] {1, 2, 3, 4};
		Fraction avgFraction = Fraction.average(ints);
		
		assertEquals(5, avgFraction.numerator);
		assertEquals(2, avgFraction.denominator);
		
	}

	@Test
	void testDecimal() {
		Fraction fraction1 = new Fraction(4, 16);
		double dec = fraction1.decimal();
		assertEquals(.25, dec, this.delta);
		
		fraction1 = new Fraction(5, 16);
		dec = fraction1.decimal();
		assertEquals(.3125, dec, this.delta);
		
	}

	@Test
	void testDiv() {
		Fraction fraction1 = new Fraction(4, 16);
		Fraction fraction2 = new Fraction(5, 16);
		Fraction newFraction = fraction1.div(fraction2);
		assertEquals(4, newFraction.numerator);
		assertEquals(5, newFraction.denominator);
		
		fraction1 = new Fraction(5, 9);
		fraction2 = new Fraction(3, 9);
		newFraction = fraction1.div(fraction2);
		assertEquals(5, newFraction.numerator);
		assertEquals(3, newFraction.denominator);
				
	}

	@Test
	void testEquals() {
		Fraction fraction1 = new Fraction(2, 3);
		Fraction fraction2 = new Fraction(2, 3);
		assertEquals(fraction1, fraction2);
		
		fraction1 = new Fraction(4, 16);
		fraction2 = new Fraction(1, 4);
		assertEquals(fraction1, fraction2);
		
		//confirm the fractions were not (permanently) reduced to lowest form
		assertEquals(4, fraction1.numerator);
		assertEquals(16, fraction1.denominator);
		assertEquals(1, fraction2.numerator);
		assertEquals(4, fraction2.denominator);
		
	}

	@Test
	void testFraction() {
		
		Fraction fraction = new Fraction(4, 16);
		assertEquals(4, fraction.numerator);
		assertEquals(16, fraction.denominator);
		
		fraction = new Fraction(4, -16);
		assertEquals(4, fraction.numerator);
		assertEquals(-16, fraction.denominator);
		
	}

	@Test
	void testMul() {
		
		Fraction fraction1 = new Fraction(4, 16);
		Fraction fraction2 = new Fraction(5, 16);
		Fraction newFraction = fraction1.mul(fraction2);
		assertEquals(5, newFraction.numerator);
		assertEquals(64, newFraction.denominator);
		
		fraction1 = new Fraction(5, 9);
		fraction2 = new Fraction(3, 9);
		newFraction = fraction1.mul(fraction2);
		assertEquals(5, newFraction.numerator);
		assertEquals(27, newFraction.denominator);
		
	}
	
	@Test
	void testReduceToLowestForm() {
		Fraction fraction = new Fraction(4, 16);
		fraction.reduceToLowestForm();
		
		assertEquals(1, fraction.numerator);
		assertEquals(4, fraction.denominator);
		
		fraction = new Fraction(0, 4);
		fraction.reduceToLowestForm();
		
		assertEquals(0, fraction.numerator);
		assertEquals(1, fraction.denominator);
		
	}

	@Test
	void testSqr() {
		Fraction fraction1 = new Fraction(2, 3);
		fraction1.sqr();
		assertEquals(4, fraction1.numerator);
		assertEquals(9, fraction1.denominator);
		
		fraction1 = new Fraction(4, 16);
		fraction1.sqr();
		assertEquals(1, fraction1.numerator);
		assertEquals(16, fraction1.denominator);
				
	}

	@Test
	void testSubtract() {
		
		Fraction fraction1 = new Fraction(4, 16);
		Fraction fraction2 = new Fraction(5, 16);
		Fraction newFraction = fraction1.subtract(fraction2);
		assertEquals(-1, newFraction.numerator);
		assertEquals(16, newFraction.denominator);
		
		fraction1 = new Fraction(5, 9);
		fraction2 = new Fraction(3, 9);
		newFraction = fraction1.subtract(fraction2);
		assertEquals(2, newFraction.numerator);
		assertEquals(9, newFraction.denominator);
		
	}

	@Test
	void testToString() {
		Fraction fraction1 = new Fraction(2, 3);
		String str = fraction1.toString();
		assertEquals("2/3", str);
		
	}

}
