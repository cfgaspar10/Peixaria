package report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import factory.JDBCFactory;

@WebServlet("/relatorioclientes")
public class ClienteReportServlet extends ReportServlet {


	private static final long serialVersionUID = -8954575764210907231L;

	@Override
	public String getJasperFile() {
		return "clientes.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParameters() {
		 HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		 param.put("NOME", String.class);
		 return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCFactory.getConnection();
	}


}