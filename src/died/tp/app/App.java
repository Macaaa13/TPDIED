package died.tp.app;

import java.sql.SQLException;

import died.tp.dao.ManejoSQL;

public class App {

	public static void main(String[] args) throws SQLException {

		ManejoSQL management = new ManejoSQL();
		management.crearTablas();

	}

}
