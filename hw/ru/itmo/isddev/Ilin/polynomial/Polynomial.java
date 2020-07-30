package ru.itmo.isddev.Ilin.polynomial;
import ru.itmo.isddev.Ilin.fraction.*;
import java.util.*;

public class Polynomial {
	private FractionGroup fg;

	public Polynomial(FractionGroup in) {
		fg = new FractionGroup(in);
	}

	public Polynomial(Polynomial in) {
		fg = in.getFg();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		int i = 0;
		List<Fraction> tmpLst = fg.getFractions();
		for (Fraction temp : tmpLst) {
			if (i > 0) {
				buf.append(" + ");
				buf.append(temp);
				buf.append(" * x^"+i);
			}
			else buf.append(temp);
			i++;
			
		}
		return buf.toString();
	}

	public double evaluate(double x) {
		List<Fraction> tmpLst = fg.getFractions();
		double sum = 0;
		int i = 0;
		for (Fraction temp : tmpLst) {
			sum += temp.evaluate()*Math.pow(x,i);
			i++;
		}
		return sum;
	}

	public FractionGroup getFg() {
		return new FractionGroup(fg);
	}

	public Polynomial add(Polynomial in) {
		FractionGroup fgTmp = this.getFg();
		fgTmp.addGroup(in.getFg());
		return new Polynomial(fgTmp);
	}
}