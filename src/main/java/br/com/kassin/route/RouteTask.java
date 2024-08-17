package br.com.kassin.route;

import br.com.kassin.TeleportPlugin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RouteTask {

    private static RouteTask routeTask;

    private RouteTask() {
    }

    public static RouteTask getInstance() {
        if (routeTask == null) {
            routeTask = new RouteTask();
        }
        return routeTask;
    }

    public void start(final Location start,final Location end) {
        travelDiagonally(start, end);
    }

    private void travelDiagonally(final Location start,final Location end) {
        new BukkitRunnable() {
            final Vector direction = end.toVector().subtract(start.toVector()).normalize();
            final double distance = start.distance(end);
            double coveredDistance = 0;
            final Location currentLocation = start.clone();

            @Override
            public void run() {
                if (coveredDistance >= distance) {
                    cancel();
                }

                currentLocation.add(direction);
                coveredDistance += direction.length();

                currentLocation.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, currentLocation, 1);
            }
        }.runTaskTimer(TeleportPlugin.getInstance(), 0L, 5L);
    }

}

