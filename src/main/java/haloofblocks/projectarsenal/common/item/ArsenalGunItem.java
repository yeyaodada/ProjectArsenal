package haloofblocks.projectarsenal.common.item;

import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.item.GunItem;
import haloofblocks.projectarsenal.config.Config;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
                ITextComponent semiAuto = new TranslationTextComponent(key + ".semi_auto").withStyle(TextFormatting.WHITE);
                ITextComponent fullAuto = new TranslationTextComponent(key + ".full_auto").withStyle(TextFormatting.WHITE);
                tooltip.add(index++, new TranslationTextComponent(key, modifiedGun.getGeneral().isAuto() ? fullAuto : semiAuto).withStyle(TextFormatting.GRAY));
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
