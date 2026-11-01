package ru.kekih.consolecommand;

import org.bukkit.plugin.java.JavaPlugin;

public final class ConsoleCommandPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        var command = getCommand("console");
        if (command != null) {
            command.setExecutor(new ConsoleCommandExecutor(this));
            command.setTabCompleter(new ConsoleCommandExecutor(this));
        } else {
            getLogger().severe("Команда /console не найдена в plugin.yml!");
            getServer().getPluginManager().disablePlugin(this);
        }

        getLogger().info("ConsoleCommand v" + getPluginMeta().getVersion() + " включён!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ConsoleCommand выключен.");
    }
}
