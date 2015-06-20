package com.jdbc.route;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DataSourcesHolder implements DataSource {
    private Map<String, DataSource> datasources;

    public Map<String, DataSource> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, DataSource> datasources) {
        this.datasources = datasources;
    }

    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * i try to use transaction ,this method must impl .  at last,i fail 
     * 
     * 
     * could not provide transaction
     */
    public Connection getConnection() throws SQLException {
//        Connection connection = ContextHoder.getConnection();
//        if (connection != null) {
//            return connection;
//        }
//        RouteParameter routeParameter = ContextHoder.getPamameter();
//
//        DataSource dataSource = datasources.get(String.valueOf(routeParameter.ROUTE_PARAMETER));
//        connection = dataSource.getConnection();
//        return connection;
        return null;
    }

    public Connection getConnection(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
