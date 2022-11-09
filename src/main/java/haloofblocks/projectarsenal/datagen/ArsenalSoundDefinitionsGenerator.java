package haloofblocks.projectarsenal.datagen;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.core.registry.ArsenalSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * Generator class for sound definitions.
 *
 * @author Autovw
 */
public class ArsenalSoundDefinitionsGenerator extends SoundDefinitionsProvider
{
    public ArsenalSoundDefinitionsGenerator(DataGenerator generator, ExistingFileHelper helper)
    {
        super(generator, ProjectArsenal.MOD_ID, helper);
    }

    @Override
    public void registerSounds()
    {
        // ===== REGULAR FIRE ===== //
        regularFire(ArsenalSounds.AA_TWELVE.get(), "item/aa12");
        regularFire(ArsenalSounds.AUTO_NINE.get(), "item/auto9");
        regularFire(ArsenalSounds.DESERT_EAGLE.get(), "item/deserteagle");
        regularFire(ArsenalSounds.DP_TWENTY_SEVEN.get(), "item/dp27");
        regularFire(ArsenalSounds.GOLDEN_HAWK.get(), "item/goldenhawk");
        regularFire(ArsenalSounds.M_TWO_FOUR_NINE.get(), "item/m249");
        regularFire(ArsenalSounds.MAXIM_NINE.get(), "item/maxim9");
        regularFire(ArsenalSounds.MOSSOU.get(), "item/mossou");
        regularFire(ArsenalSounds.MP_FIVE_A_FOUR.get(), "item/mp5a4");
        regularFire(ArsenalSounds.SHRIKE.get(), "item/shrike");
        regularFire(ArsenalSounds.P_TWO_FIFTY.get(), "item/p250");
        regularFire(ArsenalSounds.MARKXIX.get(), "item/markxix");
        regularFire(ArsenalSounds.GLOCK_TWENTY_ONE.get(), "item/glock21");

        // ===== SUPPRESSED FIRE ===== //
        suppressedFire(ArsenalSounds.SHRIKE_SUPPRESSION.get(), "item/shrike_suppression");

        // ===== ENCHANTED FIRE ===== //

        // ===== MISCELLANEOUS ===== //
    }

    /**
     * Helper method for adding sounds to the sound definitions json
     * @param soundEvent The sound event
     * @param subtitle The sound subtitle
     * @param soundLoc The location of the sound
     */
    private void addSound(SoundEvent soundEvent, String subtitle, String soundLoc)
    {
        add(soundEvent, definition().subtitle(subtitle).with(sound(new ResourceLocation(ProjectArsenal.MOD_ID, soundLoc))));
    }

    private void regularFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.regular_fire", soundLoc);
    }

    private void suppressedFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.suppressed_fire", soundLoc);
    }

    private void enchantedFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.enchanted_fire", soundLoc);
    }
}
