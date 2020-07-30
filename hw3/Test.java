import ru.itmo.isddev.Ilin.models.*;
import ru.itmo.isddev.Ilin.controllers.*;
import java.util.*;
import java.util.function.*;
import java.time.Year;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			IniParser p1 = new IniParser("test.ini");
			IniFile f1 = p1.parse();
			System.out.println(f1.getInt("ADC_DEV","Driver"));
			}
	catch(Throwable t) {
		t.printStackTrace();
	}
	}
}
