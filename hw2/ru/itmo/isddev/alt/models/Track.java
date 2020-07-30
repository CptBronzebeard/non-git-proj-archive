package ru.itmo.isddev.alt.models;
import java.util.function.*;
import java.util.*;

public class Track extends Queryable {

private Integer length;
private Album album;
public Track(String in, Album alIn, Integer inLen) {
	name = in;
	album = alIn;
	length = inLen;
	props = new LinkedHashMap<String,Supplier>();
	props.put("name", this::getName);
	props.put("length", this::getLen);
	props.put("author", this::getPerf);
	props.put("album", this::getAlb);
	props.put("genre", this::getGenre);
	album.addTrack(this);
}

public Integer getLen() {
	return length;
}

public Album getAlb() {
	return album;
}

public Performer getPerf() {
	return this.getAlb().getPerf();
}

public Genre getGenre() {
	return this.getAlb().getPerf().getGenre();
}

}