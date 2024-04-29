package laborator9.laborator9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO{

	@Override
	public String findById(int id) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select name from authors where id='" + id + "' ")){
			return rs.next() ? rs.getString(1) : null;
		}
	}

	@Override
	public Integer findByName(String name) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select id from authors where name='" + name + "' ")){
			 if (rs.next()) {
		            return rs.getInt("id");
		        } else {
		            // No matching row found
		        	System.out.println("Nu s-a gasit nici un autor cu numele " + name +" ");
		            return null;
		        }
		}
	}

	@Override
	public void create(Author author) throws SQLException {
		 Connection con = null;
		    PreparedStatement pstmt = null;
		    try {
		        con = Database.getConnection();
		        pstmt = con.prepareStatement("INSERT INTO public.authors (id, name) VALUES (?,?)");
		        pstmt.setInt(1, author.getId());
		        pstmt.setString(2, author.getName());
		        int rowsInserted = pstmt.executeUpdate();
		        System.out.println(rowsInserted + " row(s) inserted successfully.");
		        con.commit();
		    } catch (SQLException e) {
		        System.err.println("Error creating author: " + e.getMessage());
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
	public void delete(Author author) throws SQLException {
		Connection con = null;
	    Statement stmt = null;
	    try {
	        con = Database.getConnection();
	        stmt = con.createStatement();
	        stmt.executeUpdate("DELETE FROM public.authors WHERE id='" + author.getId() + "' ;"); 
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

	@Override
	public List<Author> getAll() throws SQLException {
		
		return null;
	}
	public void deleteAll() throws SQLException {
	    Connection con = null;
	    Statement stmt = null;
	    try {
	        con = Database.getConnection();
	        stmt = con.createStatement();
	        int rowsDeleted = stmt.executeUpdate("DELETE FROM public.authors WHERE id IS NOT NULL");
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
