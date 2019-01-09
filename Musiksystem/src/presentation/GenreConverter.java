package presentation;

import javafx.util.StringConverter;
import logic.domainClasses.Genre;


public class GenreConverter extends StringConverter<Genre> {
	//TODO Mangler resterende genrer
	@Override
	public String toString(Genre genre) {
		switch (genre) {
		case ALTERNATIVE:
			return "Alternativ";
		default:
			return null;
		}
	}
	
	@Override
	public Genre fromString(String genre) {
		switch (genre) {
			case "Alternativ":
				return Genre.ALTERNATIVE;
			default:
				return null;
		}
	}
}
