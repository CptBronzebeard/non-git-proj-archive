import ru.itmo.isddev.Ilin.fraction.*;
import ru.itmo.isddev.Ilin.polynomial.*;
import java.io.*;
import java.math.BigInteger;

public class TestPolynom {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			FractionGroup fg = new FractionGroup();
			FractionGroup fg1 = new FractionGroup();
			fg1.load("in1.txt");
			fg.load("in2.txt");
			Polynomial poly = new Polynomial(fg1);
			System.out.println(poly);
			Polynomial poly2 = new Polynomial(fg);
			System.out.println(poly2);
			Polynomial addRes = poly2.add(poly);
			System.out.println(addRes);
			System.out.println("Enter x value");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
       		 String line = reader.readLine(); 
			System.out.println(addRes.evaluate(Double.parseDouble(line)));
	}
	catch(Throwable t) {
		t.printStackTrace();
	}
	}
}