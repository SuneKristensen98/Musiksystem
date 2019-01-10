package logic.domainClasses;

public enum Genre {
	ALTERNATIVE("Alternativ"), BLUES("Blues"), CLASSICAL("Klassisk"), CLASSICROCK("Klassisk rock"), COUNTRY("Country"), 
	ELECTRONICA("Elektronisk"), FOLK("Folkemusik"), HEAVYMETAL("Heavy metal"), HIPHOP("HipHop"), 
	INDIEROCK("Indie rock"), JAZZ("Jazz"), RAP("Rap"), RNB("RnB"), ROCK("Rock"), ROCKANDROLL("Rock n' roll"), 
	POP("Pop"), PUNK("Punk"), SOUL("Soul"), SOUNDTRACKS("Soundtracks"), OTHER("Andet");
    
	public String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }
}
