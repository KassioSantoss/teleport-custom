package br.com.kassin.route;

import br.com.kassin.utils.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

@Getter
public final class RouteManager {

    private static RouteManager routeManager;
    private final Map<String, Route> routeMap;
    @Setter
    private Location pos1, pos2;

    private RouteManager() {
        routeMap = new HashMap<>();
    }

    public static RouteManager getInstance() {
        if (routeManager == null) {
            routeManager = new RouteManager();
        }
        return routeManager;
    }

    public void create(final Player player, final String name, final Location pos1, final Location pos2) {
        if (routeMap.containsKey(name)) {
            Message.Chat.sendMessage(player, "Já existe uma rota com esse nome.");
            return;
        }
        routeMap.put(name, new Route(name, pos1, pos2));
    }

    public Route getRoute(String name) {
        return routeMap.getOrDefault(name,null);
    }

    private Map<String, Route> getRouteMap() {
        return routeMap;
    }
}
