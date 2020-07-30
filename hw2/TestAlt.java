import ru.itmo.isddev.alt.models.*;
import ru.itmo.isddev.alt.controllers.*;
import java.util.*;
import java.util.function.*;
import java.time.Year;

public class TestAlt {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			Catalog cat = new Catalog();
			Genre gen = new Genre("Rock");
			cat.addGenre(gen);
			Performer perf = new Performer("Dandy", gen);
			Performer perf1 = new Performer("Mandy", gen);
			Year y = Year.of(2012);
			Year y1 = Year.of(2013);
			Album alb = new Album("Hit",perf,y);
			Track tr = new Track("DnB",alb,2);
			Track tr1 = new Track("AfG",alb,2);
			Album alb1 = new Album("Empt",perf,y1);
			Album alb2 = new Album("EmptM",perf1,y1);

			Engine searcher = new Engine(cat);

			List<Performer> allPerf = gen.getPerf();
			List<Album> albList = gen.getAlbums();

			List<Performer> lst = searcher.searchPerformer(gen.getPerf(),"Dandy","name");
			
			System.out.println(lst);
			List<Album> lst1 = searcher.searchAlbum(albList,perf1,"author");
			List<Track> lstTrck = searcher.searchTrack("AfG", "name");
			System.out.println(lstTrck);
	}
	catch(Throwable t) {
		System.out.println(t);
	}
	}
}