package logic;

import java.util.List;

import data.DBCalls;
import logic.domainClasses.Artist;
import logic.domainClasses.Conductor;
import logic.domainClasses.TableViewInfo;

public class Impl {

	public boolean createArtist(Artist artist) {
		return new DBCalls().addArtist(artist);
	}
	
	public boolean createConductor(Conductor conductor) {
		return new DBCalls().addConductor(conductor);
	}

	public List<TableViewInfo> searchMusic(String whereClause) {
		return new DBCalls().getAllMusicWhere(whereClause);
	}
}

//