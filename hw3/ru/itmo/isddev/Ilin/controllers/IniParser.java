package ru.itmo.isddev.Ilin.controllers;
import ru.itmo.isddev.Ilin.models.*;
import ru.itmo.isddev.Ilin.exceptions.*;
import java.util.function.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class IniParser {
private BufferedReader reader;
private IniFile ini;

public IniParser(String filenameIn) throws FileNotFoundException {
	reader = new BufferedReader(new FileReader(filenameIn));
	ini = new IniFile(filenameIn);
}

private String trimBrackets(String in) {
	in = in.replace("[", "");
	in = in.replace("]", "");
	return in;
}

public IniFile parse() throws IOException {
	Section secTmp = null;
	boolean secFlag=false;
	Pattern secPattern = Pattern.compile("^\\[[\\w]+\\]$");
	Pattern attrPattern = Pattern.compile("^([\\w]+ ?)=( ?([\\w]|\\/|\\.)+)$");
	Matcher secMatcher = null;
	Matcher attrMatcher = null;

	String tmp = reader.readLine();
	while (tmp != null) {

		tmp = (tmp.split(";"))[0];
		tmp = tmp.trim();
		secMatcher = secPattern.matcher(tmp);
		attrMatcher = attrPattern.matcher(tmp);
		if (!(tmp.equals(""))) {
			if (!secFlag) {
				if (!secMatcher.matches()) {
					throw new IncorrectFileStructureException("section");
				}
				else {
					secFlag = true;
					tmp = trimBrackets(tmp);
					secTmp = new Section(tmp);
				}
			}
			else {
				if (!secMatcher.matches()) {
					if (!attrMatcher.matches())
						{
					throw new IncorrectFileStructureException("attribute");
				}
					else {
						String[] tmpArr = tmp.split("=");
						tmpArr[0] = tmpArr[0].trim();
						tmpArr[1] = tmpArr[1].trim();
						secTmp.put(tmpArr[0],tmpArr[1]);
					}
				}
				else {
					ini.addSection(secTmp);
					tmp = trimBrackets(tmp);
					secTmp = new Section(tmp);
				}
			}
		}
		tmp = reader.readLine();
	}
	return ini;
}

}
