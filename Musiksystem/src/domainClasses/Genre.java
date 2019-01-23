package domainClasses;

public enum Genre {
	ALTERNATIVE("Alternativ"), BLUES("Blues"), COUNTRY("Country"), ELECTRONICA("Elektronisk"), FOLK("Folkemusik"), 
	HEAVYMETAL("Heavy metal"), HIPHOP("HipHop"), INDIEROCK("Indie rock"), JAZZ("Jazz"), CLASSICAL("Klassisk"), 
	CLASSICROCK("Klassisk rock"), RAP("Rap"), RNB("RnB"), ROCK("Rock"), ROCKANDROLL("Rock n' roll"), 
	POP("Pop"), PUNK("Punk"), SOUL("Soul"), SOUNDTRACKS("Soundtracks"), OTHER("Andet");
    
	public String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }
     
    @Override
    public String toString() {
    	return stringValue;
    }
    
}
