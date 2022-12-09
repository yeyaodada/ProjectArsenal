package haloofblocks.projectarsenal.client.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import haloofblocks.projectarsenal.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent;

/**
 * @author Autovw
 */
public class HudOverlay
{
    public static void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (!Config.CLIENT.hudOverlay.showHudOverlay.get())
            return;

        Minecraft mc = Minecraft.getInstance();

        if (!mc.isWindowActive() || mc.options.hideGui)
            return;

        if (mc.screen != null || mc.isPaused())
            return;

        PlayerEntity player = mc.player;
        if (player == null)
            return;

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.scale(1.0f, 1.0f, 1.0f);

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof ArsenalGunItem))
            return;

        ArsenalGunItem gunItem = (ArsenalGunItem) stack.getItem();

        Gun modifiedGun = gunItem.getModifiedGun(stack);
        CompoundNBT tag = stack.getTag();

        if (tag == null)
            return;

        int ammoCapacity = GunEnchantmentHelper.getAmmoCapacity(stack, modifiedGun);
        int ammoCount = tag.getInt("AmmoCount");

        TextFormatting ammoCountColor = TextFormatting.GREEN;

        if (ammoCount <= ammoCapacity / 2)
        {
            ammoCountColor = TextFormatting.YELLOW;
        }

        if (ammoCount <= ammoCapacity / 4)
        {
            ammoCountColor = TextFormatting.RED;
        }

        IFormattableTextComponent ammoCapacityComponent = new StringTextComponent(ammoCountColor.toString() + ammoCount + TextFormatting.RESET.toString() + "/" + ammoCapacity).withStyle(TextFormatting.WHITE);
        IFormattableTextComponent ammoComponent = new TranslationTextComponent("info.cgm.ammo",  ammoCapacityComponent).withStyle(TextFormatting.GRAY);

        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        float xPos = width * 0.72f;
        float yPos = height * 0.87f;

        if (Config.CLIENT.hudOverlay.overlayPosition.get().equals(OverlayPositions.RIGHT_TOP))
            yPos = height - yPos;

        // ammo counter
        mc.font.drawShadow(matrixStack, ammoComponent, xPos, yPos - 8, TextFormatting.BLACK.getId());

        // fire mode
        if (gunItem.hasFireMode())
        {
            IFormattableTextComponent component = new TranslationTextComponent("info.projectarsenal.fire_mode", gunItem.fireModeComponent(stack)).withStyle(TextFormatting.GRAY);
            mc.font.drawShadow(matrixStack, component, xPos, yPos + 8, TextFormatting.BLACK.getId());
        }

        matrixStack.popPose();
    }

    public enum OverlayPositions
    {
        RIGHT_BOTTOM,
        RIGHT_TOP
    }
}
