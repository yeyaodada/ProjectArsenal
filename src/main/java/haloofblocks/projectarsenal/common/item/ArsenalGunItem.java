package haloofblocks.projectarsenal.common.item;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.client.KeyBindings;
import haloofblocks.projectarsenal.common.FireModeSelector;
import haloofblocks.projectarsenal.common.FireModes;
import haloofblocks.projectarsenal.common.IFireModeSelector;
import haloofblocks.projectarsenal.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

/**
 * @author Autovw
 */
public class ArsenalGunItem extends GunItem implements IFireModeSelector
{
    private final boolean canColor;
    private FireModeSelector fireModeSelector;

    public ArsenalGunItem(@Nullable FireModeSelector.Builder fireModeSelector, Properties properties, boolean canColor)
    {
        super(properties);
        this.canColor = canColor;

        if (fireModeSelector != null)
        {
            this.fireModeSelector = fireModeSelector.get();
        }
    }

    public ArsenalGunItem(Properties properties, boolean canColor)
    {
        this(null, properties, canColor);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, tooltip, flag);

        int index = tooltip.size() - 1;

        Gun modifiedGun = getModifiedGun(stack);
        CompoundNBT nbt = stack.getTag();

        if (nbt != null)
        {
            // Reload Amount
            if (Config.CLIENT.gunTooltipInfo.showReloadAmount.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.reload_amount", TextFormatting.WHITE.toString() + modifiedGun.getGeneral().getReloadAmount()).withStyle(TextFormatting.GRAY));
            }

            // Fire Rate
            if (Config.CLIENT.gunTooltipInfo.showFireRate.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.fire_rate", TextFormatting.WHITE.toString() + modifiedGun.getGeneral().getRate()).withStyle(TextFormatting.GRAY));
            }

            // Fire Mode
            if (Config.CLIENT.gunTooltipInfo.showFireMode.get())
            {
                String key = "info.projectarsenal.fire_mode";
                tooltip.add(index++, new TranslationTextComponent(key, fireModeComponent(stack)).withStyle(TextFormatting.GRAY));
            }

            // Projectile Speed
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpeed.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.projectile_speed", TextFormatting.WHITE.toString() + modifiedGun.getProjectile().getSpeed()).withStyle(TextFormatting.GRAY));
            }

            // Projectile Spread
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpread.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.projectile_spread", TextFormatting.WHITE.toString() + modifiedGun.getGeneral().getSpread()).withStyle(TextFormatting.GRAY));
            }

            // Non-dyeable
            if (!canColor(stack) && Config.CLIENT.gunTooltipInfo.showNonDyeable.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.non_dyeable").withStyle(TextFormatting.RED));
            }

            // Switching fire mode
            if (hasFireModeSelector())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.switch_fire_mode", KeyBindings.KEY_SELECT_FIRE_MODE.getTranslatedKeyMessage().getString().toUpperCase(Locale.ENGLISH)).withStyle(TextFormatting.YELLOW));
            }
        }
    }

    public IFormattableTextComponent fireModeComponent(ItemStack stack)
    {
        String fireMode = null;

        if (hasFireModeSelector())
        {
            switch (getSelectedFireMode(stack))
            {
                case SEMI_AUTOMATIC:
                    fireMode = "semi_auto";
                    break;
                case FULL_AUTOMATIC:
                    fireMode = "full_auto";
                    break;
                case BURST:
                    fireMode = "burst";
                    break;
                case SAFETY:
                    fireMode = "safety";
                    break;
            }
        }
        else
        {
            fireMode = getModifiedGun(stack).getGeneral().isAuto() ? "full_auto" : "semi_auto";
        }

        return new TranslationTextComponent("info.projectarsenal.fire_mode." + fireMode).withStyle(TextFormatting.WHITE);
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

    @Override
    public FireModeSelector getFireModeSelector()
    {
        return this.fireModeSelector;
    }

    /**
     * Reads the weapon's selected fire mode from the <code>FireMode</code> tag.
     *
     * @param stack Weapon's item stack
     * @return Selected fire mode
     */
    public FireModes getSelectedFireMode(ItemStack stack)
    {
        CompoundNBT tag = stack.getTag();
        FireModes selectedFireMode = getInitialFireMode();

        if (tag != null)
        {
            // write default fire mode into nbt if unset
            if (!tag.contains("FireMode", Constants.NBT.TAG_INT))
                tag.putInt("FireMode", getFireModeSelector().getFireModes().indexOf(getInitialFireMode()));

            int fireModeTag = tag.getInt("FireMode");
            int size = getFireModeSelector().getFireModes().size();
            if (fireModeTag < size)
            {
                selectedFireMode = getFireModeSelector().getFireModes().get(fireModeTag);
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
        CompoundNBT tag  = stack.getTag();
        boolean burst = false;

        if (tag != null)
        {
            burst = tag.getBoolean("Burst");
        }

        return burst;
    }
}
