package br.com.kassin;

import br.com.kassin.route.Route;
import br.com.kassin.utils.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RouteManager {

    private final Map<String, Route> routeMap;
    private Location pos1;
    private Location pos2;

    public RouteManager() {
        routeMap = new HashMap<>();
    }

    public void create(final Player player,final String name,final Location pos1,final Location pos2) {
        if (routeMap.containsKey(name)) {
            Message.Chat.sendMessage(player,"Já existe uma rota com esse nome.");
            return;
        }
        routeMap.put(name, new Route(name, pos1, pos2));
    }

}
