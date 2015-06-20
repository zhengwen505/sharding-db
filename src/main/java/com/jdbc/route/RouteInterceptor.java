package com.jdbc.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * core class base on ciglib proxy
 * 
 * @author zhengwen
 * 
 */
public class RouteInterceptor implements MethodInterceptor {
    private Route route;
    private DataSourcesHolder dataSourcesHolder;

    public DataSourcesHolder getDataSourcesHolder() {
        return dataSourcesHolder;
    }

    public void setDataSourcesHolder(DataSourcesHolder dataSourcesHolder) {
        this.dataSourcesHolder = dataSourcesHolder;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * core method
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            return method.invoke(jdbcTemplate, invocation.getArguments());
        } catch (Throwable e) {
            // invocationTargetException would wrap exception in current method
            if (e instanceof InvocationTargetException) {
                InvocationTargetException invocationTargetException = (InvocationTargetException) e;
                throw invocationTargetException.getTargetException();
            }
            throw e;
        }
    }

    private JdbcTemplate getJdbcTemplate() {

        DataSource dataSource = route.route(dataSourcesHolder, ContextHoder.getRouteParameters());
        return new JdbcTemplate(dataSource);

    }

}
