package ru.itmo.isddev.alt.models;
import java.util.function.*;
import java.util.*;


public abstract class Queryable {
	protected String name;
	public String getName() {
		return name;
	}
	protected Map<String, Supplier> props;
	public Supplier getProp(String in) {
		Supplier tmp = props.get(in);
		if (tmp == null) throw (new IllegalArgumentException("Property " + in + "doesn't exist"));
		return tmp;
	}
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		Collection<Supplier> propVals = props.values();
		Iterator<String> iter = props.keySet().iterator();
		boolean first = true;
		for (Supplier i : propVals) {
			if (!first) tmp.append("; ");
			else first = false;
			tmp.append(iter.next().toString()+" = ");
			tmp.append("\""+i.get().toString()+"\"");
		}
		return tmp.toString();
	}
}