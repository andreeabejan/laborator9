package laborator9.laborator9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

	
	@Override
	public String findById(int id) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select name from books where id='" + id + "' ")){
			return rs.next() ? rs.getString(1) : null;
		}
	}

	@Override
	public Integer findByName(String name) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select id from books where name='" + name + "' ")){
			 if (rs.next()) {
		            return rs.getInt("id");
		        } else {
		            // No matching row found
		        	System.out.println("Nu s-a gasit nici o carte cu numele " + name +" ");
		            return null;
		        }
		}
	}

	@Override
	public List<Book> getAll() throws SQLException {
	    Connection con = Database.getConnection();
	    List<Book> lista = new ArrayList<>();
	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
	        while (rs.next()) {
	            int b_id = rs.getInt("id");
	            String b_title = rs.getString("title");
	            String b_lang = rs.getString("language");
	            int b_pg = rs.getInt("number_of_pages");
	            String b_gen = rs.getString("genre");
	            int b_year = rs.getInt("publication_year");
	            String b_author = rs.getString("author");
	            // Assuming Author information is not available in the result set
	            // Pass null for author parameter in Book constructor
	            lista.add(new Book(b_id, b_year, b_title, b_author, b_lang, b_pg, b_gen));
	        }
	    }
	    return lista;
	}


	@Override
	public void create(Book book) throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    try {
	        con = Database.getConnection();
	        pstmt = con.prepareStatement("INSERT INTO public.books (id, title, language, publication_year, number_of_pages, author, genre) VALUES (?,?,?,?,?,?,?)");
	        pstmt.setInt(1, book.getId());
	        pstmt.setString(2, book.getTitle());
	        pstmt.setString(3, book.getLanguage());
	        pstmt.setInt(4, book.getPublication_year());
	        pstmt.setInt(5, book.getNumber_of_pages());
	        pstmt.setString(6, book.getAuthor());
	        pstmt.setString(7, book.getGenre());
	      
	        int rowsInserted = pstmt.executeUpdate();
	        System.out.println(rowsInserted + " row(s) inserted successfully.");
	        con.commit();
	    } catch (SQLException e) {
	        System.err.println("Error creating book: " + e.getMessage());
	    } finally {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	}

	@Override
	public void delete(Book book) throws SQLException {
		Connection con = null;
	    Statement stmt = null;
	    try {
	        con = Database.getConnection();
	        stmt = con.createStatement();
	        stmt.executeUpdate("DELETE FROM public.genres WHERE id='" + book.getId() + "' ;"); 
	        con.commit();
	    } catch (SQLException e) {
	        System.err.println("Error deleting records: " + e.getMessage());
	        if (con != null) {
	            con.rollback(); // rollback the transaction in case of error
	        }
	    } finally {
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	}
	public void deleteAll() throws SQLException {
	    Connection con = null;
	    Statement stmt = null;
	    try {
	        con = Database.getConnection();
	        stmt = con.createStatement();
	        int rowsDeleted = stmt.executeUpdate("DELETE FROM public.books WHERE id IS NOT NULL");
	        System.out.println(rowsDeleted + " row(s) deleted.");
	        con.commit(); 
	    } catch (SQLException e) {
	        System.err.println("Error deleting records: " + e.getMessage());
	        if (con != null) {
	            con.rollback(); // rollback the transaction in case of error
	        }
	    } finally {
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	
}

}
