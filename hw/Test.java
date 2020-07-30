import ru.itmo.isddev.Ilin.fraction.*;
import java.math.BigInteger;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			Fraction f1 = FractionFactory.getFraction(new BigInteger("2"), new BigInteger("3"));
			Fraction f2 = FractionFactory.getFraction(new Integer("3"), new Integer("4"));
			Fraction f3 = FractionFactory.getFraction(new Integer("5"), new Integer("6"));
			FractionGroup fg = new FractionGroup();
			FractionGroup fg1 = new FractionGroup();
			fg.add(f1);
			fg.add(f2);
			fg.add(f3);
			fg1.add(f1);
			fg1.add(f3);
			fg1.save("in2.txt");
			fg.save("in1.txt");
	}
	catch(Throwable t) {
		System.out.println(t);
	}
	}
}