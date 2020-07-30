package ru.itmo.isddev.Ilin.models;
import ru.itmo.isddev.Ilin.exceptions.*;
import java.util.function.*;
import java.util.*;

public class Section {

private String name;
private Map<String,String> attributes;

public Section(String in) {
	name = in;
	attributes = new LinkedHashMap<>();
}

public String getName() {
	return name;
}

public Map<String,String> getAttrs() {
	return new LinkedHashMap<String,String>(attributes);
}

private <T> T getAttr(String in, Function<String, T> converter) throws NumberFormatException {
	String res = attributes.get(in);
	if (res == null) throw new NoSuchAttributeException(name, in);
	return converter.apply(res);
}

public Integer getIntAttr(String in) {
	Function<String, Integer> conv = Integer::decode;
	Integer res;
	try {
		res = getAttr(in, conv);
	}
	catch (NumberFormatException e) {
		throw new NumberFormatException("Attribute " + in + " doesn't represent an Integer value");
	}
	return res;
}

public Double getDoubleAttr(String in) {
	Function<String, Double> conv = Double::parseDouble;
	Double res;
	try {
		res = getAttr(in, conv);
	}
	catch (NumberFormatException e) {
		throw new NumberFormatException("Attribute " + in + " doesn't represent a Double value");
	}
	return res;
}

public String getStringAttr(String in) {
	return getAttr(in, Function.identity());
}

public Section put(String name, String val) {
	attributes.put(name,val);
	return this;
}

}
