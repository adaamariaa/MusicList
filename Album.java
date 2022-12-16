package model;

public class Album {
	
	private long id;
	private String name;
		
		public Album(String name) {
			this.name = name;
		}
		
		public Album(long id, String name) {
			this.id = id;	
			this.name = name;

		}
		
		public Album(long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
	        this.id = id;
	    }


	}