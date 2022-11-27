package haloofblocks.projectarsenal.common.item;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Autovw
 */
public class ArsenalGunItem extends GunItem
{
    private final boolean canColor;

    public ArsenalGunItem(Properties properties, boolean canColor)
    {
        super(properties);
        this.canColor = canColor;
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
                tooltip.add(index++, new TranslatableComponent("info.projectarsenal.reload_amount", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getReloadAmount()).withStyle(ChatFormatting.GRAY));
            }

            // Fire Rate
            if (Config.CLIENT.gunTooltipInfo.showFireRate.get())
            {
                tooltip.add(index++, new TranslatableComponent("info.projectarsenal.fire_rate", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getRate()).withStyle(ChatFormatting.GRAY));
            }

            // Fire Mode
            if (Config.CLIENT.gunTooltipInfo.showFireMode.get())
            {
                String key = "info.projectarsenal.fire_mode";
                Component semiAuto = new TranslatableComponent(key + ".semi_auto").withStyle(ChatFormatting.WHITE);
                Component fullAuto = new TranslatableComponent(key + ".full_auto").withStyle(ChatFormatting.WHITE);
                tooltip.add(index++, new TranslatableComponent(key, modifiedGun.getGeneral().isAuto() ? fullAuto : semiAuto).withStyle(ChatFormatting.GRAY));
            }

            // Projectile Speed
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpeed.get())
            {
                tooltip.add(index++, new TranslatableComponent("info.projectarsenal.projectile_speed", ChatFormatting.WHITE.toString() + modifiedGun.getProjectile().getSpeed()).withStyle(ChatFormatting.GRAY));
            }

            // Projectile Spread
            if (Config.CLIENT.gunTooltipInfo.showProjectileSpread.get())
            {
                tooltip.add(index++, new TranslatableComponent("info.projectarsenal.projectile_spread", ChatFormatting.WHITE.toString() + modifiedGun.getGeneral().getSpread()).withStyle(ChatFormatting.GRAY));
            }

            // Non-dyeable
            if (!canColor(stack) && Config.CLIENT.gunTooltipInfo.showNonDyeable.get())
            {
                tooltip.add(index++, new TranslatableComponent("info.projectarsenal.non_dyeable").withStyle(ChatFormatting.RED));
            }
        }
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
}
