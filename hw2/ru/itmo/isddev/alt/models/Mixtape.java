package ru.itmo.isddev.alt.models;
import java.util.function.*;
import java.util.*;

public class Mixtape extends Queryable {

private List<Track> trackList = new ArrayList<Track>();

public Mixtape(String in) {
	name = in;
	props = new LinkedHashMap<String,Supplier>();
	props.put("name", this::getName);
	//props.put("tracks", this::getTracks);
}

public List<Track> getTracks() {
	return trackList;
}

public Mixtape addTrack(Track in) {
	trackList.add(in);
	return this;
}

}