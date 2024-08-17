package br.com.kassin.commands;

import br.com.kassin.route.RouteManager;
import br.com.kassin.utils.Message;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CreateRouteCommand implements CommandExecutor, TabExecutor {

    private final RouteManager routeManager = RouteManager.getInstance();

    public CreateRouteCommand() {
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
                    Message.Chat.sendMessage(player, "Uso correto: /route save <pos1> , <pos2>");
            }
        }

        if (args[0].equalsIgnoreCase("manager")) {
            switch (args[1].toLowerCase()) {
                case "create":
                    if (args[2].isEmpty()) {
                        Message.Chat.sendMessage(player, "Voce precisa dar um nome para a rota.");
                        Message.Chat.sendMessage(player, "Exemplo: /route manager <create> RotaExemplo");
                        return true;
                    }

                    final String name = args[2];

                    routeManager.create(player, name, routeManager.getPos1(), routeManager.getPos2());
                    break;

                case "init":
                    if (args[2].isEmpty()) {
                        Message.Chat.sendMessage(player, "Voce precisa digitar o nome de uma rota para iniciar.");
                        Message.Chat.sendMessage(player, "Exemplo: /route init <name>");
                        return true;
                    }

                    final String routeName = args[2];

                    Location startLocation = routeManager.getRoute(routeName).getPos1();
                    Location endLocation = routeManager.getRoute(routeName).getPos2();
                    routeManager.getRouteTask().start(startLocation, endLocation);
                    break;

                default:
                    Message.Chat.sendMessage(player, "Uso correto: /route manager <create> <name> , <init>¶");
            }
        }

        return false;
    }

    private static final List<String> MANAGER_SUBCOMMANDS = List.of("init", "create");
    private static final List<String> SAVE_SUBCOMMANDS = List.of("name", "pos1", "pos2");
    private static final List<String> INIT_SUBCOMMANDS = List.of("name");

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("manager", "save", "init");
        }

        return switch (args[0].toLowerCase()) {
            case "manager" -> filterList(MANAGER_SUBCOMMANDS, args[1]);
            case "save" -> filterList(SAVE_SUBCOMMANDS, args[1]);
            case "init" -> filterList(INIT_SUBCOMMANDS, args[1]);
            default -> new ArrayList<>();
        };
    }

    private List<String> filterList(List<String> list, String prefix) {
        return list.stream()
                .filter(s -> s.toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void incorrectCommand(final Player player) {
        Message.Chat.sendMessage(player, "&a&lUso correto:");
        Message.Chat.sendMessage(player, "&a/route manager <create> <name>",
                "&a/route manager <init> <name> ",
                "&a/route save <name> <pos1> ",
                "&a/route save <name> <pos2> ",
                "&a/route init <name> "
        );
    }

}
