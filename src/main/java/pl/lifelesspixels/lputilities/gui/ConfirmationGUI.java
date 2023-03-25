package pl.lifelesspixels.lputilities.gui;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.lifelesspixels.lpchestgui.data.ChestGUIClickActionBuilder;
import pl.lifelesspixels.lpchestgui.data.ChestGUIClickActionResult;
import pl.lifelesspixels.lpchestgui.gui.ChestGUI;
import pl.lifelesspixels.lputilities.heads.CustomHeads;

import java.util.List;
import java.util.Objects;

public class ConfirmationGUI extends ChestGUI {

    private static final ItemStack QUESTION_MARK_HEAD = CustomHeads.createHeadFromBase64(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM4ZWExZjUxZjI1M2ZmNTE0MmNhMTFhZTQ1MTkzYTRhZDhjM2FiNWU5YzZlZWM4YmE3YTRmY2I3YmFjNDAifX19");

    private static final ItemStack YES_HEAD = CustomHeads.createHeadFromBase64(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNlOWY0ZGJhZGRlMGY3MjdjNTgwM2Q3NWQ4YmIzNzhmYjlmY2I0YjYwZDMzYmVjMTkwOTJhM2EyZTdiMDdhOSJ9fX0=");

    private static final ItemStack CROSS_HEAD = CustomHeads.createHeadFromBase64(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA2MmQ4ZDcyZjU4OTFjNzFmYWIzMGQ1MmUwNDgxNzk1YjNkMmQzZDJlZDJmOGI5YjUxN2Q3ZDI4MjFlMzVkNiJ9fX0=");

    private Runnable cancelCallback;

    public ConfirmationGUI(String promptText, List<String> extendedPrompt, Runnable confirmCallback, Runnable cancelCallback) {
        super(3, "Confirmation");
        this.cancelCallback = cancelCallback;

        // set up the UI
        setDummyItem(13, createPromptItem(promptText, extendedPrompt));
        setAction(11, new ChestGUIClickActionBuilder().withLeftClickCallback(context -> {
            if(confirmCallback != null)
                confirmCallback.run();
            return ChestGUIClickActionResult.Close;
        }).build(), createConfirmItem(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
        setAction(15, new ChestGUIClickActionBuilder().withLeftClickCallback(context -> {
            if(cancelCallback != null)
                cancelCallback.run();
            return ChestGUIClickActionResult.Close;
        }).build(), createCancelItem(), Sound.ENTITY_ITEM_BREAK);
    }

    @Override
    public void onInventoryClosed(Player player) {
        if(cancelCallback != null)
            cancelCallback.run();
        player.playSound(player, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
    }

    private ItemStack createPromptItem(String promptText, List<String> extendedPrompt) {
        ItemStack promptItem = QUESTION_MARK_HEAD.clone();
        ItemMeta meta = Objects.requireNonNull(promptItem.getItemMeta());
        meta.setDisplayName(promptText);
        if(extendedPrompt != null) {
            meta.setLore(extendedPrompt);
        }

        promptItem.setItemMeta(meta);
        return promptItem;
    }

    private ItemStack createConfirmItem() {
        ItemStack confirmItem = YES_HEAD.clone();
        ItemMeta meta = Objects.requireNonNull(confirmItem.getItemMeta());
        meta.setDisplayName(ChatColor.GREEN + "Confirm");
        confirmItem.setItemMeta(meta);
        return confirmItem;
    }

    private ItemStack createCancelItem() {
        ItemStack cancelItem = CROSS_HEAD.clone();
        ItemMeta meta = Objects.requireNonNull(cancelItem.getItemMeta());
        meta.setDisplayName(ChatColor.RED + "Cancel");
        cancelItem.setItemMeta(meta);
        return cancelItem;
    }

}
