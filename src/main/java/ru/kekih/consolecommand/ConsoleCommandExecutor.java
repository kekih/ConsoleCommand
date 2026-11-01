package ru.kekih.consolecommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleCommandExecutor implements CommandExecutor, TabCompleter {

    private final ConsoleCommandPlugin plugin;

    public ConsoleCommandExecutor(ConsoleCommandPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("console.use")) {
            sender.sendMessage("§cУ вас нет прав на использование этой команды!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§eИспользование: §f/console <команда>");
            sender.sendMessage("§7Пример: §f/console say Привет!");
            return true;
        }

        String cmd = String.join(" ", args);

        // Логируем, кто вызвал
        String executorName = sender instanceof Player player ? player.getName() : "Консоль";
        plugin.getLogger().info(executorName + " выполнил через /console: " + cmd);

        // Выполняем от имени консоли
        boolean success = Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);

        if (success) {
            sender.sendMessage("§aКоманда выполнена от имени консоли: §f" + cmd);
        } else {
            sender.sendMessage("§cНе удалось выполнить команду: §f" + cmd);
            sender.sendMessage("§7(Возможно, команда не существует или вернула false)");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!sender.hasPermission("console.use")) {
            return Collections.emptyList();
        }

        // Простое таб-комплит по известным командам сервера (очень базовый)
        if (args.length == 1) {
            String partial = args[0].toLowerCase();
            return Bukkit.getCommandMap().getKnownCommands().keySet().stream()
                    .filter(cmd -> cmd.toLowerCase().startsWith(partial))
                    .sorted()
                    .limit(50)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
