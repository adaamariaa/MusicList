package database.MusicDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Album;

public class AlbumDao {

	private final DatabaseMusic db = new DatabaseMusic();

	private static final String InsertAlbum = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?);";
	private static final String SelectAlbums = "SELECT AlbumId, Title FROM Album WHERE ArtistId = ?;";
	
	
	
	public List<Album> getAllAlbums(long artistId) {
		Connection connection = null;
    	PreparedStatement statement = null;
    	ResultSet results = null;
		List <Album> items = new ArrayList<>();
		
		try {
			connection = this.db.connect();
			statement = connection.prepareStatement(SelectAlbums);
			statement.setLong(1, artistId);
			results = statement.executeQuery();	
			while (results.next()) {
				long albumId = results.getLong("AlbumId");
				System.out.println(albumId);
	            String name = results.getString("Title");
	            System.out.println(name);
	            Album newAlbum = new Album(albumId, name);
	            items.add(newAlbum);
	    		
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.db.close(connection, statement, results);
		}
		return items;
	}



	public boolean addAlbum(Album newAlbum) {
	    	Connection connection = null;
	    	PreparedStatement statement = null;
	    	ResultSet ids = null;
	    	
			try {
				connection = this.db.connect();
				statement = connection.prepareStatement(InsertAlbum, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, newAlbum.getName());
				statement.setLong(2, newAlbum.getId());

				int rows = statement.executeUpdate();
	            if (rows == 1) {
	                ids = statement.getGeneratedKeys();
	                ids.next();
	                long generatedId = ids.getLong(1);
	                newAlbum.setId(generatedId);
	                return true;
		    		
	            }} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.db.close(connection, statement, ids);	
			}
	        return false;
			
		}
		
	}
	
