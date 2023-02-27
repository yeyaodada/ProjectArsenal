package haloofblocks.projectarsenal;

import haloofblocks.projectarsenal.common.item.ArsenalGunItem;
import haloofblocks.projectarsenal.core.registry.ArsenalItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

/**
 * Creative tab for Project Arsenal
 *
 * @author Autovw
 */
@Mod.EventBusSubscriber(modid = ProjectArsenal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectArsenalTab
{
    private ProjectArsenalTab()
    {
    }

    @SubscribeEvent
    public static void onRegisterCreativeModeTab(final CreativeModeTabEvent.Register event)
    {
        event.registerCreativeModeTab(new ResourceLocation(ProjectArsenal.MOD_ID), (builder) ->
        {
            builder.title(Component.translatable("itemGroup." + ProjectArsenal.MOD_ID));
            builder.icon(() ->
            {
                ItemStack stack = ArsenalItems.GOLDEN_HAWK.get().getDefaultInstance();
                stack.getOrCreateTag().putBoolean("IgnoreAmmo", true);
                return stack;
            });
            builder.displayItems((flags, entries, perms) ->
            {
                ArsenalItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach((entry) ->
                {
                    if (entry instanceof ArsenalGunItem gunItem)
                    {
                        ItemStack stack = gunItem.getDefaultInstance();
                        stack.getOrCreateTag().putInt("AmmoCount", gunItem.getGun().getGeneral().getMaxAmmo());
                        entries.accept(stack);
                    }
                    else
                    {
                        entries.accept(entry);
                    }
                });
            });
        });
    }
}
