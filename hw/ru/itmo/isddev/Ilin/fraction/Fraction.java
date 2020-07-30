package ru.itmo.isddev.Ilin.fraction;
import java.math.BigInteger;

public abstract class Fraction <T extends Number> {
	protected T denominator;
	protected T divider;

	private int bigIntComp(Fraction f2) {
		BigInteger lhs = new BigInteger(denominator.toString());
		lhs = lhs.multiply(new BigInteger(f2.getDiv().toString()));
		BigInteger rhs = new BigInteger(f2.getDen().toString());
		rhs = rhs.multiply(new BigInteger(divider.toString()));
		return lhs.compareTo(rhs);
	}

	static long gcd(long n1, long n2) {
		if (n2 != 0)
	       return gcd(n2, n1 % n2);
	    else 
	       return n1;
	}

	public double evaluate() {
		return denominator.doubleValue() / divider.doubleValue();
	}

	public abstract void simplify();

	public String toString() {
		return "" + denominator.toString() + " / " + divider.toString();
	}

	protected String toSaveable() {
		return "" + this.getClass().getName() + "\n" + denominator.toString() + "\n" + divider.toString() + "\n";
	}

	public boolean ensuredIsLess(Fraction in) {
		return (bigIntComp(in) == -1);
	}

	public boolean ensuredIsEqual(Fraction in) {
		return (bigIntComp(in) == 0);
	}

	public boolean ensuredIsGreater(Fraction in) {
		return (bigIntComp(in) == 1);
	}

	public abstract Fraction add(Fraction f1);

	public abstract T getDen();

	public abstract T getDiv();
	
	public abstract void parse(String[] in);

	public abstract Fraction copy();

	public boolean isLess(Fraction in) {
		return this.evaluate() < in.evaluate();
	}

	public boolean isGreater(Fraction in) {
		return this.evaluate() > in.evaluate();
	}
}