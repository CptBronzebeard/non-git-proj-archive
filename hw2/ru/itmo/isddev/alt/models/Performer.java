package ru.itmo.isddev.alt.models;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.*;

public class Performer extends Queryable {
private List<Album> albumList;
private Genre genre;
public Performer(String in, Genre genIn) {
	name = in;
	genre = genIn;
	albumList = new ArrayList<Album>();
	props = new HashMap<String,Supplier>();
	props.put("name", this::getName);
	props.put("genre", this::getGenre);
	genre.addPerf(this);
}

public List<Album> getAlbums() {
	return albumList;
}

public List<Track> getTracks() {
	List<Track> tmpTrk = new ArrayList<Track>();
	List<Album> tmp = this.getAlbums();
	for (Album i : tmp) {
		tmpTrk.addAll(i.getTracks());
	}
	return tmpTrk;
}

public Genre getGenre() {
	return genre;
}

public Performer addAlbum(Album in) {
	albumList.add(in);
	return this;
}

}