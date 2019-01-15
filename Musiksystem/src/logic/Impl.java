package logic;

import java.util.List;

import data.DBCalls;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.Genre;
import logic.domainClasses.TableViewInfo;

public class Impl {

	public int createArtist(Artist artist) {
		return new DBCalls().addArtist(artist);
	}
	
	public int createConductor(Conductor conductor) {
		return new DBCalls().addConductor(conductor);
	}

	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd) {
		return new DBCalls().getAllMusicWhere(whereClause, genreParameter, lp, cd);
	}
}