package laborator9.laborator9;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	String findById(int id) throws SQLException;
	Integer findByName(String name) throws SQLException;
	List<T> getAll() throws SQLException;
	void create(T t) throws SQLException;
	void delete(T t) throws SQLException;
	void deleteAll() throws SQLException;
}
