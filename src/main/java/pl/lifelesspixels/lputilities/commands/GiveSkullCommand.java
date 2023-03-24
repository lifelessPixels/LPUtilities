package pl.lifelesspixels.lputilities.commands;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.lifelesspixels.lputilities.LPUtilities;
import pl.lifelesspixels.lputilities.heads.CustomHeads;

import java.util.HashMap;

public class GiveSkullCommand implements CommandExecutor {

    private static final String GIVE_SKULL_PERMISSION = "lputilities.command.giveskull";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 2 || args.length == 3) {
            if(!sender.hasPermission(GIVE_SKULL_PERMISSION)) {
                CommandUtils.sendNoPermissionsMessage(sender, alias);
                return true;
            }

            Player player = LPUtilities.getInstance().getServer().getPlayer(args[0]);
            if(player == null) {
                CommandUtils.sendPlayerNotFound(sender, args[0]);
                return true;
            }

            ItemStack headItem = CustomHeads.createHeadFromBase64(args[1]);
            if(args.length == 3) {
                try { headItem.setAmount(Integer.parseInt(args[2])); }
                catch (NumberFormatException e) {
                    CommandUtils.sendCannotParseInteger(sender, args[2]);
                    return true;
                }
            }

            HashMap<Integer, ItemStack> didNotFit = player.getInventory().addItem(headItem);
            World world = player.getWorld();
            for(ItemStack item : didNotFit.values())
                world.dropItemNaturally(player.getLocation(), item);
            player.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);

            return true;
        }

        CommandUtils.sendUsage(sender, alias, "<player> <base64 skull data> [amount]");
        return true;
    }

}
