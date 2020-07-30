package ru.itmo.isddev.alt.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import ru.itmo.isddev.alt.models.*;

public class Engine {
	private Catalog cat;
	public Engine(Catalog cat) {
		this.cat = cat;
	}
	 protected List<? extends Queryable> search(List<? extends Queryable> inList, Object inO, String propName) {
	 	List<Queryable> tmp = new ArrayList<Queryable>();
		for (Queryable i : inList)
		{
			if (i.getProp(propName).get().equals(inO))
				tmp.add(i);
		}
		return tmp;
	 }

	 public List<Track> searchTrack(Object inO, String propName) {

	 	return (List<Track>) search(cat.getTracks(), inO, propName);
	 }

	  public List<Album> searchAlbum(List<Album> inList, Object inO, String propName) {
	 	return (List<Album>) search(inList, inO, propName);
	 }

	  public List<Performer> searchPerformer(List<Performer> inList, Object inO, String propName) {
	 	return (List<Performer>) search(inList, inO, propName);
	 }

	  public List<Genre> searchGenre(List<Genre> inList, Object inO, String propName) {
	 	return (List<Genre>) search(inList, inO, propName);
	 }

	 public List<Mixtape> searchMixtape(List<Mixtape> inList, Object inO, String propName) {
	 	return (List<Mixtape>) search(inList, inO, propName);
	 }
}
