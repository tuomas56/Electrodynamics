package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSinteringFurnace extends ModelBase {
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer furnaceBox;
	ModelRenderer hingeBottom;
	ModelRenderer doorTop;
	ModelRenderer doorBottom;
	ModelRenderer doorRight;
	ModelRenderer doorLeft;
	ModelRenderer hingeTop;

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
		furnaceBox = new ModelRenderer(this, 0, 0);
		furnaceBox.addBox(0F, 0F, 0F, 15, 12, 16);
		furnaceBox.setRotationPoint(-7F, 8F, -8F);
		furnaceBox.setTextureSize(128, 64);
		furnaceBox.mirror = true;
		hingeBottom = new ModelRenderer(this, 69, 43);
		hingeBottom.addBox(0F, 0F, 0F, 1, 3, 1);
		hingeBottom.setRotationPoint(-8F, 15F, 7F);
		hingeBottom.setTextureSize(128, 64);
		hingeBottom.mirror = true;
		doorTop = new ModelRenderer(this, 65, 17);
		doorTop.addBox(0F, 0F, 0F, 1, 3, 14);
		doorTop.setRotationPoint(-8F, 8F, -7F);
		doorTop.setTextureSize(128, 64);
		doorTop.mirror = true;
		doorBottom = new ModelRenderer(this, 65, 0);
		doorBottom.addBox(0F, 0F, 0F, 1, 3, 14);
		doorBottom.setRotationPoint(-8F, 17F, -7F);
		doorBottom.setTextureSize(128, 64);
		doorBottom.mirror = true;
		doorRight = new ModelRenderer(this, 73, 34);
		doorRight.addBox(0F, 0F, 0F, 1, 6, 3);
		doorRight.setRotationPoint(-8F, 11F, -7F);
		doorRight.setTextureSize(128, 64);
		doorRight.mirror = true;
		doorLeft = new ModelRenderer(this, 65, 34);
		doorLeft.addBox(0F, 0F, 0F, 1, 6, 3);
		doorLeft.setRotationPoint(-8F, 11F, 4F);
		doorLeft.setTextureSize(128, 64);
		doorLeft.mirror = true;
		hingeTop = new ModelRenderer(this, 65, 43);
		hingeTop.addBox(0F, 0F, 0F, 1, 3, 1);
		hingeTop.setRotationPoint(-8F, 10F, 7F);
		hingeTop.setTextureSize(128, 64);
		hingeTop.mirror = true;
	}

	public void render(float f5) {
		base1.render(f5);
		base2.render(f5);
		furnaceBox.render(f5);
		hingeBottom.render(f5);
		doorTop.render(f5);
		doorBottom.render(f5);
		doorRight.render(f5);
		doorLeft.render(f5);
		hingeTop.render(f5);
	}

}
