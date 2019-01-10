package logic;

import java.util.List;

import data.DBCalls;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;

public class Impl {

	public boolean createArtist(Artist artist) {
		return DBCalls.addArtist(artist);
	}
	
	public boolean createConductor(Conductor conductor) {
		return DBCalls.addConductor(conductor);
	}

	public List searchMusic(String whereClause) {
		return DBCalls.getAllMusic(whereClause);
	}
}

//