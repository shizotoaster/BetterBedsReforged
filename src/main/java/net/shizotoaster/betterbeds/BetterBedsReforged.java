package net.shizotoaster.betterbeds;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Set;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterBedsReforged.MODID)
public class BetterBedsReforged {
    public static final String MODID = "betterbeds";
    public BetterBedsReforged() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(EventPriority.NORMAL, this::addbuiltinResourcePacks);
    }

    private void addbuiltinResourcePacks(AddPackFindersEvent e) {
        if (e.getPackType() == PackType.CLIENT_RESOURCES) {
            Path fancy_beds = ModList.get().getModFileById(MODID).getFile().findResource("resourcepacks/fancybeds");
            Path connected_beds = ModList.get().getModFileById(MODID).getFile().findResource("resourcepacks/connectedbeds");
            Path fancy_connected_beds = ModList.get().getModFileById(MODID).getFile().findResource("resourcepacks/fancyconnectedbeds");

            Pack fancy_beds_pack = Pack.readMetaAndCreate("builtin/pack_fancy_beds",
                    Component.literal("BetterBeds: Fancy Beds"), false,
                    (path) -> new PathPackResources(path, true, fancy_beds),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            Pack connected_beds_pack = Pack.readMetaAndCreate("builtin/pack_connected_beds",
                    Component.literal("BetterBeds: Connected Beds"), false,
                    (path) -> new PathPackResources(path, true, connected_beds),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
            Pack fancy_connected_beds_pack = Pack.readMetaAndCreate("builtin/pack_fancy_connected_beds",
                    Component.literal("BetterBeds: Fancy Connected Beds"), false,
                    (path) -> new PathPackResources(path, true, fancy_connected_beds),
                    PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);

            e.addRepositorySource((consumer) -> consumer.accept(fancy_beds_pack));
            e.addRepositorySource((consumer) -> consumer.accept(connected_beds_pack));
            e.addRepositorySource((consumer) -> consumer.accept(fancy_connected_beds_pack));
        }
    }
}
