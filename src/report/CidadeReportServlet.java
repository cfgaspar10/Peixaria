package report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import factory.JDBCFactory;

@WebServlet("/relatoriocidades")
public class CidadeReportServlet extends ReportServlet {

	private static final long serialVersionUID = 3336581594588250594L;

	@Override
	public String getJasperFile() {
		return "cidades.jasper";
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