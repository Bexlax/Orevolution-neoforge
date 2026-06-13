package net.bexla.orevolution.content.types.providers;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.bexla.orevolution.Orevolution;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

// Credits to Oreganized (Team Galena)
public abstract class ItemModelProvider extends BlueprintItemModelProvider {

    public ItemModelProvider(PackOutput output, ExistingFileHelper help) {
        super(output, Orevolution.MODID, help);
    }

    public void trapdoorItem(DeferredHolder<Block, ? extends Block> block) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_bottom"));
    }

    protected String blockName(Supplier<? extends Block> block) {
        return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
    }

    private ResourceLocation blockTexture(Supplier<? extends Block> block) {
        return key(block.get()).withPrefix("block/");
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block) {
        return blockFlat(block, blockName(block));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        return blockFlat(block, blockName(fullBlock));
    }

    public ItemModelBuilder generated(String name, ResourceLocation texture) {
        return withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", texture);
    }

    @SafeVarargs
    public final void trimArmorItem(DeferredItem<? extends ItemLike>... items) {
        for(DeferredItem<? extends ItemLike> item : items) {
            Item var7 = ((ItemLike)item.get()).asItem();
            if (var7 instanceof ArmorItem armor) {
                ResourceLocation location = BuiltInRegistries.ITEM.getKey(armor);
                ItemModelBuilder itemModel = this.withExistingParent(name((ItemLike)item.get()), "item/generated").texture("layer0", ResourceLocation.fromNamespaceAndPath(this.modid, "item/armor/" + name(armor)));
                int trimType = 1;

                for(String trim : new String[]{"quartz", "iron", "netherite", "redstone", "copper", "gold", "emerald", "diamond", "lapis", "amethyst"}) {
                    String var10002 = location.getNamespace();
                    String var10003 = location.getPath();
                    ResourceLocation name = ResourceLocation.fromNamespaceAndPath(var10002, "item/armor/" + var10003 + "_" + trim + "_trim");
                    itemModel.override().model(new ModelFile.UncheckedModelFile(name)).predicate(ResourceLocation.withDefaultNamespace("trim_type"), (float)((double)trimType / (double)10.0F));
                    var10002 = armor.getType().getName();
                    ResourceLocation texture = ResourceLocation.withDefaultNamespace("trims/items/" + var10002 + "_trim_" + trim);
                    this.existingFileHelper.trackGenerated(texture, PackType.CLIENT_RESOURCES, ".png", "textures");
                    ((ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(name.getPath(), "item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(this.modid, "item/armor/" + location.getPath()))).texture("layer1", texture);
                    ++trimType;
                }
            }
        }
    }

    public ItemModelBuilder generated(Supplier<? extends ItemLike> itemLike, ResourceLocation texture) {
        return generated(name(itemLike.get()), texture);
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, String name) {
        return generated(block, modLoc("block/" + name));
    }

    public ItemModelBuilder normalItem(Supplier<? extends Item> item, String subfolder) {
        return generated(item, itemTex(item.get(), subfolder));
    }

    public static ResourceLocation itemTex(ItemLike item, String subfolder) {
        ResourceLocation name = key(item);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "item/" + (subfolder + "/") + name.getPath());
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(name(item.get()), mcLoc("item/handheld"))
                .texture("layer0", itemTex(item.get(), "tool"));
    }

    public ItemModelBuilder bowItem(Supplier<? extends Item> item) {
        String name = name(item.get());

        ResourceLocation texture = itemTex(item.get(), "compat/archeryexp");

        ItemModelBuilder pull0 = withExistingParent(name + "_pulling_0", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/compat/archeryexp/" + name + "_pulling_0"));

        ItemModelBuilder pull1 = withExistingParent(name + "_pulling_1", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/compat/archeryexp/" + name + "_pulling_1"));

        ItemModelBuilder pull2 = withExistingParent(name + "_pulling_2", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/compat/archeryexp/" + name + "_pulling_2"));

        return withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", texture)
                .transforms()
                    .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                    .rotation(-80, 260, -40)
                    .translation(-1F, -2F, 2.5F)
                    .scale(0.9F)
                    .end()
                    .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                    .rotation(-80, -280, 40)
                    .translation(-1F, -2F, 2.5F)
                    .scale(0.9F)
                    .end()
                    .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                    .rotation(0, -90, 25)
                    .translation(1.13F, 3.2F, 1.13F)
                    .scale(0.68F)
                    .end()
                    .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                    .rotation(0, 90, -25)
                    .translation(1.13F, 3.2F, 1.13F)
                    .scale(0.68F)
                    .end()
                .end()
                .override()
                    .predicate(mcLoc("drawing"), 1.0F)
                    .model(pull0)
                .end()
                .override()
                    .predicate(mcLoc("drawing"), 1.0F)
                    .predicate(mcLoc("draw"), 0.65F)
                    .model(pull1)
                .end()
                .override()
                    .predicate(mcLoc("drawing"), 1.0F)
                    .predicate(mcLoc("draw"), 0.9F)
                    .model(pull2)
                .end();
    }

    public ItemModelBuilder shieldItem(Supplier<? extends Item> item, String type) {
        var texture = itemTex(item.get(), "compat/shieldexp");
        var name = name(item.get());

        var blockingModel = withExistingParent(name + "_blocking", modLoc("item/" + type + "_shield_blocking"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture);

        return withExistingParent(name, modLoc("item/" + type + "_shield"))
                .guiLight(BlockModel.GuiLight.FRONT)
                .texture("1", texture)
                .texture("particle", texture)
                .override()
                .predicate(ResourceLocation.withDefaultNamespace("blocking"), 1.0F)
                .model(blockingModel)
                .end();
    }

    public ItemModelBuilder wall(Supplier<? extends Block> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), key(fullBlock.get()).withPrefix("block/decorative/"));
    }
}