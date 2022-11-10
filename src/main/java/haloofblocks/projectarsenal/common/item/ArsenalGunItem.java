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

        if (nbt != null) {
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

            // Automatic
            if (Config.CLIENT.gunTooltipInfo.showAutomatic.get())
            {
                tooltip.add(index++, new TranslationTextComponent("info.projectarsenal.automatic", TextFormatting.WHITE.toString() + modifiedGun.getGeneral().isAuto()).withStyle(TextFormatting.GRAY));
            }
        }
    }

    @Override
    public boolean canColor(ItemStack stack)
    {
        return this.canColor;
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return super.isFoil(stack) && Config.CLIENT.enableGunEnchantmentGlint.get();
    }
}
