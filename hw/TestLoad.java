import ru.itmo.isddev.Ilin.fraction.*;
import java.math.BigInteger;

public class TestLoad {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			FractionGroup fg = new FractionGroup();
			FractionGroup fg1 = new FractionGroup();
			fg1.load("in1.txt");
			fg.load("in2.txt");
			fg.addGroup(fg1);
			fg.save("out.txt");
			System.out.println();
	}
	catch(Throwable t) {
		t.printStackTrace();
	}
	}
}