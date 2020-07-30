package ru.itmo.isddev.Ilin.fraction;
import java.math.BigInteger;

public class BigIntegerFraction extends Fraction<BigInteger> {

	public BigIntegerFraction(){
	}

	public BigIntegerFraction(BigInteger inDen, BigInteger inDiv) {
		denominator = inDen;
		divider = inDiv;
	}

	public BigIntegerFraction(BigIntegerFraction in) {
		denominator = in.getDen();
		divider = in.getDiv();
	}

	public void simplify(){
			BigInteger tmp = gcd(denominator, divider);
			denominator = denominator.divide(tmp);
			divider = divider.divide(tmp);
	}

	static BigInteger gcd(BigInteger n1, BigInteger n2) {
		if (n2.compareTo(BigInteger.valueOf(0l)) != 0)
	       return gcd(n2, n1.mod(n2));
	    else 
	       return n1;
	}

	public BigInteger getDen(){
		return denominator;
	}

	public BigInteger getDiv(){
		return divider;
	} 

	public BigIntegerFraction copy() {
		return new BigIntegerFraction(denominator,divider);
	}

	public boolean isLess(Fraction in) {
		return ensuredIsLess(in);
	}

	public boolean isEqual(Fraction in) {
		return ensuredIsEqual(in);
	}

	public void parse(String[] in) {
		denominator = new BigInteger(in[0]);
		divider = new BigInteger(in[1]);
	}

	public BigIntegerFraction add(Fraction in) {
		if (!(in instanceof BigIntegerFraction)) 
			throw (new IllegalArgumentException(in.getClass().getSimpleName() + " cannot be added to " + this.getClass().getSimpleName()));
		denominator = (denominator.multiply((BigInteger) in.getDiv()))
					  .add(((BigInteger) in.getDen()).multiply(divider));
		divider = divider.multiply((BigInteger)in.getDiv());
		return this;
	}

}