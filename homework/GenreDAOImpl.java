package laborator9.laborator9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GenreDAOImpl implements GenreDAO{
	
	@Override
	public String findById(int id) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select name from genres where id='" + id + "' ")){
			return rs.next() ? rs.getString(1) : null;
		}
	}

	@Override
	public Integer findByName(String name) throws SQLException {
		Connection con = Database.getConnection();
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select id from genres where name='" + name + "' ")){
			 if (rs.next()) {
		            return rs.getInt("id");
		        } else {
		            // No matching row found
		        	System.out.println("Nu s-a gasit nici un gen definit ca " + name +" ");
		            return null;
		        }
		}
	}

	@Override
	public List<Genre> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Genre genre) throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    try {
	        con = Database.getConnection();
	        pstmt = con.prepareStatement("INSERT INTO public.genres (id, name) VALUES (?,?)");
	        pstmt.setInt(1, genre.getId());
	        pstmt.setString(2, genre.getName());
	        int rowsInserted = pstmt.executeUpdate();
	        System.out.println(rowsInserted + " row(s) inserted successfully.");
	        con.commit();
	    } catch (SQLException e) {
	        System.err.println("Error creating genre: " + e.getMessage());
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
	public void delete(Genre genre) throws SQLException {
		Connection con = null;
	    Statement stmt = null;
	    try {
	        con = Database.getConnection();
	        stmt = con.createStatement();
	        stmt.executeUpdate("DELETE FROM public.genres WHERE id='" + genre.getId() + "' ;"); 
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
		        int rowsDeleted = stmt.executeUpdate("DELETE FROM public.genres WHERE id IS NOT NULL");
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
