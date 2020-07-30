package ru.itmo.isddev.alt.models;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.*;

public class Genre extends Queryable {

private List<Performer> perfList;
private List<Genre> subGenList;

public Genre(String in) {
	name = in;
	perfList = new ArrayList<Performer>();
	subGenList = new ArrayList<Genre>();
	props = new LinkedHashMap<String,Supplier>();
	props.put("name", this::getName);
}

public List<Performer> getPerf() {
	List<Performer> tmp = new ArrayList<Performer>(perfList);
	for (Genre i : subGenList) {
		tmp.addAll(i.getPerf());
	}
	return tmp;
}

public List<Album> getAlbums() {
	List<Album> tmpAlb = new ArrayList<Album>();
	List<Performer> tmp = this.getPerf();
	for (Performer i : tmp) {
		tmpAlb.addAll(i.getAlbums());
	}
	return tmpAlb;
}

public List<Track> getTracks() {
	List<Track> tmpTrk = new ArrayList<Track>();
	List<Album> tmp = this.getAlbums();
	for (Album i : tmp) {
		tmpTrk.addAll(i.getTracks());
	}
	return tmpTrk;
}

public List<Genre> getSubGen() {
	return subGenList;
}

public Genre addPerf(Performer in) {
	perfList.add(in);
	return this;
}

public Genre addSubGen(Genre in) {
	subGenList.add(in);
	return this;
}

}