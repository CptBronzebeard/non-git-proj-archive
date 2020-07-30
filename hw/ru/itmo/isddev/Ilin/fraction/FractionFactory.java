package ru.itmo.isddev.Ilin.fraction;
import java.lang.reflect.*;
public class FractionFactory {
	public static <T> Fraction getFraction(T n1, T n2) throws Exception {
		return (Fraction)Class.forName("ru.itmo.isddev.Ilin.fraction."+n1.getClass().getSimpleName()+"Fraction")
							  .getConstructor(n1.getClass(), n1.getClass()).newInstance(n1, n2);
	}
	public static <T> Fraction getFraction(String name) throws Exception {
		return (Fraction)Class.forName(name).newInstance();
	}
}