package ru.itmo.isddev.alt.models;
import java.util.*;

public class Catalog {
private List<Genre> genres;
private List<Mixtape> mixes;

public Catalog() {
	genres = new ArrayList<Genre>();
	mixes = new ArrayList<Mixtape>();
}

public Catalog addGenre(Genre in) {
	genres.add(in);
	return this;
}

public Catalog addMixtape(Mixtape in) {
	mixes.add(in);
	return this;
}

public List<Mixtape> getMixtapes() {
	return mixes;
}

public List<Genre> getGenres() {
	return genres;
}

public List<Performer> getPerf() {
	List<Performer> tmp = new ArrayList<Performer>();
	for (Genre i : genres) {
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
}