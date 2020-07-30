import ru.itmo.isddev.alt.models.*;
import ru.itmo.isddev.alt.controllers.*;
import java.util.*;
import java.util.function.*;
import java.time.Year;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			Genre gen = new Genre("Rock");
			Performer perf = new Performer("Dandy", gen);
			gen.addPerf(perf);
			Year y = Year.of(2012);
			Album alb = new Album("Hit",perf,y);
			perf.addAlbum(alb);
			Track tr = new Track("DnB",alb,2);
			alb.addTrack(tr);
			Track tr1 = new Track("AfG",alb,2);
			alb.addTrack(tr1);
			Album alb1 = new Album("Empt",perf,y);
			perf.addAlbum(alb1);
			/*BiFunction bf = new ByName();
			Query q = new QueryByNameF(gen.getPerf(),"Dandy",bf);
			List<Performer> tmp = (List<Performer>)q.execute();
			for (Performer i : tmp) {
				List<Album> tmp1 = i.getAlbums();
				Query q1 = new QueryByName(tmp1,"pezda");
				Album al1 = (Album)q1.execute().get(0);
				Query qt = new QueryTracksByLength(al1.getTracks(),2);
				List<Track> t = (List<Track>) qt.execute();
				for (Track j : t){
					System.out.println(j.getName()+" "+j.getLen());
				}
			}*/
			ByName searcher = new ByName();
			List<Performer> lst = searcher.apply(gen.getPerf(),"Dandy","name");
			System.out.println(lst);
			//System.out.println(pip.add(pop));
			//System.out.println(pip.getClass().getName());
			System.out.println(tmp);

			
	}
	catch(Throwable t) {
		System.out.println(t);
	}
	}
}