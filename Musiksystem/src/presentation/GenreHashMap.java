package presentation;

import java.util.HashMap;

import logic.domainClasses.Genre;

public class GenreHashMap {
	public HashMap<String, Genre> makeHashMap() {
		HashMap<String, Genre> map = new HashMap<String, Genre>();
		String[] stringGenre = { "Alternativ", "Blues", "Country", "Elektronisk", "Folkemusik", "Heavy metal", "HipHop",
				"Indie rock", "Jazz", "Klassisk", "Klassisk rock", "Rap", "RnB", "Rock", "Rock n' roll", "Pop", "Punk",
				"Soul", "Soundtracks", "Andet" };
		Genre[] genreGenre = { Genre.ALTERNATIVE, Genre.BLUES, Genre.COUNTRY, Genre.ELECTRONICA, Genre.FOLK,
				Genre.HEAVYMETAL, Genre.HIPHOP, Genre.INDIEROCK, Genre.JAZZ, Genre.CLASSICAL, Genre.CLASSICROCK,
				Genre.RAP, Genre.RNB, Genre.ROCK, Genre.ROCKANDROLL, Genre.POP, Genre.PUNK, Genre.SOUL,
				Genre.SOUNDTRACKS, Genre.OTHER };

		for (int i = 0; i < stringGenre.length; i++) {
			map.put(stringGenre[i], genreGenre[i]);
		}
		return map;
	}
}
