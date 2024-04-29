package laborator9.laborator9;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {    	
    	try {
    		var books = new BookDAOImpl();
        	var authors = new AuthorDAOImpl();
        	var genres = new GenreDAOImpl();
        	books.deleteAll();
        	authors.deleteAll();
        	genres.deleteAll();
    		
        	
    		authors.create(new Author(1, "William Shakespeare"));
    		
    		genres.create(new Genre(1, "Tragedy"));
    		
    		
    		books.create(new Book(1, 1597, "Romeo and Juliet", "William Shakespeare","English",360, "Tragedy"));
    		books.create(new Book(2, 1979, "The Hitchhiker's Guide to the Galaxy","Douglas Adams","English",240, "Science fiction, Comedy, Adventure"));
    		
    		System.out.println(books.getAll());
    		
    		
    	Database.getConnection().close();
    	 } catch (SQLException e) {
    	  System.err.println(e);
    	  //Database.rollback();
    	}
    }
}
