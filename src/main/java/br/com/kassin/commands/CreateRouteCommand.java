package br.com.kassin;

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

    private final Positions positions;
    private final RouteManager routeManager;

    public CreateRouteCommand() {
        positions = new Positions();
        routeManager = new RouteManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length < 1) {
            incorrectCommand();
            return true;
        }

        if (args[0].equalsIgnoreCase("save")) {
            switch (args[1].toLowerCase()) {

                case "pos1":
                    positions.setPos1(player.getLocation());
                    break;

                case "pos2":
                    positions.setPos2(player.getLocation());
                    break;

                default:
                    Message.Chat.sendMessage("Uso correto: /position save <pos1> , <pos2>");
            }
        }

        if (args[0].equalsIgnoreCase("manager")) {
            switch (args[1].toLowerCase()) {
                case "create":
                    try {
                        if (args[2].equalsIgnoreCase(""))


                            routeManager.create("");

                    } catch (Exception e) {

                    }

                    break;

                case "init":

                    break;

                default:
                    Message.Chat.sendMessage("Uso correto: /position manager <create> , <init>");
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    private void incorrectCommand() {
        Message.Chat.sendMessage("&a&lUso correto:");
        Message.Chat.sendMessage("&a/position manager <create> <name>",
                "&a/position manager <init> <name> ",
                "&a/position save <name> <pos1> ",
                "&a/position save <name> <pos2> "
        );
    }

}
