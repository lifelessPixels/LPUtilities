package pl.lifelesspixels.lputilities.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.lifelesspixels.lputilities.LPUtilities;
import pl.lifelesspixels.lputilities.data.PlayerCommandRegistry;

import java.util.ArrayList;
import java.util.List;

public class AdvancedArgsParser {

    private String[] rawArgs;

    public static AdvancedArgsParser fromLastSentCommand(CommandSender sender) {
        PlayerCommandRegistry commandRegistry = LPUtilities.getInstance().getPlayerCommandRegistry();
        if(sender instanceof Player)
            return new AdvancedArgsParser(commandRegistry.getForPlayer((Player)(sender)));
        else return new AdvancedArgsParser(commandRegistry.getForConsole());
    }

    private AdvancedArgsParser(String commandText) {
        parse(commandText);
    }

    private void parse(String commandText) {
        List<String> args = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean inArgument = false;
        boolean inQuotedStringArgument = false;

        if(!commandText.startsWith("/"))
            return;
        commandText = commandText.substring(1);
        for(char c : commandText.toCharArray()) {
            if(!inArgument) {
                if(Character.isWhitespace(c))
                    continue;

                inArgument = true;
                if(c == '"') {
                    inQuotedStringArgument = true;
                    continue;
                }

                builder.append(c);
            } else {
                if((Character.isWhitespace(c) && !inQuotedStringArgument) || (c == '"' && inQuotedStringArgument)) {
                    inArgument = false;
                    inQuotedStringArgument = false;
                    args.add(builder.toString());
                    builder = new StringBuilder();
                    continue;
                }

                builder.append(c);
            }
        }

        if(!builder.isEmpty())
            args.add(builder.toString());

        rawArgs = new String[args.size() - 1];
        for(int index = 1; index < args.size(); index++)
            rawArgs[index - 1] = args.get(index);
    }

    public String[] getRawArgs() {
        return rawArgs;
    }

}
