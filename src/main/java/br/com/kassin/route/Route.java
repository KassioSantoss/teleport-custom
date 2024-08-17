package br.com.kassin.route;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public final class Route {

    private String name;
    private Location pos1;
    private Location pos2;

    public Route(String name, Location pos1, Location pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

}