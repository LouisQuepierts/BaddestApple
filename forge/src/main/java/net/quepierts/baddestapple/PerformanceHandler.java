package net.quepierts.baddestapple;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.quepierts.baddestapple.client.ClientSettings;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PerformanceHandler {
    @SubscribeEvent
    public static void onTossEvent(final ItemTossEvent event) {
        if (ClientSettings.isEnableBadAppleStyle()) {
            return;
        }

        Player player = event.getPlayer();
        if (player.getId() != Minecraft.getInstance().player.getId()) {
            return;
        }

        if (!player.isShiftKeyDown()) {
            return;
        }

        if (player.getXRot() > -45f) {
            return;
        }

        if (event.getEntity().getItem().is(Items.APPLE)) {
            ClientSettings.setEnableBadAppleStyle(true);
        }
    }

    @SubscribeEvent
    public static void onAteApple(final LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity().getId() != Minecraft.getInstance().player.getId()) {
            return;
        }

        if (event.getItem().is(Items.APPLE)) {
            ClientSettings.setEnableBadAppleStyle(false);
        } else if (ClientSettings.isEnableBadAppleStyle()) {
            ClientSettings.random();
        }
    }

    @SubscribeEvent
    public static void onUsingStop(final LivingEntityUseItemEvent.Stop event) {
        if (event.getEntity().getId() != Minecraft.getInstance().player.getId()) {
            return;
        }

        if (!event.getItem().is(Items.APPLE) && ClientSettings.isEnableBadAppleStyle()) {
            ClientSettings.random();
        }
    }

    @SubscribeEvent
    public static void onLeftClick(final PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getEntity().getId() != Minecraft.getInstance().player.getId()) {
            return;
        }

        if (ClientSettings.isEnableBadAppleStyle()) {
            ClientSettings.random();
        }
    }
}
