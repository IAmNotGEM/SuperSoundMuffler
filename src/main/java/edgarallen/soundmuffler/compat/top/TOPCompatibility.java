package edgarallen.soundmuffler.compat.top;

import edgarallen.soundmuffler.compat.top.provider.SoundMufflerInfoProvider;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.apiimpl.TheOneProbeImp;

public class TOPCompatibility {
    public static void registerCompatibility() {
        TheOneProbeImp theOneProbeImp = TheOneProbe.theOneProbeImp;
        theOneProbeImp.registerProvider((IProbeInfoProvider)new SoundMufflerInfoProvider());
    }
}
