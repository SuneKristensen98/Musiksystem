package logic.domainClasses;

public class Artist {

	private int id;
	private String name;
	
	public Artist(int id, String name) { 
		  this.id = id;
		  this.name = name;
		
		  //gggg
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
}
