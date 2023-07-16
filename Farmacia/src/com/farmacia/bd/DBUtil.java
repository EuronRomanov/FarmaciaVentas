package com.farmacia.bd;

import javax.sql.DataSource;
import java.util.Properties;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.collections4.*;


public class DBUtil {
	private  DataSource dataSource = null;

	public DBUtil(){

		Properties properties = new Propiedades().cargarArchivoProperties();
		

		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://"+properties.getProperty("db.localhost")+":"+properties.getProperty("db.port")+"/"+properties.getProperty("db.database")+"?serverTimezone=UTC",
				properties.getProperty("db.user"),properties.getProperty("db.password"));

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
		config.setMaxTotal(25);
		config.setMaxIdle(10);
		config.setMinIdle(5);

		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);
		poolableConnectionFactory.setPool(connectionPool);

		dataSource = new PoolingDataSource<>(connectionPool);
		

	}

	public  Connection getConexion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
		//	e.printStackTrace();
			return null;
			
			
		}
	
	}
	
	
	/*public static DBUtil getInstancia() {
		if (dataSource!=null) {
			dataSource=dataSource.getConnection();
		}
	}*/
}
