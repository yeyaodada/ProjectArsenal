package haloofblocks.projectarsenal.common;

import java.util.Arrays;
import java.util.List;

/**
 * @author Autovw
 */
public class FireModeSelector
{
    private final List<FireModes> fireModes;
    private final int burstCount;

    private FireModeSelector(List<FireModes> fireModes, int burstCount)
    {
        this.fireModes = fireModes;
        this.burstCount = burstCount;
    }

    /**
     * Set fire modes for the fire mode selector to use.
     * The first fire mode added will also act as the "default" one.
     * Everytime the fire mode is switched it'll use the next available fire mode in the array.
     * <br/><b>Important:</b> This will overwrite Gun Mod's default fire mode behaviour.
     *
     * @param fireModes The fire modes to set
     * @return instance of {@link FireModeSelector}
     */
    public static Builder set(FireModes... fireModes)
    {
        return new Builder(fireModes);
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

    public static class Builder
    {
        private final List<FireModes> fireModes;
        private int burstCount = 3;

        /**
         * Use {@link FireModeSelector#set(FireModes...)}
         */
        private Builder(FireModes... fireModes)
        {
            this.fireModes = Arrays.asList(fireModes);
        }

        /**
         * Set burst count for weapon.
         * This number is set to <b>3</b> by default.
         *
         * @param rounds The rounds to be fired automatically between a burst
         * @return {@link Builder}
         */
        public Builder setBurstCount(int rounds)
        {
            this.burstCount = rounds;
            return this;
        }

        /**
         * @return A built instance of {@link FireModeSelector}
         */
        public FireModeSelector build()
        {
            return new FireModeSelector(this.fireModes, this.burstCount);
        }
    }
}
