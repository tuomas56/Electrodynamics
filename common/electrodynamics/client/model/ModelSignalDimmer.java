package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class ModelSignalDimmer extends ModelBase{
	private ModelRenderer southWire;
	private ModelRenderer northWire;
	private ModelRenderer switchBoxTop4;
	private ModelRenderer switchBoxTop3;
	private ModelRenderer switchBoxTop2;
	private ModelRenderer switchBoxTop1;
	private ModelRenderer switchBoxBottom;
	private ModelRenderer switchTop;
	private ModelRenderer switchBase1;
	private ModelRenderer switchBase2;
	
	public ModelSignalDimmer(){
		this.textureWidth = 64;
		this.textureHeight = 32;
		
		this.southWire = new ModelRenderer(this, 12, 0);
		this.southWire.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 4);
		this.southWire.setRotationPoint(0.0F, 22.0F, 4.0F);
		this.southWire.setTextureSize(64, 32);
		this.southWire.mirror = true;
		this.setRotation(this.southWire, 0.0F, 0.0F, 0.0F);
		
		this.northWire = new ModelRenderer(this, 0, 0);
		this.northWire.addBox(-1.0F, 0.0F, -6.0F, 2, 2, 4);
		this.northWire.setRotationPoint(0.0F, 22.0F, -2.0F);
		this.northWire.setTextureSize(64, 32);
		this.northWire.mirror = true;
		this.setRotation(this.northWire, 0.0F, 0.0F, 0.0F);
		
		this.switchBoxTop4 = new ModelRenderer(this, 0, 18);
		this.switchBoxTop4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
		this.switchBoxTop4.setRotationPoint(1.0F, 21.5F, -3.0F);
		this.switchBoxTop4.setTextureSize(64, 32);
		this.switchBoxTop4.mirror = true;
		this.setRotation(this.switchBoxTop4, 0.0F, 0.0F, 0.0F);
		
		this.switchBoxTop3 = new ModelRenderer(this, 0, 16);
		this.switchBoxTop3.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1);
		this.switchBoxTop3.setRotationPoint(-3.0F, 21.5F, 3.0F);
		this.switchBoxTop3.setTextureSize(64, 32);
		this.switchBoxTop3.mirror = true;
		this.setRotation(this.switchBoxTop3, 0.0F, 0.0F, 0.0F);
		
		this.switchBoxTop2 = new ModelRenderer(this, 0, 18);
		this.switchBoxTop2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
		this.switchBoxTop2.setRotationPoint(-2.0F, 21.5F, -3.0F);
		this.switchBoxTop2.setTextureSize(64, 32);
		this.switchBoxTop2.mirror = true;
		this.setRotation(this.switchBoxTop2, 0.0F, 0.0F, 0.0F);
		
		this.switchBoxTop1 = new ModelRenderer(this, 0, 16);
		this.switchBoxTop1.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1);
		this.switchBoxTop1.setRotationPoint(-3.0F, 21.5F, -4.0F);
		this.switchBoxTop1.setTextureSize(64, 32);
		this.switchBoxTop1.mirror = true;
		this.setRotation(this.switchBoxTop1, 0.0F, 0.0F, 0.0F);
		
		this.switchBoxBottom = new ModelRenderer(this, 0, 6);
		this.switchBoxBottom.addBox(0.0F, 0.0F, 0.0F, 6, 2, 8);
		this.switchBoxBottom.setRotationPoint(-3.0F, 22.0F, -4.0F);
		this.switchBoxBottom.setTextureSize(64, 32);
		this.switchBoxBottom.mirror = true;
		this.setRotation(this.switchBoxBottom, 0.0F, 0.0F, 0.0F);
		
		this.switchTop = new ModelRenderer(this, 22, 16);
		this.switchTop.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.switchTop.setRotationPoint(-1.0F, 21.5F, -2.25F);
		this.switchTop.setTextureSize(64, 32);
		this.switchTop.mirror = true;
		this.setRotation(this.switchTop, 0.7853982F, 0.0F, 0.0F);
		
		this.switchBase1 = new ModelRenderer(this, 22, 16);
		this.switchBase1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.switchBase1.setRotationPoint(-1.0F, 21.5F, -2.3F);
		this.switchBase1.setTextureSize(64, 32);
		this.switchBase1.mirror = true;
		this.setRotation(this.switchBase1, 0.0F, 0.0F, 0.0F);
		
		this.switchBase2 = new ModelRenderer(this, 22, 16);
		this.switchBase2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
		this.switchBase2.setRotationPoint(-1.0F, 21.5F, -1.8F);
		this.switchBase2.setTextureSize(64, 32);
		this.switchBase2.mirror = true;
		this.setRotation(this.switchBase1, 0.0F, 0.0F, 0.0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		this.southWire.render(f5);
		this.northWire.render(f5);
		this.switchBoxTop4.render(f5);
		this.switchBoxTop3.render(f5);
		this.switchBoxTop2.render(f5);
		this.switchBoxTop1.render(f5);
		this.switchBoxBottom.render(f5);
		this.switchTop.render(f5);
		this.switchBase1.render(f5);
		this.switchBase2.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void renderSouthWire(float f){
		this.southWire.render(f);
	}
	
	public void renderNorthWire(float f){
		this.northWire.render(f);
	}
	
	public void renderSwitchBoxTop4(float f){
		this.switchBoxTop4.render(f);
	}
	
	public void renderSwitchBoxTop3(float f){
		this.switchBoxTop3.render(f);
	}
	
	public void renderSwitchBoxTop2(float f){
		this.switchBoxTop2.render(f);
	}
	
	public void renderSwitchBoxTop1(float f){
		this.switchBoxTop1.render(f);
	}
	
	public void renderSwitchBoxBottom(float f){
		this.switchBoxBottom.render(f);
	}
	
	public void renderSwitchTop(float f){
		this.switchTop.render(f);
	}
	
	public void renderSwitchBase1(float f){
		this.switchBase1.render(f);
	}
	
	public void renderSwitchBase2(float f){
		this.switchBase2.render(f);
	}
	
	public void renderAll(float f){
		renderNorthWire(f);
		renderSouthWire(f);
		renderSwitchBoxTop4(f);
		renderSwitchBoxTop3(f);
		renderSwitchBoxTop2(f);
		renderSwitchBoxTop1(f);
		renderSwitchBoxBottom(f);
		renderSwitchTop(f);
		renderSwitchBase1(f);
		renderSwitchBase2(f);
	}
	
	public void renderAllButWires(float f){
		renderSwitchBoxTop4(f);
		renderSwitchBoxTop3(f);
		renderSwitchBoxTop2(f);
		renderSwitchBoxTop1(f);
		renderSwitchBoxBottom(f);
		renderSwitchTop(f);
		renderSwitchBase1(f);
		renderSwitchBase2(f);
	}
}