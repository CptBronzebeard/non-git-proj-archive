package ru.itmo.isddev.Ilin.fraction;

public class IntegerFraction extends Fraction<Integer> {

	public IntegerFraction(){
	}

	public IntegerFraction(Integer inDen, Integer inDiv) {
		denominator = inDen;
		divider = inDiv;
	}

	public IntegerFraction(IntegerFraction in) {
		denominator = in.getDen();
		divider = in.getDiv();
	}

	public void simplify(){
			int tmp = (int) gcd(denominator, divider);
			denominator = denominator / tmp;
			divider = divider / tmp;
	}

	public Integer getDen(){
	return denominator;
	}

	public Integer getDiv(){
		return divider;
	} 

	public void parse(String[] in) {
		denominator = new Integer(in[0]);
		divider = new Integer(in[1]);
	}

	public IntegerFraction copy() {
		return new IntegerFraction(denominator,divider);
	}

	public IntegerFraction add(Fraction in){
		if (!(in instanceof IntegerFraction)) 
			throw (new IllegalArgumentException(in.getClass().getSimpleName() + " cannot be added to " + this.getClass().getSimpleName()));
		denominator = denominator * (Integer)in.getDiv() + (Integer)in.getDen() * divider;
		divider = divider * (Integer)in.getDiv();
		return this;
	}

}