package com.jdbc.route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class HeartbeatThread implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatThread.class);
    private DataSourcesHolder dataSourcesHolder;

    public DataSourcesHolder getDataSourcesHolder() {
        return dataSourcesHolder;
    }

    public void setDataSourcesHolder(DataSourcesHolder dataSourcesHolder) {
        this.dataSourcesHolder = dataSourcesHolder;
    }

    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> datasources = dataSourcesHolder.getDatasources();
        final Iterator<Entry<String, DataSource>> iterator = datasources.entrySet().iterator();
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    while (iterator.hasNext()) {
                        Entry<String, DataSource> entry = iterator.next();
                        DataSource dataSource = entry.getValue();
                        Connection connection;
                        try {
                            connection = dataSource.getConnection();
                            closeConnection(connection);
                        } catch (SQLException e) {
                            // get connection exception ,after five seconds try again；
                            try {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e1) {
                                }
                                connection = dataSource.getConnection();
                                closeConnection(connection);
                            } catch (SQLException e1) {
                                // lose this connnetcion
                                iterator.remove();
                                logger.error("{} can't get connection", entry.getKey());
                            }
                        }
                    }
                    // after five minutes poll again
                    try {
                        Thread.sleep(5 * 1000 * 60);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();

    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("release connetion fail");
        }
    }
}
