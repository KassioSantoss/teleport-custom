package br.com.kassin;

import br.com.kassin.commands.CreateRouteCommand;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class TeleportPlugin extends JavaPlugin {

    @Getter
    private static TeleportPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO,"PLUGIN DE TELEPORTE CUSTOM INICIALIZADO!");
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands() {
        getCommand("route").setExecutor(new CreateRouteCommand());
    }

}