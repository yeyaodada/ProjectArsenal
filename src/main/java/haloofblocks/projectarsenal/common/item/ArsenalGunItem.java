package haloofblocks.projectarsenal.common.item;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.client.KeyBindings;
import haloofblocks.projectarsenal.common.FireMode;
import haloofblocks.projectarsenal.common.FireModes;
import haloofblocks.projectarsenal.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * @author Autovw
 */
public class ArsenalGunItem extends GunItem
{
    private final boolean canColor;
    private final FireMode fireMode;

    public ArsenalGunItem(@Nullable FireMode fireMode, Properties properties, boolean canColor)
    {
        super(properties);
        this.fireMode = fireMode;
        this.canColor = canColor;
    }

    public ArsenalGunItem(Properties properties, boolean canColor)
    {
        this(null, properties, canColor);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, world, tooltip, flag);

        int index = tooltip.size() - 1;

        Gun modifiedGun = getModifiedGun(stack);
        CompoundTag nbt = stack.getTag();

        if (nbt != null)
        {
            // Reload Amount
            if (Config.CLIENT.gunTooltipInfo.showReloadAmount.get())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.reload_amount", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getReloadAmount()).withStyle(ChatFormatting.GRAY));
            }

            // Fire Rate
            if (Config.CLIENT.gunTooltipInfo.showFireRate.get())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.fire_rate", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getRate()).withStyle(ChatFormatting.GRAY));
            }

            // Fire Mode
            if (Config.CLIENT.gunTooltipInfo.showFireMode.get())
            {
                String key = "info.projectarsenal.fire_mode";
                tooltip.add(index++, Component.translatable(key, fireModeComponent(stack)).withStyle(ChatFormatting.GRAY));
            }

            // Projectile Speed
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpeed.get())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.projectile_speed", ChatFormatting.WHITE.toString() + modifiedGun.getProjectile().getSpeed()).withStyle(ChatFormatting.GRAY));
            }

            // Projectile Spread
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpread.get())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.projectile_spread", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getSpread()).withStyle(ChatFormatting.GRAY));
            }

            // Non-dyeable
            if (!canColor(stack) && Config.CLIENT.gunTooltipInfo.showNonDyeable.get())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.non_dyeable").withStyle(ChatFormatting.RED));
            }

            // Switching fire mode
            if (hasFireMode())
            {
                tooltip.add(index++, Component.translatable("info.projectarsenal.switch_fire_mode", KeyBindings.KEY_SELECT_FIRE_MODE.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(ChatFormatting.YELLOW));
            }
        }
    }

    public MutableComponent fireModeComponent(ItemStack stack)
    {
        String fireMode = null;

        if (hasFireMode())
        {
            switch (getSelectedFireMode(stack))
            {
                case SEMI_AUTOMATIC -> fireMode = "semi_auto";
                case FULL_AUTOMATIC -> fireMode = "full_auto";
                case BURST -> fireMode = "burst";
                case SAFETY -> fireMode = "safety";
            }
        }
        else
        {
            fireMode = getModifiedGun(stack).getGeneral().isAuto() ? "full_auto" : "semi_auto";
        }

        return Component.translatable("info.projectarsenal.fire_mode." + fireMode).withStyle(ChatFormatting.WHITE);
    }

    @Override
    public boolean canColor(ItemStack stack)
    {
        return this.canColor || Config.COMMON.experimental.forceGunDyeAbility.get();
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return super.isFoil(stack) && Config.CLIENT.enableGunEnchantmentGlint.get();
    }

    /**
     * @return If {@link FireMode} has been set for this gun
     */
    public boolean hasFireMode()
    {
        return this.fireMode != null;
    }

    public FireMode getFireMode()
    {
        return this.fireMode;
    }

    /**
     * Reads the weapon's selected fire mode from the <code>FireMode</code> tag.
     *
     * @param stack Weapon's item stack
     * @return Selected fire mode
     */
    public FireModes getSelectedFireMode(ItemStack stack)
    {
        CompoundTag tag = stack.getTag();
        FireModes selectedFireMode = getInitialFireMode();

        if (tag != null)
        {
            // write default fire mode into nbt if unset
            if (!tag.contains("FireMode", Tag.TAG_INT))
                tag.putInt("FireMode", getFireMode().getFireModes().indexOf(getInitialFireMode()));

            int fireModeTag = tag.getInt("FireMode");
            int size = getFireMode().getFireModes().size();
            if (fireModeTag < size)
            {
                selectedFireMode = getFireMode().getFireModes().get(fireModeTag);
            }
        }

        return selectedFireMode;
    }

    /**
     * Checks if the <code>Burst</code> tag has been set for the weapon
     * @param stack The weapon stack
     */
    public boolean isBurst(ItemStack stack)
    {
        CompoundTag tag  = stack.getTag();
        boolean burst = false;

        if (tag != null)
        {
            burst = tag.getBoolean("Burst");
        }

        return burst;
    }

    /**
     * @return The first available fire mode set using {@link FireMode#set(FireModes...)}.
     */
    public FireModes getInitialFireMode()
    {
        return getFireMode().getFireModes().get(0);
    }
}
