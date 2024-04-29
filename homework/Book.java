package laborator9.laborator9;

public class Book {
	private int id;
	private String author;
	private String title;
	private String language;
	private int publication_year;
	private int number_of_pages;
	private String genre;
	
	public Book(int id, int publication_year,String title, String author, String language, int number_of_pages, String genre) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.language = language;
		this.publication_year = publication_year;
		this.number_of_pages = number_of_pages;
		this.setGenre(genre);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPublication_year() {
		return publication_year;
	}
	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}
	public int getNumber_of_pages() {
		return number_of_pages;
	}
	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", language=" + language
				+ ", publication_year=" + publication_year + ", number_of_pages=" + number_of_pages + ", genre=" + genre + "]\n";
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
