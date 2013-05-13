package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMetalTray extends ModelBase {
	ModelRenderer traySide1;
	ModelRenderer trayBase;
	ModelRenderer traySide2;
	ModelRenderer traySide3;
	ModelRenderer traySide4;

	public ModelMetalTray() {
		textureWidth = 64;
		textureHeight = 32;

		traySide1 = new ModelRenderer(this, 0, 0);
		traySide1.addBox(0F, 0F, 0F, 8, 1, 1);
		traySide1.setRotationPoint(-4F, 22F, 3F);
		traySide1.setTextureSize(64, 32);
		traySide1.mirror = true;
		setRotation(traySide1, 0F, 0F, 0F);
		trayBase = new ModelRenderer(this, 0, 0);
		trayBase.addBox(0F, 0F, 0F, 10, 1, 8);
		trayBase.setRotationPoint(-5F, 23F, -4F);
		trayBase.setTextureSize(64, 32);
		trayBase.mirror = true;
		setRotation(trayBase, 0F, 0F, 0F);
		traySide2 = new ModelRenderer(this, 0, 0);
		traySide2.addBox(0F, 0F, 0F, 1, 1, 8);
		traySide2.setRotationPoint(-5F, 22F, -4F);
		traySide2.setTextureSize(64, 32);
		traySide2.mirror = true;
		setRotation(traySide2, 0F, 0F, 0F);
		traySide3 = new ModelRenderer(this, 0, 0);
		traySide3.addBox(0F, 0F, 0F, 1, 1, 8);
		traySide3.setRotationPoint(4F, 22F, -4F);
		traySide3.setTextureSize(64, 32);
		traySide3.mirror = true;
		setRotation(traySide3, 0F, 0F, 0F);
		traySide4 = new ModelRenderer(this, 0, 0);
		traySide4.addBox(0F, 0F, 0F, 8, 1, 1);
		traySide4.setRotationPoint(-4F, 22F, -4F);
		traySide4.setTextureSize(64, 32);
		traySide4.mirror = true;
		setRotation(traySide4, 0F, 0F, 0F);
	}

	public void render(float f5) {
		traySide1.render(f5);
		trayBase.render(f5);
		traySide2.render(f5);
		traySide3.render(f5);
		traySide4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
