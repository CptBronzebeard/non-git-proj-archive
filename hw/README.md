# Fraction
Fraction interface features generic denominator/divider types together with **addition, comparison, simplification, evaluation and GCD** operations.
Implementations of Integer and BigInteger fractions are provided.
Fractions can be grouped into FractionGroups, featuring QueryLess/QueryGreater operations, as well as saving to file.

# Polynomial
Polynomials feature **addition and evaluation** and can be constructed of FractionGroups acting as variable coefficients.

## Example


```java
//An example of constructing a BigInteger fraction
Fraction f1 = FractionFactory.getFraction(new BigInteger("2"), new BigInteger("3"));
Fraction f2 = FractionFactory.getFraction(new Integer("3"), new Integer("4"));
Fraction f3 = FractionFactory.getFraction(new Integer("5"), new Integer("6"));
//Fraction addition example
f1.add(f2);
//FractionGroup operations example
FractionGroup fg = new FractionGroup();
fg.add(f1);
fg.add(f2);
fg.add(f3);
fg.save("output.txt");

```
## output.txt for given example
```
3
ru.itmo.isddev.Ilin.fraction.BigIntegerFraction
2
3
ru.itmo.isddev.Ilin.fraction.IntegerFraction
3
4
ru.itmo.isddev.Ilin.fraction.IntegerFraction
8
6
```

# Task
Whole the project is developed as a [task](lab1.pdf) for Technologies of Programming class at the ITP department of ITMO University.