package fraction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;


class FractionTest {
	
	int[] integers = {2, 4, 7 ,10};
	int[] integers2 = {-10, -6, 5, 0, 8};
	int[] integersEmpty = {};
	int[] integersNull = null;
	Fraction emptyFraction;
	double err;
	Fraction sampleFraction1;
	Fraction sampleFraction2;
	Fraction sampleFraction3;
	Fraction sampleFraction4;
	Fraction sampleFraction5;
	Fraction[] sampleFractionsArray1 = 
		{new Fraction(2, 3), new Fraction(1, 7), new Fraction(-3, 14), new Fraction(4, -3)};
	Fraction[] sampleFractionsArray2 =
		{new Fraction(7, -3), new Fraction(0, 1), new Fraction(-5, 5), new Fraction(5, 15)};
	Fraction[] sampleFractionsArray3 = {};
	Fraction[] sampleFractionsArray4 = null;
	

	
	@SuppressWarnings("deprecation")
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	

	@BeforeEach
	void setUp() throws Exception {
		this.sampleFraction1 = new Fraction(7,21);
		this.sampleFraction2 = new Fraction(21,-14);
		this.sampleFraction3 = new Fraction(-14,77);
		this.sampleFraction4 = new Fraction(0,11);	
		this.sampleFraction5 = new Fraction(-14,28);	
		this.emptyFraction = null;	
		this.err = 0.000001;
	}

	
	@Test
	void testAdd(){
		// test 1: adding two fractions
		assertEquals(5, sampleFraction1.add(sampleFraction3).numerator);
		assertEquals(33, sampleFraction1.add(sampleFraction3).denominator);
		// test 2: adding another two fractions
		assertEquals(-1, sampleFraction1.add(sampleFraction5).numerator);
		assertEquals(6, sampleFraction1.add(sampleFraction5).denominator);
		// test 3: adding a fraction with a 0 numerator
		assertEquals(-3, sampleFraction2.add(sampleFraction4).numerator);
		assertEquals(2, sampleFraction2.add(sampleFraction4).denominator);	
		// test 4: adding a fraction with a negative denominator
		assertEquals(-7, sampleFraction1.add(sampleFraction2).numerator);
		assertEquals(6, sampleFraction1.add(sampleFraction2).denominator);
	}
	
	
	@Test
	void testAverage(){

		// Test 1: averaging two normal fractions:
		assertEquals("-7/12", sampleFraction2.average(sampleFraction1).toString());
		assertEquals("5/66", sampleFraction3.average(sampleFraction1).toString());
		// Test 2: averaging normal fraction with a 0 fraction
		assertEquals(1, sampleFraction4.average(sampleFraction1).numerator);
		assertEquals(6, sampleFraction4.average(sampleFraction1).denominator);
		// Test 3: averaging itself
		assertEquals(1, sampleFraction1.average(sampleFraction1).numerator);
		assertEquals(3, sampleFraction1.average(sampleFraction1).denominator);
		
		// Test 4: averaging an array of fractions
		assertEquals("-31/168", Fraction.average(sampleFractionsArray1).toString());
		//  Test 5: averaging an array of fractions with zeros
		assertEquals("-3/4", Fraction.average(sampleFractionsArray2).toString());
		assertEquals(-31, Fraction.average(sampleFractionsArray1).numerator);
		assertEquals(168, Fraction.average(sampleFractionsArray1).denominator);
		// Test 6: averaging an empty array
		assertEquals(0, Fraction.average(sampleFractionsArray3).numerator);
		assertEquals(1, Fraction.average(sampleFractionsArray3).denominator);
		
		// test 7: averaging an array of integers
		assertEquals(23, Fraction.average(integers).numerator);
		assertEquals(4, Fraction.average(integers).denominator);
		assertEquals("23/4", Fraction.average(integers).toString());		
		// test 8: averaging an array of integers with zeros and negatives
		assertEquals(-3, Fraction.average(integers2).numerator);
		assertEquals(5, Fraction.average(integers2).denominator);
		assertEquals("-3/5", Fraction.average(integers2).toString());		
		// test 9: averaging an empty array
		assertEquals(0, Fraction.average(integersEmpty).numerator);
		assertEquals(1, Fraction.average(integersEmpty).denominator);
		assertEquals("0/1", Fraction.average(integersEmpty).toString());	
		// test 10: averaging a null array
		assertEquals(0, Fraction.average(integersNull).numerator);
		assertEquals(1, Fraction.average(integersNull).denominator);
		assertEquals("0/1", Fraction.average(integersNull).toString());
	}
	
	
	@Test
	void testConstructor() {
		// test 1: constructor for normal fraction
		assertTrue(sampleFraction1.numerator == 7);
		assertTrue(sampleFraction1.denominator == 21);
		assertTrue(sampleFraction1.absNumerator == 7);
		assertTrue(sampleFraction1.absDenominator == 21);
		assertTrue(sampleFraction1.sign == 1);
		// test 2: constructor for negative fraction		
		assertTrue(sampleFraction2.numerator == 21);
		assertTrue(sampleFraction2.denominator == -14);
		assertTrue(sampleFraction2.absNumerator == 21);
		assertTrue(sampleFraction2.absDenominator == 14);
		assertTrue(sampleFraction2.sign == -1);
		// test 3: sign for normal fraction
		assertTrue(sampleFraction1.sign == 1);	
		// test 4: sign for negative fraction
		assertTrue(sampleFraction2.sign == -1);
		// test 5: sign for fraction with 0 numerator
		assertTrue(sampleFraction4.sign == 0);
	}
	

	@Test
	void testDecimal(){
		// test 1: normal fractions
		assertEquals(0.33333333, sampleFraction1.decimal(), err);
		// test 2: negative fractions
		assertEquals(-1.5, sampleFraction2.decimal(), err);
		assertEquals(-0.18181818, sampleFraction3.decimal(), err);
		// test 3: zero denominator
		assertEquals(0.0, sampleFraction4.decimal(), err);
		// test 4: negative
		assertEquals(-0.5, sampleFraction5.decimal(), err);
	}
	
	
	@Test
	void testDiv(){
		// test 1: dividing normal fractions
		assertEquals(-2, sampleFraction1.div(sampleFraction2).numerator);
		assertEquals(9, sampleFraction1.div(sampleFraction2).denominator);
		assertEquals("-2/9", sampleFraction1.div(sampleFraction2).toString());
		// test 2: dividing normal fractions
		assertEquals(33, sampleFraction2.div(sampleFraction3).numerator);
		assertEquals(4, sampleFraction2.div(sampleFraction3).denominator);
		assertEquals("33/4", sampleFraction2.div(sampleFraction3).toString());
		// test 3: dividing fractions with zero
		assertEquals(0, sampleFraction4.div(sampleFraction3).numerator);
		assertEquals(1, sampleFraction4.div(sampleFraction3).denominator);
		// test 4: dividing two negative fractions
		assertEquals(4, sampleFraction3.div(sampleFraction2).numerator);
		assertEquals(33, sampleFraction3.div(sampleFraction2).denominator);
		assertEquals("4/33", sampleFraction3.div(sampleFraction2).toString());
	}
	
	
	@Test	
	void testDivZero() {
		// test: dividing by zero should throw an exception
		thrown.expect(ArithmeticException.class);
		sampleFraction2.div(sampleFraction4);
	}
	
	
	@Test
	void testFindGCD() {
		// test 1-4: findGCD (greatest common divisor) 
		assertEquals(3, sampleFraction1.findGCD(3, 3));
		assertEquals(14, sampleFraction1.findGCD(14, 28));
		assertEquals(7, sampleFraction1.findGCD(0,7));
		assertEquals(1, sampleFraction1.findGCD(1,0));				
	}
	
	
	@Test
	void testFindLCM() {
		// test 1-4: findLCM (least common multiple) 
		assertEquals(48, sampleFraction1.findLCM(16, 24));
		assertEquals(19, sampleFraction1.findLCM(19, 19));
		assertEquals(24, sampleFraction1.findLCM(3, 8));
		assertEquals(0, sampleFraction1.findLCM(0, 15));
	}
	
	
	@Test
	void testMul(){
		//  test 1: multiplying normal fractions
		assertEquals(-2, sampleFraction1.mul(sampleFraction3).numerator);
		assertEquals(33, sampleFraction1.mul(sampleFraction3).denominator);
		// test 2: multiply by fraction with 0 numerator
		assertEquals("0/1", sampleFraction1.mul(sampleFraction4).toString());
		// test 3: multiply two negative fractions
		assertEquals(1, sampleFraction3.mul(sampleFraction5).numerator);
		assertEquals(11, sampleFraction3.mul(sampleFraction5).denominator);
		// test 4: multiply negative with zero
		assertEquals(0, sampleFraction2.mul(sampleFraction4).numerator);
		assertEquals(1, sampleFraction2.mul(sampleFraction4).denominator);

	}
	
	
	@Test
	void testReduceToLowestForm(){
		// test 1: normal fraction
		this.sampleFraction1.reduceToLowestForm();
		assertEquals(1, this.sampleFraction1.numerator);
		assertEquals(3, this.sampleFraction1.denominator);
		// test 2: fraction with negative denominator
		this.sampleFraction2.reduceToLowestForm();
		assertEquals(-3, this.sampleFraction2.numerator);
		assertEquals(2, this.sampleFraction2.denominator);
		// test 3: fraction with negative numerator
		this.sampleFraction3.reduceToLowestForm();
		assertTrue(this.sampleFraction3.numerator == -2);
		assertTrue(this.sampleFraction3.denominator == 11);
		// test 4: fraction that equals 0
		this.sampleFraction4.reduceToLowestForm();
		assertTrue(this.sampleFraction4.numerator == 0);
		assertTrue(this.sampleFraction4.denominator == 1);
	}
	
	
	@Test
	void testSqr(){
		// test 1: squaring a normal fraction
		sampleFraction1.sqr();
		assertEquals(1, sampleFraction1.numerator);
		assertEquals(9, sampleFraction1.denominator);
		assertEquals("1/9", sampleFraction1.toString());	
		// test 2: squaring a fraction with negative denominator
		sampleFraction2.sqr();
		assertEquals(9, sampleFraction2.numerator);
		assertEquals(4, sampleFraction2.denominator);
		assertEquals("9/4", sampleFraction2.toString());
		// test 3: squaring a fraction with negative numerator
		sampleFraction3.sqr();
		assertEquals(9, sampleFraction2.numerator);
		assertEquals(4, sampleFraction2.denominator);
		assertEquals("9/4", sampleFraction2.toString());
		// test 4: squaring a fraction with 0 numerator
 		sampleFraction4.sqr();
		assertEquals(0, sampleFraction4.numerator);
		assertEquals(1, sampleFraction4.denominator);
		assertEquals("0/1", sampleFraction4.toString());	
	}
	
	
	@Test
	void testSubtract(){
		// test 1: subtracting normal fractions
		assertEquals(11, sampleFraction1.subtract(sampleFraction2).numerator);
		assertEquals(6, sampleFraction1.subtract(sampleFraction2).denominator);
		// test 2: subtracting negative fraction
		assertEquals(11, sampleFraction1.subtract(sampleFraction2).numerator);
		assertEquals(6, sampleFraction1.subtract(sampleFraction2).denominator);
		// test 3: fraction subtracting itself
		assertEquals(0,sampleFraction1.subtract(sampleFraction1).numerator);
		assertEquals(1,sampleFraction1.subtract(sampleFraction1).denominator);
		// test 4: subtracting fraction with 0 numerator
		assertEquals(1, sampleFraction1.subtract(sampleFraction4).numerator);
		assertEquals(3, sampleFraction1.subtract(sampleFraction4).denominator);
		
	}
		
		
	@Test
	void testToString(){
		assertEquals("7/21", sampleFraction1.toString());
		assertEquals("-21/14", sampleFraction2.toString());
		assertEquals("-14/77", sampleFraction3.toString());
		assertEquals("0/11", sampleFraction4.toString());
	}
}