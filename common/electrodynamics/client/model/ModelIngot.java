package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelIngot extends ModelBase {
	ModelRenderer ingotRight;
	ModelRenderer ingotBody;
	ModelRenderer ingotLeft;

	public ModelIngot() {
		textureWidth = 64;
		textureHeight = 32;

		ingotRight = new ModelRenderer(this, 0, 0);
		ingotRight.addBox(0F, 0F, 0F, 7, 2, 1);
		ingotRight.setRotationPoint(0F, 0F, 0F);
		ingotRight.setTextureSize(64, 32);
		ingotRight.mirror = true;
		setRotation(ingotRight, -0.2792527F, 0F, 0F);
		ingotBody = new ModelRenderer(this, 0, 0);
		ingotBody.addBox(0F, 0F, 0F, 7, 2, 3);
		ingotBody.setRotationPoint(0F, 0F, 0F);
		ingotBody.setTextureSize(64, 32);
		ingotBody.mirror = true;
		setRotation(ingotBody, 0F, 0F, 0F);
		ingotLeft = new ModelRenderer(this, 0, 0);
		ingotLeft.addBox(0F, 0F, -1F, 7, 2, 1);
		ingotLeft.setRotationPoint(0F, 0F, 3F);
		ingotLeft.setTextureSize(64, 32);
		ingotLeft.mirror = true;
		setRotation(ingotLeft, 0.2792527F, 0F, 0F);
	}

	public void render(float f5) {
		ingotRight.render(f5);
		ingotBody.render(f5);
		ingotLeft.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
