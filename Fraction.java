package fraction;
import java.lang.Math;

/**
 * CIT 591 HW 7 - Fractions
 * @author Carol Wong
 * Penn ID: 57485844
 * 
 * I completed this assignment refering to the below sources:
 * https://stackoverflow.com/questions/12781806/groovy-eclipse-cant-launch-junit-tests 
 * 
 * This is a Fraction class with methods that represent fractions and performs fraction arithmetic.
 */
class Fraction {

//	public static void main(String[] args) {
// 	}
	
	int absDenominator;
	int absNumerator;
	int denominator;
	int numerator;
	int sign = 1;
	
	
	/**
	 * Constructor. This creates a fraction with the given numerator and denominator.
	 * This also properly formats negative fractions.
	 * Negative fractions have the negative in the numerator.
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		// Takes the numerator and denominator and sets it to absolute value, assign it to the following variables
		this.absNumerator = Math.abs(numerator);
		this.absDenominator = Math.abs(denominator);
		// Check whether num or dem are negative, and if so, assign the negative to the sign
		if(numerator * denominator == 0) {
			this.sign = 0;
		}
		if (numerator * denominator < 0) {
			this.sign = -1;
		}
		if (numerator * denominator > 0) {
			this.sign = 1;
		}
	}
	
	/**
	 * Reduce fraction to its lowest form by eliminating common factors.
	 * This is done using the GCD helper method.
	 */
	public void reduceToLowestForm() {				
		// Takes care of fractions that are 0
		if (this.sign == 0) {
			this.denominator = 1;
			this.absDenominator = 1;
		}
		// Find the greatest common denominator to reduce fraction to lowest form
		else {
		int gcd = findGCD(this.absDenominator, this.absNumerator);		
		this.absNumerator = absNumerator / gcd;  
		this.absDenominator = absDenominator / gcd;
		
			// place the appropriate sign on the numerator, 
			// assign the absNumerator to numerator, and absDenominator to denominator respectively 
			if(this.sign < 0) {
				this.numerator = -1 * this.absNumerator;
				this.denominator = this.absDenominator;
			}
			if(this.sign > 0) {
				this.numerator = 1 * this.absNumerator;
				this.denominator = this.absDenominator;
			}

		}	
	}
	
	
	/**
	 * Adds the current fraction to the given otherFraction.
	 * Returns a new Fraction that is the sum of the two Fractions.
	 * The returned Fraction is in reduced/lowest form.
	 * @param otherFraction
	 * @return sum fraction
	 */
	public Fraction add(Fraction otherFraction) {				
		// find the least common multiple between fractions 
		int lcm = findLCM(this.absDenominator, otherFraction.absDenominator);	
		int addend1 = this.absNumerator * lcm / this.absDenominator;
		int addend2 = otherFraction.absNumerator * lcm / otherFraction.absDenominator;
		
		// assign the signs
		addend1 = addend1 * this.sign;
		addend2 = addend2 * otherFraction.sign;
		
		// combine the two fractions
		Fraction sum = new Fraction(addend1 + addend2, lcm);
		sum.reduceToLowestForm();
		return sum;
	}
	
	/**
	 * Subtract the given otherFraction from the current fraction.
	 * That is, thisFraction - otherFraction.
	 * Returns a new Faction that is the difference of the two Fractions.
	 * Returned fraction is in reduced/lowest form.
	 * @param otherFraction
	 * @return difference
	 */
	public Fraction subtract(Fraction otherFraction) {
		// perform subtraction by reusing add method, adding the opposite value		
		Fraction subtraction = new Fraction(otherFraction.numerator * -1, otherFraction.denominator);
		Fraction difference = this.add(subtraction);
		return difference;
	}
	

	/**
	 * Multiply the current fraction by the given otherFraction.
	 * Returns a new Fraction that is the product of this fraction the the otherFraction.
	 * The returned Fraction is in reduced/lowest form.
	 * @param otherFraction
	 * @return product Fraction
	 */
	public Fraction mul(Fraction otherFraction) {
		
		// product in abs form
		int numProduct = this.absNumerator * otherFraction.absNumerator;
		int denomProduct = this.absDenominator * otherFraction.absDenominator;
		Fraction product = new Fraction(numProduct, denomProduct);
		
		// assign the signs
		product.sign = this.sign * otherFraction.sign;
		product.numerator = product.numerator * product.sign;			

		product.reduceToLowestForm();
		return product;
	}
	
	

	
	/**
	 * Divide the current fraction by the given otherFraction.
	 * That is, thisFractin / other Fraction.
	 * Return a new Fraction that is the quotient of this fraction and the other fraction.
	 * The returned Fraction is in reduced/lowest form.
	 * @param otherFraction
	 * @return quotient fraction
	 */
	public Fraction div(Fraction otherFraction) {
		// takes care of 0 divisors
		if(otherFraction.toString().equals("0/1")) {
			throw new ArithmeticException("Division by zero is undefined.");	
		}
		// divide: reuse mul by multiplying reciprocal.
		int newNumerator = otherFraction.denominator;
		int newDenominator = otherFraction.numerator;
		Fraction flipMul = new Fraction(newNumerator, newDenominator); 		
		Fraction quotient = this.mul(flipMul);
		return quotient;
	}
	
	
	/**
	 * Return the fraction in decimal form
	 * @return double decimal
	 */
	public double decimal() {
		double doubleDenom = (double) this.denominator;
		return this.numerator / doubleDenom;
	}
	
	
	/**
	 * Square the current fraction.
	 * This method modifies the current fraction and reduces it to lowest form.
	 */
	public void sqr() {
		// self * self for both numerator and denominator
		this.numerator = this.absNumerator * this.absNumerator;
		this.absNumerator = this.absNumerator * this.absNumerator;
		this.denominator = this.absDenominator * this.absDenominator;
		this.absDenominator = this.absDenominator * this.absDenominator;
		
		// the product is always positive
		this.sign = 1;
		
		this.reduceToLowestForm();
	}


	/**
	 * Average the current fraction with the given otherFraction.
	 * Return a new Fraction that is the average of this fraction and the otherFraction.
	 * The returned fraction is in reduced/lowest form
	 * @param otherFraction
	 * @return average as fraction
	 */
	public Fraction average(Fraction otherFraction) {		
		
		// add fractions
		Fraction sumFraction = this.add(otherFraction);
		
		// divide by the number of values
		Fraction twoFraction = new Fraction(2, 1);
		
		Fraction divFraction = sumFraction.div(twoFraction);
		divFraction.reduceToLowestForm();
		
		return divFraction;		
	}
	
	
	/**
	 * Static method to average all of fractions in the given array.
	 * The current fraction is not included.
	 * Returns the average of the array.
	 * If the array is empty, returns a new fraction that equals 0.
	 * @param fractions
	 * @return average as fraction
	 */
	public static Fraction average(Fraction[] fractions) {
		Fraction sum = new Fraction(0, 1);
		
		// takes care of empty arrays
		if (fractions.length == 0){
			return sum;
		}
		
		// use the add method to sum up fractions
		for(int i = 0; i < fractions.length; i++) {
			sum = sum.add(fractions[i]); 
		}
		
		// use the divide method to get the average
		Fraction count = new Fraction(fractions.length, 1);
		Fraction avg = sum.div(count);		
		return avg;
	}
	
	
	/**
	 * Overriden method to compare the given object (as a fraction) to the current fraction,
	 * for equality. 
	 * Two fractions are considered equal if they have the same numerator and same denominator,
	 * after eliminating common factors.
	 */
	@Override
	public boolean equals(Object object) {
		
		//check whether the object something that we can cast into fraction
		if(!(object instanceof Fraction)) {
			return false;
		}
		
		//create fraction objects, reduce to the lowest form
		Fraction thisFraction = new Fraction(this.numerator, this.denominator);
		thisFraction.reduceToLowestForm();
		
		Fraction otherFraction = (Fraction)object;
		otherFraction = new Fraction(otherFraction.numerator, otherFraction.denominator);
		otherFraction.reduceToLowestForm();
		
		// compare the numerators and denominators
		return (thisFraction.numerator == otherFraction.numerator
				&& thisFraction.denominator == otherFraction.denominator);
	}
	
	/**
	 * Print Fraction in a particular way.
	 */
	@Override
	public String toString() {
		if(this.sign < 0) {
			return "-" + this.absNumerator + "/" + this.absDenominator;
		}
		return this.numerator + "/" + this.denominator;
		}
	
	/**
	 * Helper method to get the greatest common divisor of a and b.
	 * @param a
	 * @param b
	 * @return gcd
	 */
	public int findGCD(int a, int b) {
		int gcd = 1;
		int smaller = Math.min(a, b);
		
		if(a == b) {
			return a;
		}		
		if(a == 0) {
			return b;
		}		
		if(b == 0) {
			return a;
		}		
		// find the greatest common divisor
		for (int i = smaller; i > 1 ; i--) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
				break;
			}
		}		
		return gcd;
	}
	
	
	/**
	 * Helper method to get the least common multiple of a and b, using GCD.
	 * @param a
	 * @param b
	 * @return lcm
	 */
	public int findLCM(int a, int b) {
		if(a * b == 0) {
			return 0;
		}
		// find the least common multiple
		else {
			int lcm = (a * b) / findGCD(a, b);		
			return lcm;
		}
	}
	
	
	
	/**
	 * Static method to average all the integers in the given array.
	 * The current fraction is not included in the average.
	 * Return the average of the array as a new fraction.
	 * The returned fraction is in reduced/lowest form.
	 * @param ints
	 * @return Average of these integers: in a Fraction
	 */
	public static Fraction average(int[] ints) {
		
		// takes care of empty arrays
		if (ints.length == 0 || ints == null) {
			return new Fraction(0, 1);
		}	
		// create a new fraction
		int sum = 0;
		int count = ints.length;		
		for (int i = 0; i< count; i++) {
			sum += ints[i];
		}
		Fraction average = new Fraction(sum, count);
		average.reduceToLowestForm();		
		return average;
	}
	

			
	
}