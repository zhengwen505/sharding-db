package com.jdbc.route;

import javax.sql.DataSource;

/**
 * route rule 。you impl it by yourself
 * 
 * @author zhengwen
 *
 */
public interface Route {
    public DataSource route(DataSourcesHolder dataSourcesHolder, RouteParameter routeParameter);

}
