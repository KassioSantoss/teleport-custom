package br.com.kassin.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {

    public static class Chat {
        public static void sendMessage(String... message) {
            for (String string : message) {
                ChatColor.translateAlternateColorCodes('&', string);
            }
        }
    }

    public static class ActionBar {
        public static void send(Player player, String message) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
        }
    }

}
