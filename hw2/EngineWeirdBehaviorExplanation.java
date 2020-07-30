public class Engine <T> {
	 public List<Queryable> search(List<? extends Queryable> inList, T inO, String propName) {
	 	List<Queryable> tmp = new ArrayList<Queryable>();
		for (Queryable i : inList) 
		{
			if (i.getProp(propName).get().equals(inO))
				tmp.add(i);
		}
		return tmp;
	 }
} // With such implementation code List<Track> tmp = searcher.search(...) would compile and execute perfectly
public class Engine {
	 public List<Queryable> search(List<? extends Queryable> inList, Object inO, String propName) {
	 	List<Queryable> tmp = new ArrayList<Queryable>();
		for (Queryable i : inList) 
		{
			if (i.getProp(propName).get().equals(inO))
				tmp.add(i);
		}
		return tmp;
	 }
} // On the other hand, such implementation would cause compile-time incompatible types error
/* EXPLANATION:
In the first case code new Engine() would cause creation of a raw type object, which still behaves similarly to generics, and, therefore, only causes unchecked generic warning on implicit Queryable -> Track (or whichever Queryable subtype) downcasting
In the second case new Engine() creates a non-generic class object which causes expected compile-time types error