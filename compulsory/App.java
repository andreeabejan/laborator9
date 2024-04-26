package laborator9.laborator9;

import java.sql.SQLException;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
         Database.getConnection();
         ArtistAO artistAO = new ArtistAO();
         	artistAO.deleteAll();
			artistAO.create("Maria Dopovici");
			artistAO.create("Christian Halls");
			artistAO.create("Some One");
			artistAO.create("Ana Crina");
			System.out.println(artistAO.findByName("Maria Dopovici"));
			System.out.println(artistAO.findByName("Christian Halls"));
			System.out.println(artistAO.findByName("Some One"));
			System.out.println(artistAO.findById(3));

    }
}
