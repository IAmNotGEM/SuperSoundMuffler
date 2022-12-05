package edgarallen.soundmuffler.compat.top.provider;

import edgarallen.soundmuffler.block.TileEntitySoundMuffler;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class SoundMufflerInfoProvider implements IProbeInfoProvider {

    public String getID() {
        return "supersoundmuffler:default";
    }

    public void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull EntityPlayer player, @Nonnull World world, @Nonnull IBlockState blockState, @Nonnull IProbeHitData data) {
        TileEntity te = world.getTileEntity(data.getPos());
        if (te instanceof TileEntitySoundMuffler) {
            TileEntitySoundMuffler tileEntity = (TileEntitySoundMuffler) te;
            String key = tileEntity.isWhiteList() ? "{*item.sound_muffler.tooltip.mode.white_list*}" : "{*item.sound_muffler.tooltip.mode.black_list*}";
            probeInfo.horizontal().text(key);
            probeInfo.horizontal().text("{*item.sound_muffler.tooltip.range*}" + tileEntity.getRange());
            List<ResourceLocation> sounds = tileEntity.getMuffledSounds();
            if(sounds.isEmpty()) {
                probeInfo.horizontal().text("{*item.sound_muffler.tooltip.sounds.count*}" + 0);
            } else {
                probeInfo.horizontal().text("{*item.sound_muffler.tooltip.sounds.count*}" + sounds.size());
                if (player.isSneaking()) {
                    sounds.stream().map(sound -> "{*item.sound_muffler.tooltip.sound*}" + sound.toString())
                            .collect(Collectors.toList())
                            .forEach(item->probeInfo.horizontal().text(item));
                }
            }
        }
    }
}
