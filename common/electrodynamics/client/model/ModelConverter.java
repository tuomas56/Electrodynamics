package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelConverter extends ModelBase {
	ModelRenderer right;
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer left;

	public ModelConverter() {
		textureWidth = 64;
		textureHeight = 32;

		right = new ModelRenderer(this, 0, 0);
		right.addBox(0F, 0F, 0F, 1, 14, 2);
		right.setRotationPoint(6F, 9F, -1F);
		right.setTextureSize(64, 32);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 14, 1, 2);
		bottom.setRotationPoint(-7F, 23F, -1F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(0F, 0F, 0F, 14, 1, 2);
		top.setRotationPoint(-7F, 8F, -1F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		left = new ModelRenderer(this, 0, 0);
		left.addBox(0F, 0F, 0F, 1, 14, 2);
		left.setRotationPoint(-7F, 9F, -1F);
		left.setTextureSize(64, 32);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
	}

	public void render(float f5) {
		right.render(f5);
		bottom.render(f5);
		top.render(f5);
		left.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
