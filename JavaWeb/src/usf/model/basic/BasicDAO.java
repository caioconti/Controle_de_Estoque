package usf.model.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BasicDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private String jdbcDriver;
	protected Connection jdbcConnection;
	
	public BasicDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
		this.jdbcDriver = "com.mysql.cj.jdbc.Driver";
	}

	public BasicDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
		this.jdbcDriver = jdbcDriver;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName(this.jdbcDriver);
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null || !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public abstract boolean insert(ModelBasic model) throws SQLException;

	public abstract List<ModelBasic> listAll() throws SQLException;

	public abstract boolean delete(ModelBasic model) throws SQLException;

	public abstract boolean update(ModelBasic model) throws SQLException;

	public abstract ModelBasic getRecord(int id) throws SQLException;
	
}
