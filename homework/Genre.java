package laborator9.laborator9;

public class Genre {
	private String name;
	private int id;
	
	public Genre(int id, String name) {
		this.name = name;
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

}
