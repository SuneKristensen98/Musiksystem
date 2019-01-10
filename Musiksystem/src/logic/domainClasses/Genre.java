package logic.domainClasses;

public enum Genre {
	ALTERNATIVE("Alternativ"), BLUES("Blues"), CLASSICAL("Klassisk"), CLASSICROCK("Klassisk rock"), COUNTRY("Country"), ELECTRONICA("Elektronisk"), FOLK(""), HEAVYMETAL(""), HIPHOP(""), 
	INDIEROCK(""), JAZZ(""), RAP(""), RNB(""), ROCK(""), ROCKANDROLL(""), POP(""), PUNK(""), SOUL(""), SOUNDTRACKS(""), OTHER("");
    
	public String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }
}
