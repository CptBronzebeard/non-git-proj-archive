import ru.itmo.isddev.Ilin.fraction.*;
import java.math.BigInteger;

public class TestCntLess {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			//FractionGroup fg = new FractionGroup();
			FractionGroup fg1 = new FractionGroup();
			fg1.load("in1.txt");
			//fg.load("in2.txt");
			IntegerFraction f1 = new IntegerFraction(1,1);
			System.out.println(fg1.queryLess(f1));
			System.out.println(fg1.queryGreater(f1));
	}
	catch(Throwable t) {
		t.printStackTrace();
	}
	}
}