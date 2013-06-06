package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDolly extends ModelBase {
	ModelRenderer plank2;
	ModelRenderer support2;
	ModelRenderer support1;
	ModelRenderer plank1;
	ModelRenderer wheel4;
	ModelRenderer hinge4;
	ModelRenderer wheel3;
	ModelRenderer hinge3;
	ModelRenderer wheel2;
	ModelRenderer hinge2;
	ModelRenderer hinge1;
	ModelRenderer wheel1;

	public ModelDolly() {
		textureWidth = 64;
		textureHeight = 32;

		plank2 = new ModelRenderer(this, 0, 0);
		plank2.addBox(0F, 0F, 0F, 16, 1, 4);
		plank2.setRotationPoint(-8F, 21F, 4F);
		plank2.setTextureSize(64, 32);
		plank2.mirror = true;
		setRotation(plank2, 0F, 0F, 0F);
		support2 = new ModelRenderer(this, 0, 5);
		support2.addBox(0F, 0F, 0F, 4, 1, 16);
		support2.setRotationPoint(4F, 20F, -8F);
		support2.setTextureSize(64, 32);
		support2.mirror = true;
		setRotation(support2, 0F, 0F, 0F);
		support1 = new ModelRenderer(this, 0, 5);
		support1.addBox(0F, 0F, 0F, 4, 1, 16);
		support1.setRotationPoint(-8F, 20F, -8F);
		support1.setTextureSize(64, 32);
		support1.mirror = true;
		setRotation(support1, 0F, 0F, 0F);
		plank1 = new ModelRenderer(this, 0, 0);
		plank1.addBox(0F, 0F, 0F, 16, 1, 4);
		plank1.setRotationPoint(-8F, 21F, -8F);
		plank1.setTextureSize(64, 32);
		plank1.mirror = true;
		setRotation(plank1, 0F, 0F, 0F);
		wheel4 = new ModelRenderer(this, 0, 27);
		wheel4.addBox(0F, 0F, 0F, 1, 2, 2);
		wheel4.setRotationPoint(5.5F, 22F, 5F);
		wheel4.setTextureSize(64, 32);
		wheel4.mirror = true;
		setRotation(wheel4, 0F, 0F, 0F);
		hinge4 = new ModelRenderer(this, 0, 24);
		hinge4.addBox(0F, 0F, 0F, 1, 1, 1);
		hinge4.setRotationPoint(6.5F, 22F, 5.5F);
		hinge4.setTextureSize(64, 32);
		hinge4.mirror = true;
		setRotation(hinge4, 0F, 0F, 0F);
		wheel3 = new ModelRenderer(this, 0, 27);
		wheel3.addBox(0F, 0F, 0F, 1, 2, 2);
		wheel3.setRotationPoint(5.5F, 22F, -7F);
		wheel3.setTextureSize(64, 32);
		wheel3.mirror = true;
		setRotation(wheel3, 0F, 0F, 0F);
		hinge3 = new ModelRenderer(this, 0, 24);
		hinge3.addBox(0F, 0F, 0F, 1, 1, 1);
		hinge3.setRotationPoint(6.5F, 22F, -6.5F);
		hinge3.setTextureSize(64, 32);
		hinge3.mirror = true;
		setRotation(hinge3, 0F, 0F, 0F);
		wheel2 = new ModelRenderer(this, 0, 27);
		wheel2.addBox(0F, 0F, 0F, 1, 2, 2);
		wheel2.setRotationPoint(-6.5F, 22F, 5F);
		wheel2.setTextureSize(64, 32);
		wheel2.mirror = true;
		setRotation(wheel2, 0F, 0F, 0F);
		hinge2 = new ModelRenderer(this, 0, 24);
		hinge2.addBox(0F, 0F, 0F, 1, 1, 1);
		hinge2.setRotationPoint(-5.5F, 22F, 5.5F);
		hinge2.setTextureSize(64, 32);
		hinge2.mirror = true;
		setRotation(hinge2, 0F, 0F, 0F);
		hinge1 = new ModelRenderer(this, 0, 24);
		hinge1.addBox(0F, 0F, 0F, 1, 1, 1);
		hinge1.setRotationPoint(-5.5F, 22F, -6.5F);
		hinge1.setTextureSize(64, 32);
		hinge1.mirror = true;
		setRotation(hinge1, 0F, 0F, 0F);
		wheel1 = new ModelRenderer(this, 0, 27);
		wheel1.addBox(0F, 0F, 0F, 1, 2, 2);
		wheel1.setRotationPoint(-6.5F, 22F, -7F);
		wheel1.setTextureSize(64, 32);
		wheel1.mirror = true;
		setRotation(wheel1, 0F, 0F, 0F);
	}

	public void render(float f5) {
		plank2.render(f5);
		support2.render(f5);
		support1.render(f5);
		plank1.render(f5);
		wheel4.render(f5);
		hinge4.render(f5);
		wheel3.render(f5);
		hinge3.render(f5);
		wheel2.render(f5);
		hinge2.render(f5);
		hinge1.render(f5);
		wheel1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
