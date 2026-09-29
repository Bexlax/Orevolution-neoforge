package net.bexla.orevolution.content.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record ReinforcedComponent(boolean showInTooltip) implements TooltipProvider {
    public static final Codec<ReinforcedComponent> CODEC = RecordCodecBuilder.create(
            p_337955_ -> p_337955_.group(Codec.BOOL.optionalFieldOf("show_in_tooltip", Boolean.TRUE).forGetter(ReinforcedComponent::showInTooltip))
                    .apply(p_337955_, ReinforcedComponent::new)
    );
    public static final StreamCodec<ByteBuf, ReinforcedComponent> STREAM_CODEC = ByteBufCodecs.BOOL.map(ReinforcedComponent::new, ReinforcedComponent::showInTooltip);

    private static final Component UPGRADE_TITLE = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.withDefaultNamespace("smithing_template.upgrade"))).withStyle(ChatFormatting.GRAY);

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        if (this.showInTooltip) {
            tooltipAdder.accept(UPGRADE_TITLE);
            tooltipAdder.accept(CommonComponents.space().append(Component.translatable("item.orevolution.tungsten_reinforced").withStyle(ChatFormatting.GREEN)));
        }
    }
}