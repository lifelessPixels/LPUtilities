package pl.lifelesspixels.lputilities.heads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.lifelesspixels.lputilities.LPUtilities;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

public class CustomHeads {

    public static ItemStack createHeadFromBase64(String base64) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(base64.isEmpty())
            return head;

        SkullMeta skullMeta = Objects.requireNonNull((SkullMeta)(head.getItemMeta()));
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", base64));

        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (Exception e) {
            LPUtilities.getInstance().getLogger().warning("cannot create custom head, reflection error occurred");
            e.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
    }

}
