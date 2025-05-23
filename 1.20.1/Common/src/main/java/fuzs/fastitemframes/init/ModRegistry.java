package fuzs.fastitemframes.init;

import fuzs.fastitemframes.FastItemFrames;
import fuzs.fastitemframes.capability.ItemFrameColorCapability;
import fuzs.fastitemframes.world.level.block.ItemFrameBlock;
import fuzs.fastitemframes.world.level.block.entity.ItemFrameBlockEntity;
import fuzs.puzzleslib.api.capability.v3.CapabilityController;
import fuzs.puzzleslib.api.capability.v3.data.CapabilityKey;
import fuzs.puzzleslib.api.capability.v3.data.SyncStrategy;
import fuzs.puzzleslib.api.init.v3.RegistryManager;
import fuzs.puzzleslib.api.init.v3.tags.BoundTagFactory;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModRegistry {
    public static final SoundType ITEM_FRAME_SOUND_TYPE = MutableSoundType.copyOf(SoundType.WOOD)
            .setBreakSound(SoundEvents.ITEM_FRAME_BREAK)
            .setPlaceSound(SoundEvents.ITEM_FRAME_PLACE);
    public static final SoundType GLOW_ITEM_FRAME_SOUND_TYPE = MutableSoundType.copyOf(SoundType.WOOD)
            .setBreakSound(SoundEvents.GLOW_ITEM_FRAME_BREAK)
            .setPlaceSound(SoundEvents.GLOW_ITEM_FRAME_PLACE);

    static final RegistryManager REGISTRY = RegistryManager.from(FastItemFrames.MOD_ID);
    public static final Holder.Reference<Block> ITEM_FRAME_BLOCK = REGISTRY.registerBlock("item_frame",
            () -> new ItemFrameBlock(Items.ITEM_FRAME,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .ignitedByLava()
                            .instabreak()
                            .pushReaction(PushReaction.DESTROY)
                            .sound(ITEM_FRAME_SOUND_TYPE)));
    public static final Holder.Reference<Block> GLOW_ITEM_FRAME_BLOCK = REGISTRY.registerBlock("glow_item_frame",
            () -> new ItemFrameBlock(Items.GLOW_ITEM_FRAME,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.SAND)
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .lightLevel((BlockState blockState) -> 1)
                            .ignitedByLava()
                            .instabreak()
                            .pushReaction(PushReaction.DESTROY)
                            .sound(GLOW_ITEM_FRAME_SOUND_TYPE)));
    public static final Holder.Reference<BlockEntityType<ItemFrameBlockEntity>> ITEM_FRAME_BLOCK_ENTITY = REGISTRY.registerBlockEntityType(
            "item_frame",
            () -> BlockEntityType.Builder.of(ItemFrameBlockEntity::new,
                    ITEM_FRAME_BLOCK.value(),
                    GLOW_ITEM_FRAME_BLOCK.value()));

    static final BoundTagFactory TAGS = BoundTagFactory.make(FastItemFrames.MOD_ID);
    public static final TagKey<Block> ITEM_FRAMES_BLOCK_TAG = TAGS.registerBlockTag("item_frames");
    public static final TagKey<EntityType<?>> ITEM_FRAMES_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag("item_frames");

    static final CapabilityController CAPABILITIES = CapabilityController.from(FastItemFrames.MOD_ID);
    public static final CapabilityKey<ItemFrame, ItemFrameColorCapability> ITEM_FRAME_COLOR_CAPABILITY = CAPABILITIES.registerEntityCapability(
            "item_frame_color",
            ItemFrameColorCapability.class,
            ItemFrameColorCapability::new,
            ItemFrame.class).setSyncStrategy(SyncStrategy.TRACKING);

    public static void touch() {

    }
}
