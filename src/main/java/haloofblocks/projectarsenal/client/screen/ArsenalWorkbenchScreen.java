package haloofblocks.projectarsenal.client.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.guns.client.screen.CheckBox;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.NetworkGunManager;
import com.mrcrayfish.guns.crafting.WorkbenchIngredient;
import com.mrcrayfish.guns.crafting.WorkbenchRecipe;
import com.mrcrayfish.guns.crafting.WorkbenchRecipes;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.IAmmo;
import com.mrcrayfish.guns.item.IColored;
import com.mrcrayfish.guns.item.attachment.IAttachment;
import com.mrcrayfish.guns.util.InventoryUtil;
import haloofblocks.projectarsenal.common.blockentity.ArsenalWorkbenchBlockEntity;
import haloofblocks.projectarsenal.common.container.ArsenalWorkbenchContainer;
import haloofblocks.projectarsenal.network.PacketHandler;
import haloofblocks.projectarsenal.network.message.MessageCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Autovw
 */
public class ArsenalWorkbenchScreen extends ContainerScreen<ArsenalWorkbenchContainer>
{
    private static final ResourceLocation GUI_BASE = new ResourceLocation("cgm:textures/gui/workbench.png");
    private static int maxItems = 6;
    private static int maxScrollPos = 97;
    private static boolean showAssemble = true;
    private static boolean showRemaining = false;

    private ArsenalWorkbenchScreen.Tab currentTab;
    private List<ArsenalWorkbenchScreen.Tab> tabs = new ArrayList<>();
    private List<ArsenalWorkbenchScreen.MaterialItem> materials;
    private List<ArsenalWorkbenchScreen.MaterialItem> filteredMaterials;
    private PlayerInventory playerInventory;
    private ArsenalWorkbenchBlockEntity workbench;
    private Button btnCraft;
    private TextFieldWidget searchBar;
    private CheckBox checkBoxAssemble;
    private CheckBox checkBoxMaterials;
    private ItemStack displayStack;
    private boolean ignoreTextInput;
    private int itemPos;
    private int scrollPos;

    public ArsenalWorkbenchScreen(ArsenalWorkbenchContainer container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
        this.playerInventory = playerInventory;
        this.workbench = container.getWorkbench();
        this.imageWidth = 275;
        this.imageHeight = 184;
        this.materials = new ArrayList<>();
        this.createTabs(WorkbenchRecipes.getAll(playerInventory.player.level));
        if (!this.tabs.isEmpty())
        {
            this.imageHeight += 28;
        }
        this.itemPos = 0;
        this.scrollPos = 0;
    }

    private void createTabs(NonNullList<WorkbenchRecipe> recipes)
    {
        List<WorkbenchRecipe> weapons = new ArrayList<>();
        List<WorkbenchRecipe> attachments = new ArrayList<>();
        List<WorkbenchRecipe> ammo = new ArrayList<>();
        List<WorkbenchRecipe> misc = new ArrayList<>();

        for (WorkbenchRecipe recipe : recipes)
        {
            ItemStack output = recipe.getItem();
            if (output.getItem() instanceof GunItem)
            {
                weapons.add(recipe);
            }
            else if (output.getItem() instanceof IAttachment)
            {
                attachments.add(recipe);
            }
            else if (this.isAmmo(output))
            {
                ammo.add(recipe);
            }
            else
            {
                misc.add(recipe);
            }
        }

        if (!weapons.isEmpty())
        {
            ItemStack icon = new ItemStack(ModItems.ASSAULT_RIFLE.get());
            icon.getOrCreateTag().putInt("AmmoCount", ModItems.ASSAULT_RIFLE.get().getGun().getGeneral().getMaxAmmo());
            this.tabs.add(new ArsenalWorkbenchScreen.Tab(icon, "weapons", weapons));
        }

        if (!attachments.isEmpty())
        {
            this.tabs.add(new ArsenalWorkbenchScreen.Tab(new ItemStack(ModItems.LONG_SCOPE.get()), "attachments", attachments));
        }

        if (!ammo.isEmpty())
        {
            this.tabs.add(new ArsenalWorkbenchScreen.Tab(new ItemStack(ModItems.SHELL.get()), "ammo", ammo));
        }

        if (!misc.isEmpty())
        {
            this.tabs.add(new ArsenalWorkbenchScreen.Tab(new ItemStack(Items.BARRIER), "misc", misc));
        }

        if (!this.tabs.isEmpty())
        {
            this.currentTab = this.tabs.get(0);
        }
    }

    private boolean isAmmo(ItemStack stack)
    {
        if (stack.getItem() instanceof IAmmo)
        {
            return true;
        }
        ResourceLocation id = stack.getItem().getRegistryName();
        Objects.requireNonNull(id);
        for (GunItem gunItem : NetworkGunManager.getClientRegisteredGuns())
        {
            if (id.equals(gunItem.getModifiedGun(stack).getProjectile().getItem()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init()
    {
        super.init();
        if (!this.tabs.isEmpty())
        {
            this.topPos += 28;
        }
        this.addButton(new Button(this.leftPos + 9, this.topPos + 18, 15, 20, new StringTextComponent("<"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            if (index - 1 < 0)
            {
                this.loadItem(this.currentTab.getRecipes().size() - 1);
            }
            else
            {
                this.loadItem(index - 1);
            }
        }));
        this.addButton(new Button(this.leftPos + 153, this.topPos + 18, 15, 20, new StringTextComponent(">"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            if (index + 1 >= this.currentTab.getRecipes().size())
            {
                this.loadItem(0);
            }
            else
            {
                this.loadItem(index + 1);
            }
        }));
        this.btnCraft = this.addButton(new Button(this.leftPos + 195, this.topPos + 16, 74, 20, new TranslationTextComponent("gui.cgm.workbench.assemble"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            WorkbenchRecipe recipe = this.currentTab.getRecipes().get(index);
            ResourceLocation registryName = recipe.getId();
            PacketHandler.getPlayChannel().sendToServer(new MessageCraft(registryName, this.workbench.getBlockPos()));
        }));
        this.btnCraft.active = false;

        this.searchBar = this.addWidget(new TextFieldWidget(this.font, this.leftPos + 174, this.topPos + 26, 95, 9, new TranslationTextComponent("gui.projectarsenal.arsenal_workbench.search_bar")));
        this.searchBar.setMaxLength(50);
        this.searchBar.setBordered(false);
        this.searchBar.setTextColor(16777215);

        this.checkBoxAssemble = this.addButton(new CheckBox(this.leftPos + 172, this.topPos + 40, new TranslationTextComponent("gui.projectarsenal.arsenal_workbench.show_assemble")));
        this.checkBoxAssemble.setToggled(showAssemble);

        this.checkBoxMaterials = this.addButton(new CheckBox(this.leftPos + 172, this.topPos + 51, new TranslationTextComponent("gui.cgm.workbench.show_remaining")));
        this.checkBoxMaterials.setToggled(showRemaining);

        this.loadItem(this.currentTab.getCurrentIndex());
    }

    @Override
    public void tick()
    {
        super.tick();

        for (ArsenalWorkbenchScreen.MaterialItem material : this.materials)
        {
            material.tick();
        }

        this.btnCraft.visible = this.checkBoxAssemble.isToggled();
        this.searchBar.visible = !this.checkBoxAssemble.isToggled();
        this.checkBoxMaterials.visible = this.checkBoxAssemble.isToggled();

        boolean canCraft = true;
        for (ArsenalWorkbenchScreen.MaterialItem material : this.materials)
        {
            if (!material.isEnabled())
            {
                canCraft = false;
                break;
            }
        }

        this.btnCraft.active = canCraft;

        this.updateColor();
    }

    private void updateColor()
    {
        if (this.currentTab != null)
        {
            ItemStack item = this.displayStack;
            if (IColored.isDyeable(item))
            {
                IColored colored = (IColored) item.getItem();
                if (!this.workbench.getItem(0).isEmpty())
                {
                    ItemStack dyeStack = this.workbench.getItem(0);
                    if (dyeStack.getItem() instanceof DyeItem)
                    {
                        DyeColor color = ((DyeItem) dyeStack.getItem()).getDyeColor();
                        float[] components = color.getTextureDiffuseColors();
                        int red = (int) (components[0] * 255F);
                        int green = (int) (components[1] * 255F);
                        int blue = (int) (components[2] * 255F);
                        colored.setColor(item, ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF)));
                    }
                    else
                    {
                        colored.removeColor(item);
                    }
                }
                else
                {
                    colored.removeColor(item);
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
    {
        boolean result = super.mouseClicked(mouseX, mouseY, mouseButton);
        showAssemble = this.checkBoxAssemble.isToggled();
        showRemaining = this.checkBoxMaterials.isToggled();

        for (int i = 0; i < this.tabs.size(); i++)
        {
            if (RenderUtil.isMouseWithin((int) mouseX, (int) mouseY, this.leftPos + 28 * i, this.topPos - 28, 28, 28))
            {
                this.currentTab = this.tabs.get(i);
                this.loadItem(this.currentTab.getCurrentIndex());
                this.minecraft.getSoundManager().play(SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
        }

        return result;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        boolean result = super.mouseScrolled(mouseX, mouseY, delta);

        this.filteredMaterials = this.getMaterials();
        int items = this.checkBoxAssemble.isToggled() ? this.filteredMaterials.size() : this.currentTab.getRecipes().size();

        if (items <= maxItems)
        {
            // TODO implement scrollbar properly
        }

        return result;
    }

    private boolean isInsideScrollbar(double mouseX, double mouseY)
    {
        // TODO implement scrollbar properly
        return mouseX >= this.leftPos && mouseY >= this.topPos;
    }

    private void loadItem(int index)
    {
        WorkbenchRecipe recipe = this.currentTab.getRecipes().get(index);
        this.displayStack = recipe.getItem().copy();
        this.updateColor();

        this.materials.clear();

        List<WorkbenchIngredient> ingredients = recipe.getMaterials();
        if (ingredients != null)
        {
            for (WorkbenchIngredient ingredient : ingredients)
            {
                ArsenalWorkbenchScreen.MaterialItem item = new ArsenalWorkbenchScreen.MaterialItem(ingredient);
                item.updateEnabledState();
                this.materials.add(item);
            }

            this.currentTab.setCurrentIndex(index);
        }
    }

    @Override
    protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType)
    {
        super.slotClicked(slot, mouseX, mouseY, clickType);
        if (this.checkBoxAssemble.isToggled())
        {
            this.searchBar.moveCursorToEnd();
            this.searchBar.setHighlightPos(0);
        }
        // TODO search functionality
    }

    @Override
    public boolean charTyped(char character, int modifiers)
    {
        if (this.ignoreTextInput)
        {
            return false;
        }
        else
        {
            String searchValue = this.searchBar.getValue();
            if (this.searchBar.charTyped(character, modifiers))
            {
                if (!Objects.equals(searchValue, this.searchBar.getValue()))
                {
                    //this.refreshSearchResults(); // TODO search functionality
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public boolean keyPressed(int key, int code, int modifiers)
    {
        boolean present = InputMappings.getKey(key, code).getNumericKeyValue().isPresent();
        if (present && this.checkHotbarKeyPressed(key, code))
        {
            this.ignoreTextInput = true;
            return true;
        }
        else
        {
            String searchValue = this.searchBar.getValue();
            if (this.searchBar.keyPressed(key, code, modifiers))
            {
                if (!Objects.equals(searchValue, this.searchBar.getValue()))
                {
                    //this.refreshSearchResults(); // TODO search functionality
                }

                return true;
            }
            else
            {
                return this.searchBar.isFocused() && this.searchBar.isVisible() && key != 256 || super.keyPressed(key, code, modifiers);
            }
        }
    }

    @Override
    public boolean keyReleased(int key, int code, int modifiers)
    {
        this.ignoreTextInput = false;
        return super.keyReleased(key, code, modifiers);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);

        int startX = this.leftPos;
        int startY = this.topPos;

        for (int i = 0; i < this.tabs.size(); i++)
        {
            if (RenderUtil.isMouseWithin(mouseX, mouseY, startX + 28 * i, startY - 28, 28, 28))
            {
                this.renderTooltip(matrixStack, new TranslationTextComponent(this.tabs.get(i).getTabKey()), mouseX, mouseY);
                return;
            }
        }

        int size = this.checkBoxAssemble.isToggled() ? this.filteredMaterials.size() : this.currentTab.getRecipes().size();
        for (int i = this.itemPos; i < size; i++)
        {
            int itemX = startX + 172;
            int itemY = startY + i * 19 + 63;
            if (RenderUtil.isMouseWithin(mouseX, mouseY, itemX, itemY, 80, 19))
            {

                if (this.checkBoxAssemble.isToggled())
                {
                    // render tooltip for assemble materials
                    ArsenalWorkbenchScreen.MaterialItem materialItem = this.filteredMaterials.get(i);
                    if (materialItem != ArsenalWorkbenchScreen.MaterialItem.EMPTY)
                    {
                        this.renderTooltip(matrixStack, materialItem.getDisplayStack(), mouseX, mouseY);
                        return;
                    }
                }
                else
                {
                    // render tooltip for guns in search
                    ItemStack resultItem = this.currentTab.getRecipes().get(i).getResultItem();
                    if (resultItem != null)
                    {
                        this.renderTooltip(matrixStack, resultItem, mouseX, mouseY);
                        return;
                    }
                }
            }
        }

        if (RenderUtil.isMouseWithin(mouseX, mouseY, startX + 8, startY + 38, 160, 48))
        {
            this.renderTooltip(matrixStack, this.displayStack, mouseX, mouseY);
        }

        if (this.searchBar.visible)
        {
            this.searchBar.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        int offset = this.tabs.isEmpty() ? 0 : 28;
        this.font.draw(matrixStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY - 28 + offset, 4210752);
        this.font.draw(matrixStack, this.playerInventory.getDisplayName(), (float)this.inventoryLabelX, (float)this.inventoryLabelY - 9 + offset, 4210752);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        /* Fixes partial ticks to use percentage from 0 to 1 */
        partialTicks = Minecraft.getInstance().getFrameTime();

        int startX = this.leftPos;
        int startY = this.topPos;

        RenderSystem.enableBlend();

        /* Draw unselected tabs */
        for (int i = 0; i < this.tabs.size(); i++)
        {
            ArsenalWorkbenchScreen.Tab tab = this.tabs.get(i);
            if (tab != this.currentTab)
            {
                this.minecraft.getTextureManager().bind(GUI_BASE);
                this.blit(matrixStack, startX + 28 * i, startY - 28, 80, 184, 28, 32);
                Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(tab.getIcon(), startX + 28 * i + 6, startY - 28 + 8);
                Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, tab.getIcon(), startX + 28 * i + 6, startY - 28 + 8, null);
            }
        }

        this.minecraft.getTextureManager().bind(GUI_BASE);
        this.blit(matrixStack, startX, startY, 0, 0, 173, 184);
        blit(matrixStack, startX + 173, startY, 78, 184, 173, 0, 1, 184, 256, 256);
        this.blit(matrixStack, startX + 251, startY, 174, 0, 24, 184);


        if (!this.checkBoxAssemble.isToggled())
        {
            // draw search bar background
            this.blit(matrixStack, startX + 172, startY + 16, 198, 0, 1, 20);
            blit(matrixStack, startX + 173, startY + 16, 95, 184, 199, 0, 1, 184, 256, 256);
            this.blit(matrixStack, startX + 268, startY + 16, 217, 0, 1, 20);
        }
        else
        {
            // draw dye input background
            this.blit(matrixStack, startX + 172, startY + 16, 198, 0, 20, 20);
        }

        // Draw selected tab
        if (this.currentTab != null)
        {
            int i = this.tabs.indexOf(this.currentTab);
            int u = i == 0 ? 80 : 108;
            this.minecraft.getTextureManager().bind(GUI_BASE);
            this.blit(matrixStack, startX + 28 * i, startY - 28, u, 214, 28, 32);
            Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(this.currentTab.getIcon(), startX + 28 * i + 6, startY - 28 + 8);
            Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, this.currentTab.getIcon(), startX + 28 * i + 6, startY - 28 + 8, null);
        }

        this.minecraft.getTextureManager().bind(GUI_BASE);

        // dye icon
        if (this.workbench.getItem(0).isEmpty() && this.checkBoxAssemble.isToggled())
        {
            this.blit(matrixStack, startX + 174, startY + 18, 165, 199, 16, 16);
        }

        ItemStack currentItem = this.displayStack;
        StringBuilder builder = new StringBuilder(currentItem.getHoverName().getString());
        if (currentItem.getCount() > 1)
        {
            builder.append(TextFormatting.GOLD);
            builder.append(TextFormatting.BOLD);
            builder.append(" x ");
            builder.append(currentItem.getCount());
        }
        this.drawCenteredString(matrixStack, this.font, builder.toString(), startX + 88, startY + 22, Color.WHITE.getRGB());

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderUtil.scissor(startX + 8, startY + 17, 160, 70);

        RenderSystem.pushMatrix();
        {
            RenderSystem.translatef(startX + 88, startY + 60, 100);
            RenderSystem.scalef(50F, -50F, 50F);
            RenderSystem.rotatef(5F, 1, 0, 0);
            RenderSystem.rotatef(Minecraft.getInstance().player.tickCount + partialTicks, 0, 1, 0);

            RenderSystem.enableRescaleNormal();
            RenderSystem.enableAlphaTest();
            RenderSystem.defaultAlphaFunc();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            IRenderTypeBuffer.Impl buffer = this.minecraft.renderBuffers().bufferSource();
            Minecraft.getInstance().getItemRenderer().render(currentItem, ItemCameraTransforms.TransformType.FIXED, false, matrixStack, buffer, 15728880, OverlayTexture.NO_OVERLAY, RenderUtil.getModel(currentItem));
            buffer.endBatch();

            RenderSystem.disableAlphaTest();
            RenderSystem.disableRescaleNormal();
        }
        RenderSystem.popMatrix();

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        this.minecraft.getTextureManager().bind(GUI_BASE);

        this.filteredMaterials = this.getMaterials();
        int items = this.checkBoxAssemble.isToggled() ? this.filteredMaterials.size() : this.currentTab.getRecipes().size();

        if (items <= maxItems)
        {
            // draw disabled slide
            this.blit(matrixStack, startX + 179 + 77, startY + 64, 176, 184, 12, 15);
        }
        else
        {
            // draw enabled slide
            this.blit(matrixStack, startX + 179 + 77, startY + 64 + this.scrollPos, 164, 184, 12, 15);
        }
        // TODO add slide functionality

        // draw items
        for (int i = this.itemPos; i < items; i++)
        {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.minecraft.getTextureManager().bind(GUI_BASE);

            if (i - this.itemPos >= maxItems)
                break;

            if (this.checkBoxAssemble.isToggled())
            {
                ArsenalWorkbenchScreen.MaterialItem materialItem = this.filteredMaterials.get(i);
                ItemStack stack = materialItem.getDisplayStack();
                if (!stack.isEmpty())
                {
                    RenderHelper.turnOff();
                    if (materialItem.isEnabled())
                    {
                        this.blit(matrixStack, startX + 172, startY + i * 19 + 63, 0, 184, 80, 19);
                    }
                    else
                    {
                        this.blit(matrixStack, startX + 172, startY + i * 19 + 63, 0, 222, 80, 19);
                    }

                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    String name = stack.getHoverName().getString();
                    if (this.font.width(name) > 55)
                    {
                        name = this.font.plainSubstrByWidth(name, 50).trim() + "...";
                    }
                    this.font.draw(matrixStack, name, startX + 172 + 22, startY + i * 19 + 6 + 63, Color.WHITE.getRGB());

                    Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(stack, startX + 172 + 2, startY + i * 19 + 1 + 63);

                    if (this.checkBoxMaterials.isToggled())
                    {
                        int count = InventoryUtil.getItemStackAmount(Minecraft.getInstance().player, stack);
                        stack = stack.copy();
                        stack.setCount(stack.getCount() - count);
                    }

                    Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, stack, startX + 172 + 2, startY + i * 19 + 1 + 63, null);
                }
            }
            else
            {
                ItemStack stack = this.currentTab.getRecipes().get(i).getResultItem();
                if (!stack.isEmpty())
                {
                    RenderHelper.turnOff();

                    this.blit(matrixStack, startX + 172, startY + i * 19 + 63, 0, 184, 80, 19);

                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    String name = stack.getHoverName().getString();
                    if (this.font.width(name) > 55)
                    {
                        name = this.font.plainSubstrByWidth(name, 50).trim() + "...";
                    }
                    this.font.draw(matrixStack, name, startX + 172 + 22, startY + i * 19 + 6 + 63, Color.WHITE.getRGB());

                    Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(stack, startX + 172 + 2, startY + i * 19 + 1 + 63);

                    Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, stack, startX + 172 + 2, startY + i * 19 + 1 + 63, null);
                }
            }
        }
    }

    private List<ArsenalWorkbenchScreen.MaterialItem> getMaterials()
    {
        List<ArsenalWorkbenchScreen.MaterialItem> materials = NonNullList.withSize(6, ArsenalWorkbenchScreen.MaterialItem.EMPTY);
        List<ArsenalWorkbenchScreen.MaterialItem> filteredMaterials = this.materials.stream().filter(materialItem -> this.checkBoxMaterials.isToggled() ? !materialItem.isEnabled() : materialItem != ArsenalWorkbenchScreen.MaterialItem.EMPTY).collect(Collectors.toList());
        for (int i = 0; i < filteredMaterials.size() && i < materials.size(); i++)
        {
            materials.set(i, filteredMaterials.get(i));
        }
        return materials;
    }

    public List<ArsenalWorkbenchScreen.Tab> getTabs()
    {
        return ImmutableList.copyOf(this.tabs);
    }

    public static class MaterialItem
    {
        public static final ArsenalWorkbenchScreen.MaterialItem EMPTY = new ArsenalWorkbenchScreen.MaterialItem();

        private long lastTime = System.currentTimeMillis();
        private int displayIndex;
        private boolean enabled = false;
        private WorkbenchIngredient ingredient;
        private final List<ItemStack> displayStacks = new ArrayList<>();

        private MaterialItem() {}

        private MaterialItem(WorkbenchIngredient ingredient)
        {
            this.ingredient = ingredient;
            Stream.of(ingredient.getItems()).forEach(stack -> {
                ItemStack displayStack = stack.copy();
                displayStack.setCount(ingredient.getCount());
                this.displayStacks.add(displayStack);
            });
        }

        public WorkbenchIngredient getIngredient()
        {
            return this.ingredient;
        }

        public void tick()
        {
            if (this.ingredient == null)
                return;

            this.updateEnabledState();
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastTime >= 1000)
            {
                this.displayIndex = (this.displayIndex + 1) % this.displayStacks.size();
                this.lastTime = currentTime;
            }
        }

        public ItemStack getDisplayStack()
        {
            return this.ingredient != null ? this.displayStacks.get(this.displayIndex) : ItemStack.EMPTY;
        }

        public void updateEnabledState()
        {
            this.enabled = InventoryUtil.hasWorkstationIngredient(Minecraft.getInstance().player, this.ingredient);
        }

        public boolean isEnabled()
        {
            return this.ingredient == null || this.enabled;
        }
    }

    private static class Tab
    {
        private final ItemStack icon;
        private final String id;
        private final List<WorkbenchRecipe> items;
        private int currentIndex;

        public Tab(ItemStack icon, String id, List<WorkbenchRecipe> items)
        {
            this.icon = icon;
            this.id = id;
            this.items = items;
        }

        public ItemStack getIcon()
        {
            return this.icon;
        }

        public String getTabKey()
        {
            return "gui.cgm.workbench.tab." + this.id;
        }

        public void setCurrentIndex(int currentIndex)
        {
            this.currentIndex = currentIndex;
        }

        public int getCurrentIndex()
        {
            return this.currentIndex;
        }

        public List<WorkbenchRecipe> getRecipes()
        {
            return this.items;
        }
    }
}
