package database.MusicDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class ArtistDao {
	private final DatabaseMusic db = new DatabaseMusic();
	
	private static final String SelectAll = "SELECT ArtistId, Name FROM Artist ORDER BY Name ASC;";
	private static final String InsertArtist = "INSERT INTO Artist (Name) VALUES (?);";
	private static final String SelectAllOf = "SELECT ArtistId, Name FROM Artist WHERE Name LIKE ? ORDER BY Name ASC;";

	

	public List<Artist> getAllArtists(){
		Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet results = null;
		List <Artist> items = new ArrayList<>();
		
		try {
			connection = this.db.connect();
			statement = connection.prepareStatement(SelectAll);
			results = statement.executeQuery();	
			while (results.next()) {
				long id = results.getLong("ArtistId");
	            String name = results.getString("Name");
	            Artist newArtist = new Artist(id, name);
	            items.add(newArtist);
	    		
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.db.close(connection, statement, results);
		}
		return items;
	}
	

	

	public boolean addArtist(Artist newArtist) {
    	Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet ids = null;
    	
		try {
			connection = this.db.connect();
			statement = connection.prepareStatement(InsertArtist, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newArtist.getName());
			int rows = statement.executeUpdate();
            if (rows == 1) {
                ids = statement.getGeneratedKeys();
                ids.next();
                long generatedId = ids.getLong(1);
                newArtist.setId(generatedId);
                return true;
	    		
            }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.db.close(connection, statement, ids);	
		}
        return false;
		
	}
	
	public List<Artist> getAllArtistsContains(Artist SearchArtist) {
		Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet results = null;
		List <Artist> items = new ArrayList<>();
		
		try {
			connection = this.db.connect();
			statement = connection.prepareStatement(SelectAllOf);
			statement.setString(1, "%" + SearchArtist.getName() + "%");
			results = statement.executeQuery();	
			while (results.next()) {
				long id = results.getLong("ArtistId");
	            String name = results.getString("Name");
	            Artist newArtist = new Artist(id, name);
	            items.add(newArtist);
	    		
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.db.close(connection, statement, results);
		}
		return items;
	}
		
		
		
	}