package haloofblocks.projectarsenal.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author Autovw
 */
public class FireMode
{
    private final List<FireModes> fireModes;
    private int burstCount = 3;

    private FireMode(FireModes... fireModes)
    {
        this.fireModes = Arrays.asList(fireModes);
    }

    /**
     * Set fire modes for the fire mode selector to use.
     * The first fire mode added will also act as the "default" one.
     * Everytime the fire mode is switched it'll use the next available fire mode in the array.
     * <br/><b>Important:</b> This will overwrite Gun Mod's default fire mode behaviour.
     *
     * @param fireModes The fire modes to set
     * @return instance of {@link FireMode}
     */
    public static FireMode set(FireModes... fireModes)
    {
        return new FireMode(fireModes);
    }

    /**
     * Set burst count for weapon.
     * This number is set to <b>3</b> by default.
     *
     * @param rounds The rounds to be fired automatically between a burst
     * @return instance of {@link FireMode}
     */
    public FireMode setBurstCount(int rounds)
    {
        this.burstCount = rounds;
        return this;
    }

    /**
     * @return List of set fire modes
     */
    public List<FireModes> getFireModes()
    {
        return this.fireModes;
    }

    /**
     * @return Burst count for weapon
     */
    public int getBurstCount()
    {
        return this.burstCount;
    }
}
