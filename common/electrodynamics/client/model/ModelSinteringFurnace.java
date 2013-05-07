package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSinteringFurnace extends ModelBase {
	public ModelRenderer base1;
	public ModelRenderer base2;
	public ModelRenderer furnace;
	public ModelRenderer hingeBottom;
	public ModelRenderer doorTop;
	public ModelRenderer doorBottom;
	public ModelRenderer doorRight;
	public ModelRenderer doorLeft;
	public ModelRenderer hingeTop;

	public ModelSinteringFurnace() {
		textureWidth = 128;
		textureHeight = 64;

		base1 = new ModelRenderer(this, 0, 29);
		base1.addBox(0F, 0F, 0F, 16, 2, 16);
		base1.setRotationPoint(-8F, 22F, -8F);
		base1.setTextureSize(128, 64);
		base1.mirror = true;
		base2 = new ModelRenderer(this, 0, 48);
		base2.addBox(0F, 0F, 0F, 14, 2, 14);
		base2.setRotationPoint(-6F, 20F, -7F);
		base2.setTextureSize(128, 64);
		base2.mirror = true;
		furnace = new ModelRenderer(this, 0, 0);
		furnace.addBox(0F, 0F, 0F, 15, 12, 16);
		furnace.setRotationPoint(-7F, 8F, -8F);
		furnace.setTextureSize(128, 64);
		furnace.mirror = true;
		hingeBottom = new ModelRenderer(this, 69, 43);
		hingeBottom.addBox(0F, 0F, 0F, 1, 3, 1);
		hingeBottom.setRotationPoint(-8F, 15F, 7F);
		hingeBottom.setTextureSize(128, 64);
		hingeBottom.mirror = true;
		doorTop = new ModelRenderer(this, 65, 17);
		doorTop.addBox(0F, 0F, -14F, 1, 3, 14);
		doorTop.setRotationPoint(-8F, 8F, 7F);
		doorTop.setTextureSize(128, 64);
		doorTop.mirror = true;
		doorBottom = new ModelRenderer(this, 65, 0);
		doorBottom.addBox(0F, 9F, -14F, 1, 3, 14);
		doorBottom.setRotationPoint(-8F, 8F, 7F);
		doorBottom.setTextureSize(128, 64);
		doorBottom.mirror = true;
		doorRight = new ModelRenderer(this, 73, 34);
		doorRight.addBox(0F, 3F, -14F, 1, 6, 3);
		doorRight.setRotationPoint(-8F, 8F, 7F);
		doorRight.setTextureSize(128, 64);
		doorRight.mirror = true;
		doorLeft = new ModelRenderer(this, 65, 34);
		doorLeft.addBox(0F, 3F, -3F, 1, 6, 3);
		doorLeft.setRotationPoint(-8F, 8F, 7F);
		doorLeft.setTextureSize(128, 64);
		doorLeft.mirror = true;
		hingeTop = new ModelRenderer(this, 65, 43);
		hingeTop.addBox(0F, 0F, 0F, 1, 3, 1);
		hingeTop.setRotationPoint(-8F, 10F, 7F);
		hingeTop.setTextureSize(128, 64);
		hingeTop.mirror = true;
	}

	public void rotateDoor(float angle) {
		doorTop.rotateAngleY = angle;
		doorBottom.rotateAngleY = angle;
		doorLeft.rotateAngleY = angle;
		doorRight.rotateAngleY = angle;
	}
	
	public void renderAll(float scale) {
		renderFurnace(scale);
		renderDoor(scale);
	}
	
	public void renderFurnace(float scale) {
		base1.render(scale);
		base2.render(scale);
		furnace.render(scale);
		hingeBottom.render(scale);
		hingeTop.render(scale);
	}

	public void renderDoor(float scale) {
		doorTop.render(scale);
		doorBottom.render(scale);
		doorRight.render(scale);
		doorLeft.render(scale);
	}
	
}
