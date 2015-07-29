package com.jdbc.route;

import java.util.concurrent.ConcurrentHashMap;

/**
 * use InheritableThreadLocal ;hold route parameter
 * 
 * @author zhengwen
 *
 */
public class ContextHoder {
    private static InheritableThreadLocal<ConcurrentHashMap<String, Object>> holder = new InheritableThreadLocal<ConcurrentHashMap<String, Object>>();
    private static String ROUTEPARA = "ROUTEPARA";
    public static RouteParameter getRouteParameters() {
        ConcurrentHashMap<String, Object> data = holder.get();
        if (data == null) {
            return null;
        }
        return (RouteParameter)data.get(ROUTEPARA);
    }

    public static void setRouteParameters(RouteParameter routeParameter) {
        ConcurrentHashMap<String, Object> data = holder.get();
        if (data == null) {
            data = new ConcurrentHashMap<String, Object>();
            holder.set(data);
        }
        data.put(ROUTEPARA, routeParameter);
    }

 
}
