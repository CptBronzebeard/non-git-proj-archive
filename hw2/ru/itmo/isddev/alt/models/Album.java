package ru.itmo.isddev.alt.models;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;
import java.util.function.*;
import java.util.*;

public class Album extends Queryable {

private List<Track> trackList;
private Performer perf;
private Year releaseYear;

public Album(String in, Performer perfIn, Year yIn) {
	name = in;
	perf = perfIn;
	trackList = new ArrayList<Track>();
	releaseYear = yIn;
	props = new LinkedHashMap<String,Supplier>();
	props.put("name", this::getName);
	props.put("year", this::getYear);
	props.put("genre", this::getGenre);
	props.put("author", this::getPerf);
	perf.addAlbum(this);
}

public List<Track> getTracks() {
	return trackList;
}

public Year getYear(){
	return releaseYear;
}

public Performer getPerf() {
	return perf;
}

public Genre getGenre() {
	return this.getPerf().getGenre();
}

public Album addTrack(Track in) {
	trackList.add(in);
	return this;
}

}