package haloofblocks.projectarsenal.common;

/**
 * @author Autovw
 */
public interface IFireModeSelector
{
    FireModeSelector getFireModeSelector();

    /**
     * @return If {@link FireModeSelector} has been set for this gun
     */
    default boolean hasFireModeSelector()
    {
        return getFireModeSelector() != null;
    }

    /**
     * @return The first available fire mode set by {@link FireModeSelector#set(FireModes...)}
     */
    default FireModes getInitialFireMode()
    {
        return getFireModeSelector().getFireModes().get(0);
    }
}
