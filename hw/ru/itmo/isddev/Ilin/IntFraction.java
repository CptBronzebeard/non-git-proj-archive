package ru.itmo.isddev.Ilin.fraction;

public class IntFraction extends Fraction<Integer> {

	public IntFraction(Integer inDen, Integer inDiv) {
		denominator = new Integer(inDen);
		divider = new Integer(inDiv);
	}


	public void simplify(){
			int tmp = (int)gcd(denominator, divider);
			denominator = denominator / tmp;
			divider = divider / tmp;
	}

	public Integer getDen(){
	return new Integer(denominator);
	}

	public Integer getDiv(){
		return new Integer(divider);
	} 
	
	public void setDen(Integer inDen){
		denominator = new Integer(inDen);
	}

	public void setDiv(Integer inDiv) {
		divider = new Integer(inDiv);
	}

	public boolean isLess(Fraction rhs) {
		//TODO: divide by gcd
		return denominator * rhs.getDiv().intValue() < rhs.getDen().intValue() * divider;
	}
}