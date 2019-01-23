package logic;

import java.util.List;

import data.DBCalls;
import domainClasses.Artist;
import domainClasses.Conductor;
import domainClasses.Genre;
import domainClasses.TableViewInfo;

public class Impl {
	DBCalls dbCalls = new DBCalls();

	public int createArtist(Artist artist) {
		return dbCalls.addArtist(artist);
	}

	public int createConductor(Conductor conductor) {
		return dbCalls.addConductor(conductor);
	}

	public List<TableViewInfo> searchMusic(String whereClause, Genre genreParameter, Boolean lp, Boolean cd,
			int albumMaybeId) {
		return dbCalls.getAllMusicWhere(whereClause, genreParameter, lp, cd, albumMaybeId);
	}

	public int searchArtist(String findArtistName) {
		return dbCalls.findArtist(findArtistName);
	}

	public int searchConductor(String findConductorName) {
		return dbCalls.findConductor(findConductorName);
	}
}