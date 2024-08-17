package br.com.kassin.commands;

import br.com.kassin.RouteManager;
import br.com.kassin.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class CreateRouteCommand implements CommandExecutor, TabExecutor {

    private final RouteManager routeManager;

    public CreateRouteCommand() {
        routeManager = new RouteManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length < 1) {
            incorrectCommand(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("save")) {
            switch (args[1].toLowerCase()) {
                case "pos1":
                    routeManager.setPos1(player.getLocation());
                    break;
                case "pos2":
                    routeManager.setPos2(player.getLocation());
                    break;

                default:
                    Message.Chat.sendMessage(player,"Uso correto: /position save <pos1> , <pos2>");
            }
        }

        if (args[0].equalsIgnoreCase("manager")) {
            switch (args[1].toLowerCase()) {
                case "create":
                    if (args[2].isEmpty()) {
                        Message.Chat.sendMessage(player,"Voce precisa dar um nome para a rota.");
                        Message.Chat.sendMessage(player,"Exemplo: /position manager <create> RotaExemplo");
                        return true;
                    }

                    String name = args[2];

                    routeManager.create(player,name, routeManager.getPos1(), routeManager.getPos2());
                    break;

                case "init":

                    break;

                default:
                    Message.Chat.sendMessage(player,"Uso correto: /position manager <create> <name> , <init>¶");
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    private void incorrectCommand(final Player player) {
        Message.Chat.sendMessage(player,"&a&lUso correto:");
        Message.Chat.sendMessage(player,"&a/position manager <create> <name>",
                "&a/position manager <init> <name> ",
                "&a/position save <name> <pos1> ",
                "&a/position save <name> <pos2> "
        );
    }

}
