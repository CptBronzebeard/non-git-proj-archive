# IniParser
This project provides a library for **.ini** file structure storage and parsing.

## Example
Provided code snipper is taken from the file [Test.java](Test.java)
```java
IniParser p1 = new IniParser("test.ini");
IniFile f1 = p1.parse();
f1.getInt("ADC_DEV","Driver");
```