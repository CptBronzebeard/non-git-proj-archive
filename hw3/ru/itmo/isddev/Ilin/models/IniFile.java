package ru.itmo.isddev.Ilin.models;
import ru.itmo.isddev.Ilin.exceptions.*;
import java.util.function.*;
import java.util.*;

public class IniFile {

private String name;
private Map<String,Section> sections;

public IniFile(String in) {
	name = in;
	sections = new LinkedHashMap<String,Section>();
}

public IniFile addSection(Section in) {
	sections.put(in.getName(),in);
	return this;
}

public Map<String,Section> getSections() {
	return new LinkedHashMap<String,Section>(sections);
}

public Section getSection(String secName) {
	Section sec = sections.get(secName);
	if (sec == null) throw new NoSuchSectionException(name,secName);
	return sec;
}

public Integer getInt(String secName, String attrName) {
	Section sec = this.getSection(secName);
	return sec.getIntAttr(attrName);
}

public Double getDouble(String secName, String attrName) {
	Section sec = this.getSection(secName);
	return sec.getDoubleAttr(attrName);
}

public String getString(String secName, String attrName) {
	Section sec = this.getSection(secName);
	return sec.getStringAttr(attrName);
}

}
