package logic.domainClasses;

public enum Genre {
	ALTERNATIVE("Alternativ"), BLUES("Blues"), COUNTRY("Country"), ELECTRONICA("Elektronisk"), FOLK("Folkemusik"), 
	HEAVYMETAL("Heavy metal"), HIPHOP("HipHop"), INDIEROCK("Indie rock"), JAZZ("Jazz"), CLASSICAL("Klassisk"), 
	CLASSICROCK("Klassisk rock"), RAP("Rap"), RNB("RnB"), ROCK("Rock"), ROCKANDROLL("Rock n' roll"), 
	POP("Pop"), PUNK("Punk"), SOUL("Soul"), SOUNDTRACKS("Soundtracks"), OTHER("Andet");
    
	public String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }
    
    String[] stringGenre = {"Alternativ", "Blues", "Country", "Elektronisk", "Folkemusik", "Heavy metal"
            , "HipHop", "Indie rock", "Jazz", "Klassisk", "Klassisk rock", "Rap", "RnB"
            , "Rock", "Rock n' roll", "Pop", "Punk", "Soul", "Soundtracks", "Andet"};
     Genre[] genreGenre = {Genre.ALTERNATIVE, Genre.BLUES, Genre.COUNTRY, Genre.ELECTRONICA, Genre.FOLK
           , Genre.HEAVYMETAL, Genre.HIPHOP, Genre.INDIEROCK, Genre.JAZZ, Genre.CLASSICAL
           , Genre.CLASSICROCK, Genre.RAP, Genre.RNB, Genre.ROCK, Genre.ROCKANDROLL
           , Genre.POP, Genre.PUNK, Genre.SOUL, Genre.SOUNDTRACKS, Genre.OTHER};
    
    @Override
    public String toString() {
    	return stringValue;
    }
}
