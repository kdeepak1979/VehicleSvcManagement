package com.innomind.vehiclesvc.mgmt.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {
	
	private static final String ERROR_WHILE_CREATING_SCHEMA = "Error while creating schema for ";
	@Autowired
	private MultiTenantConnectionProvider connectionProvider;
	
	public void initSchema(String tenantId) {
		Connection connection = null;
		try {
			connection = connectionProvider.getAnyConnection();
			connection.createStatement().execute(String.format("CREATE SCHEMA \"%s\";", tenantId));
			connection.createStatement().execute(String.format("SET search_path = \"%s\";", tenantId));

			executeDDL(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SchemaException(ERROR_WHILE_CREATING_SCHEMA+tenantId, e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SchemaException(ERROR_WHILE_CREATING_SCHEMA+tenantId, e);
		} finally {
			try {
				if (connection != null) {
					connectionProvider.releaseAnyConnection(connection);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param connection
	 * @throws Exception
	 */
	private void executeDDL(Connection connection) throws Exception {
		Resource resource = new ClassPathResource("migration.sql");
		URI uri = resource.getURI();  		
		FileReader fr = new FileReader(new File(uri));
		BufferedReader br = new BufferedReader(fr);

		String s = new String();
		StringBuffer sb = new StringBuffer();

		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		br.close();
		String[] inst = sb.toString().split(";");
		Statement st = connection.createStatement();
		for (int i = 0; i < inst.length; i++) {
			if (!inst[i].trim().equals("")) {
				System.out.println(">>" + inst);
				st.executeUpdate(inst[i]);
			}
		}
	}
	
}
