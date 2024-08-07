package br.com.kassin;

import br.com.kassin.utils.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class RouteManager {

    private final Map<String, Route> routeMap;

    public RouteManager() {
        routeMap = new HashMap<>();
    }

    public void create(String name, Location pos1, Location pos2) {
        if (routeMap.containsKey(name)) {
            Message.Chat.sendMessage("Já existe uma rota com esse nome.");
            return;
        }
        routeMap.put(name, new Route(name, pos1, pos2));
    }

}
