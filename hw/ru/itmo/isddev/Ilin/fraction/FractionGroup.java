package ru.itmo.isddev.Ilin.fraction;
import ru.itmo.isddev.Ilin.util.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class FractionGroup {
	private int size = 0;
	private boolean modified;
	private Fraction max;
	private Fraction min;
	private LruCache<String, Integer> queries = new LruCache<String,Integer>(5);

	private List<Fraction> fractionContainer;

	public FractionGroup() {
		size = 0;
		modified = false;
		fractionContainer = new ArrayList<Fraction>();
	}



	public FractionGroup(FractionGroup in) {
		size = in.getSize();
		modified = true;
		fractionContainer = new ArrayList<Fraction>(in.getFractions());
	}

	public List<Fraction> getFractions() {
		//System.out.println("F1 " + fractionContainer);
		List<Fraction> tmpLst = new ArrayList<Fraction>(size);
		for (Fraction temp : fractionContainer) {
			tmpLst.add(temp.copy());
		}
		//System.out.println("F2 " + tmpLst);
		return tmpLst;
	}

	public Fraction min() {
		if (size == 0) return null;
		else return min;
	}

	public Fraction max() {
		if (size == 0) return null;
		else return max;
	}

	public FractionGroup load(String filename) {
		try {
			modified = true;
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			int sz = new Integer(line);
			for (int i = 0; i < sz; i++) {
				line = reader.readLine();
				Fraction tmpFrac = FractionFactory.getFraction(line);
				String[] tmp = new String[2];
				tmp[0] = reader.readLine();
				tmp[1] = reader.readLine();
				//System.out.println(line+"\n"+tmp[0]+"\n"+tmp[1]);
				tmpFrac.parse(tmp);
				this.add(tmpFrac);
			}
			reader.close();
		}
		catch (Exception e){e.printStackTrace();}
		return this;
	}

	public FractionGroup add(Fraction in) {
		modified = true;
		fractionContainer.add(in);
		if (size != 0) {
			if (in.isLess(min)) min = in;
			if (in.isLess(min)) min = in;
		}
		else {
			min = in;
			max = in;
		}
		size++;
		return this;
	}

	public int queryLess(Fraction in) {
		int cnt = 0;
		if (modified == false && queries.get("<"+in) != null) {
			return queries.get("<"+in);
		}
		else {
			modified = false;
			queries = new LruCache<String,Integer>(5);
			for (Fraction tmp : fractionContainer)
			{
				if (tmp.isLess(in)) cnt++;
			}
			queries.put("<"+in,cnt);
		}
		return cnt;
	}

	public int queryGreater(Fraction in) {
		int cnt = 0;
		if (modified == false && queries.get(">"+in) != null) {
			return queries.get(">"+in);
		}
		else {
			modified = false;
			queries = new LruCache<String,Integer>(5);
			for (Fraction tmp : fractionContainer)
			{
				if (tmp.isGreater(in)) cnt++;
			}
			queries.put(">"+in,cnt);
		}
		return cnt;
	}


	public int getSize(){
		return size;
	}

	public void save(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(""+size+"\n");
			for (Fraction temp : fractionContainer) {
				writer.write(temp.toSaveable());
			}
			writer.close();
		}
		catch (Exception e){System.out.println(e);}
	}

	public FractionGroup addGroup(FractionGroup in) {
		modified = true;
		int tmpSz = Math.max(size,in.getSize());
		List<Fraction> tempIn = in.getFractions();
		ArrayList<Fraction> tmp = new ArrayList<Fraction>(tmpSz);
		for (int i = 0; i < tmpSz; i++) {
			if (i < size)
			{
				if (i < in.getSize())
				{
					tmp.add(tempIn.get(i).add(fractionContainer.get(i)));
				}
				else {
					tmp.add(fractionContainer.get(i));
				}
			}
			else tmp.add(tempIn.get(i));
		}
		fractionContainer = tmp;
		size = tmpSz;
		return this;
	}
}