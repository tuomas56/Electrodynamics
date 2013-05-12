package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTreeTap extends ModelBase {
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer hook;

	public ModelTreeTap() {
		textureWidth = 128;
		textureHeight = 64;

		left = new ModelRenderer(this, 66, 0);
		left.addBox(-2F, -1F, 0F, 2, 1, 6);
		left.setRotationPoint(-0.03F, 0F, 3F);
		left.setTextureSize(128, 64);
		left.mirror = true;
		setRotation(left, 0.2443461F, -0.1741839F, 0.7853982F);
		right = new ModelRenderer(this, 66, 0);
		right.addBox(0F, -1F, 0F, 2, 1, 6);
		right.setRotationPoint(0F, 0F, 3.01F);
		right.setTextureSize(128, 64);
		right.mirror = true;
		setRotation(right, 0.2443461F, 0.1745329F, -0.7853982F);
		hook = new ModelRenderer(this, 66, 8);
		hook.addBox(0F, 0F, 0F, 1, 3, 1);
		hook.setRotationPoint(-0.5F, -0.7F, 3F);
		hook.setTextureSize(128, 64);
		hook.mirror = true;
		setRotation(hook, 0F, 0F, 0F);
	}

	public void render(float f5) {
		left.render(f5);
		right.render(f5);
		hook.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
