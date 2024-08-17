package br.com.kassin.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface Message {

    class Chat {
        public static void sendMessage(Player player, String... message) {
            for (String string : message) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
        }
    }

    class ActionBar {
        public static void send(Player player, String message) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
        }
    }

}
