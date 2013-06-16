package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class ModelWire extends ModelBase{
	private ModelRenderer eastWire;
	private ModelRenderer westWire;
	private ModelRenderer northWire;
	private ModelRenderer southWire;
	private ModelRenderer centerWire;
	
	public ModelWire(){
		this.textureWidth = 64;
		this.textureHeight = 32;
		
		this.eastWire = new ModelRenderer(this, 0, 0);
		this.eastWire.addBox(-7.0F, 0.0F, -1.0F, 7, 2, 2);
		this.eastWire.setRotationPoint(-1.0F, 22.0F, 0.0F);
		this.eastWire.setTextureSize(64, 32);
		this.eastWire.mirror = true;
		this.setRotation(this.eastWire, 0.0F, 0.0F, 0.0F);
		
		this.westWire = new ModelRenderer(this, 0, 0);
		this.westWire.addBox(0.0F, 0.0F, -1.0F, 7, 2, 2);
		this.westWire.setRotationPoint(1.0F, 22.0F, 0.0F);
		this.westWire.setTextureSize(64, 32);
		this.westWire.mirror = true;
		this.setRotation(this.westWire, 0.0F, 0.0F, 0.0F);
		
		this.northWire = new ModelRenderer(this, 0, 15);
		this.northWire.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.northWire.setRotationPoint(-1.0F, 22.0F, -1.0F);
		this.northWire.setTextureSize(64, 32);
		this.northWire.mirror = true;
		this.setRotation(this.northWire, 0.0F, 0.0F, 0.0F);
		
		this.southWire = new ModelRenderer(this, 0, 4);
		this.southWire.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 7);
		this.southWire.setRotationPoint(0.0F, 22.0F, 1.0F);
		this.southWire.setTextureSize(64, 32);
		this.southWire.mirror = true;
		this.setRotation(this.southWire, 0.0F, 0.0F, 0.0F);
		
		this.centerWire = new ModelRenderer(this, 0, 4);
		this.centerWire.addBox(-1.0F, 0.0F, -6.0F, 2, 2, 7);
		this.centerWire.setRotationPoint(0.0F, 22.0F, -2.0F);
		this.centerWire.setTextureSize(64, 32);
		this.centerWire.mirror = true;
		this.setRotation(this.centerWire, 0.0F, 0.0F, 0.0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.eastWire.render(f5);
		this.southWire.render(f5);
		this.westWire.render(f5);
		this.centerWire.render(f5);
		this.northWire.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void renderCenter(float f){
		this.northWire.render(f);
	}
	
	public void renderWest(float f){
		this.westWire.render(f);
	}
	
	public void renderSouth(float f){
		this.southWire.render(f);
	}
	
	public void renderEast(float f){
		this.eastWire.render(f);
	}
	
	public void renderNorth(float f){
		this.centerWire.render(f);
	}
	
	public void renderAll(float f){
		this.renderCenter(f);
		this.renderSouth(f);
		this.renderNorth(f);
		this.renderEast(f);
		this.renderWest(f);
	}
}