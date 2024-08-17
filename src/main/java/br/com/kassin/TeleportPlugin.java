package br.com.kassin;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportPlugin extends JavaPlugin {

    @Getter
    private static TeleportPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

}