package tokyo.ramune.papertemplate.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.papertemplate.command.subcommand.HelpCommand;
import tokyo.ramune.papertemplate.command.subcommand.SubCommand;
import tokyo.ramune.papertemplate.util.Chat;

import java.util.ArrayList;


public class CommandHandler implements CommandExecutor {

    private static final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandHandler(@NonNull JavaPlugin plugin, @NonNull String command) {
        plugin.getCommand(command).setExecutor(this);
        plugin.getCommand(command).setTabCompleter(new TabCompleterHandler());

        registerSubCommand(new HelpCommand());
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            getSubCommand("help").onCommand(player, args);
            return true;
        }
        try {
            getSubCommand(args[0]).onCommand(player, args);
        } catch (Exception e) {
            Chat.sendMessage(player, ChatColor.RED + "サブコマンドが見つかりませんでした... もしくは、サブコマンド実行中にエラーが発生しました", true);
            getSubCommand("help").onCommand(player, args);
        }
        return true;
    }

    public static void registerSubCommand(SubCommand command) {
        subCommands.add(command);
    }

    @Nullable
    public static SubCommand getSubCommand(@NonNull String subCommand) {
        for (SubCommand command : subCommands) {
            if (command.getSubCommand().equals(subCommand)) {
                return command;
            }
        }
        return null;
    }

    public static SubCommand[] getSubCommands() {
        return subCommands.toArray(new SubCommand[0]);
    }
}
