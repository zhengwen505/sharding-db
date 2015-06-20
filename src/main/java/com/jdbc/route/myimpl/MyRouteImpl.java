package com.jdbc.route.myimpl;

import javax.sql.DataSource;

import com.jdbc.route.DataSourcesHolder;
import com.jdbc.route.Route;
import com.jdbc.route.RouteParameter;

/**
 * you can impl Route by yourself 
 * 
 * @author zhengwen
 *
 */
public class MyRouteImpl implements Route {
    public DataSource route(DataSourcesHolder dataSourcesHolder, RouteParameter routeParameter) {
        int dbindex = processRouteParameter(routeParameter);
        return dataSourcesHolder.getDatasources().get(String.valueOf(dbindex));
    }

    private int processRouteParameter(RouteParameter parameter) {
        return (Integer) parameter.ROUTE_PARAMETER;
    }

}
