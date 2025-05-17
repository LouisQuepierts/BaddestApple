package net.quepierts.baddestapple;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.quepierts.baddestapple.client.ClientSettings;

@EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
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
