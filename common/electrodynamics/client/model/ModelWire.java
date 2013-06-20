package electrodynamics.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWire extends ModelBase{
	ModelRenderer rightWire1;
	ModelRenderer rightWire2;
	ModelRenderer rightWire3;
	ModelRenderer rightWire4;
	ModelRenderer rightWire5;
	
	ModelRenderer topWire2;
	ModelRenderer topWire4;
	ModelRenderer topWire5;
	ModelRenderer topWire3;
	ModelRenderer topWire1;
	
	ModelRenderer bottomWire1;
	ModelRenderer bottomWire2;
	ModelRenderer bottomWire3;
	ModelRenderer bottomWire4;
	ModelRenderer bottomWire5;
	
	ModelRenderer leftWire1;
	ModelRenderer leftWire3;
	ModelRenderer leftWire2;
	ModelRenderer leftWire4;
	ModelRenderer leftWire5;
	
	ModelRenderer backWire1;
	ModelRenderer backWire2;
	ModelRenderer backWire3;
	ModelRenderer backWire4;
	ModelRenderer backWire5;
	
	ModelRenderer frontWire1;
	ModelRenderer frontWire2;
	ModelRenderer frontWire3;
	ModelRenderer frontWire4;
	ModelRenderer frontWire5;

	public ModelWire(){
		textureWidth = 64;
		textureHeight = 32;

		rightWire1 = new ModelRenderer(this, 18, 0);
		rightWire1.addBox(0F, 0F, -1F, 2, 7, 2);
		rightWire1.setRotationPoint(-8F, 8F, 0F);
		rightWire1.setTextureSize(64, 32);
		rightWire1.mirror = true;
		setRotation(rightWire1, 0F, 0F, 0F);
		
		rightWire2 = new ModelRenderer(this, 0, 4);
		rightWire2.addBox(-1F, 0F, -6F, 2, 2, 7);
		rightWire2.setRotationPoint(-7F, 15F, 7F);
		rightWire2.setTextureSize(64, 32);
		rightWire2.mirror = true;
		setRotation(rightWire2, 0F, 0F, 0F);
		
		rightWire3 = new ModelRenderer(this, 18, 0);
		rightWire3.addBox(0F, 0F, -1F, 2, 7, 2);
		rightWire3.setRotationPoint(-8F, 17F, 0F);
		rightWire3.setTextureSize(64, 32);
		rightWire3.mirror = true;
		setRotation(rightWire3, 0F, 0F, 0F);
		
		rightWire4 = new ModelRenderer(this, 0, 4);
		rightWire4.addBox(-1F, 0F, -6F, 2, 2, 7);
		rightWire4.setRotationPoint(-7F, 15F, -2F);
		rightWire4.setTextureSize(64, 32);
		rightWire4.mirror = true;
		setRotation(rightWire4, 0F, 0F, 0F);
		
		rightWire5 = new ModelRenderer(this, 0, 15);
		rightWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		rightWire5.setRotationPoint(-8F, 15F, -1F);
		rightWire5.setTextureSize(64, 32);
		rightWire5.mirror = true;
		setRotation(rightWire5, 0F, 0F, 0F);
		
		topWire2 = new ModelRenderer(this, 0, 4);
		topWire2.addBox(-1F, 0F, -6F, 2, 2, 7);
		topWire2.setRotationPoint(0F, 8F, -2F);
		topWire2.setTextureSize(64, 32);
		topWire2.mirror = true;
		setRotation(topWire2, 0F, 0F, 0F);
		
		topWire4 = new ModelRenderer(this, 0, 4);
		topWire4.addBox(-1F, 0F, 0F, 2, 2, 7);
		topWire4.setRotationPoint(0F, 8F, 1F);
		topWire4.setTextureSize(64, 32);
		topWire4.mirror = true;
		setRotation(topWire4, 0F, 0F, 0F);
		
		topWire5 = new ModelRenderer(this, 0, 15);
		topWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		topWire5.setRotationPoint(-1F, 8F, -1F);
		topWire5.setTextureSize(64, 32);
		topWire5.mirror = true;
		setRotation(topWire5, 0F, 0F, 0F);
		
		topWire3 = new ModelRenderer(this, 0, 0);
		topWire3.addBox(0F, 0F, -1F, 7, 2, 2);
		topWire3.setRotationPoint(1F, 8F, 0F);
		topWire3.setTextureSize(64, 32);
		topWire3.mirror = true;
		setRotation(topWire3, 0F, 0F, 0F);
		
		topWire1 = new ModelRenderer(this, 0, 0);
		topWire1.addBox(-7F, 0F, -1F, 7, 2, 2);
		topWire1.setRotationPoint(-1F, 8F, 0F);
		topWire1.setTextureSize(64, 32);
		topWire1.mirror = true;
		setRotation(topWire1, 0F, 0F, 0F);
		
		bottomWire1 = new ModelRenderer(this, 0, 0);
		bottomWire1.addBox(-7F, 0F, -1F, 7, 2, 2);
		bottomWire1.setRotationPoint(-1F, 22F, 0F);
		bottomWire1.setTextureSize(64, 32);
		bottomWire1.mirror = true;
		setRotation(bottomWire1, 0F, 0F, 0F);
		
		bottomWire2 = new ModelRenderer(this, 0, 4);
		bottomWire2.addBox(-1F, 0F, -6F, 2, 2, 7);
		bottomWire2.setRotationPoint(0F, 22F, -2F);
		bottomWire2.setTextureSize(64, 32);
		bottomWire2.mirror = true;
		setRotation(bottomWire2, 0F, 0F, 0F);
		
		bottomWire3 = new ModelRenderer(this, 0, 0);
		bottomWire3.addBox(0F, 0F, -1F, 7, 2, 2);
		bottomWire3.setRotationPoint(1F, 22F, 0F);
		bottomWire3.setTextureSize(64, 32);
		bottomWire3.mirror = true;
		setRotation(bottomWire3, 0F, 0F, 0F);
		
		bottomWire4 = new ModelRenderer(this, 0, 4);
		bottomWire4.addBox(-1F, 0F, 0F, 2, 2, 7);
		bottomWire4.setRotationPoint(0F, 22F, 1F);
		bottomWire4.setTextureSize(64, 32);
		bottomWire4.mirror = true;
		setRotation(bottomWire4, 0F, 0F, 0F);
		
		bottomWire5 = new ModelRenderer(this, 0, 15);
		bottomWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		bottomWire5.setRotationPoint(-1F, 22F, -1F);
		bottomWire5.setTextureSize(64, 32);
		bottomWire5.mirror = true;
		setRotation(bottomWire5, 0F, 0F, 0F);
		
		leftWire1 = new ModelRenderer(this, 18, 0);
		leftWire1.addBox(0F, 0F, -1F, 2, 7, 2);
		leftWire1.setRotationPoint(6F, 8F, 0F);
		leftWire1.setTextureSize(64, 32);
		leftWire1.mirror = true;
		setRotation(leftWire1, 0F, 0F, 0F);
		
		leftWire3 = new ModelRenderer(this, 18, 0);
		leftWire3.addBox(0F, 0F, -1F, 2, 7, 2);
		leftWire3.setRotationPoint(6F, 17F, 0F);
		leftWire3.setTextureSize(64, 32);
		leftWire3.mirror = true;
		setRotation(leftWire3, 0F, 0F, 0F);
		
		leftWire2 = new ModelRenderer(this, 0, 4);
		leftWire2.addBox(-1F, 0F, -6F, 2, 2, 7);
		leftWire2.setRotationPoint(7F, 15F, 7F);
		leftWire2.setTextureSize(64, 32);
		leftWire2.mirror = true;
		setRotation(leftWire2, 0F, 0F, 0F);
		
		leftWire4 = new ModelRenderer(this, 0, 4);
		leftWire4.addBox(-1F, 0F, -6F, 2, 2, 7);
		leftWire4.setRotationPoint(7F, 15F, -2F);
		leftWire4.setTextureSize(64, 32);
		leftWire4.mirror = true;
		setRotation(leftWire4, 0F, 0F, 0F);
		
		leftWire5 = new ModelRenderer(this, 0, 15);
		leftWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		leftWire5.setRotationPoint(6F, 15F, -1F);
		leftWire5.setTextureSize(64, 32);
		leftWire5.mirror = true;
		setRotation(leftWire5, 0F, 0F, 0F);
		
		backWire1 = new ModelRenderer(this, 18, 0);
		backWire1.addBox(0F, 0F, -1F, 2, 7, 2);
		backWire1.setRotationPoint(-1F, 8F, 7F);
		backWire1.setTextureSize(64, 32);
		backWire1.mirror = true;
		setRotation(backWire1, 0F, 0F, 0F);
		
		backWire2 = new ModelRenderer(this, 0, 0);
		backWire2.addBox(0F, 0F, -1F, 7, 2, 2);
		backWire2.setRotationPoint(1F, 15F, 7F);
		backWire2.setTextureSize(64, 32);
		backWire2.mirror = true;
		setRotation(backWire2, 0F, 0F, 0F);
		
		backWire3 = new ModelRenderer(this, 18, 0);
		backWire3.addBox(0F, 0F, -1F, 2, 7, 2);
		backWire3.setRotationPoint(-1F, 17F, 7F);
		backWire3.setTextureSize(64, 32);
		backWire3.mirror = true;
		setRotation(backWire3, 0F, 0F, 0F);
		
		backWire4 = new ModelRenderer(this, 0, 0);
		backWire4.addBox(-7F, 0F, -1F, 7, 2, 2);
		backWire4.setRotationPoint(-1F, 15F, 7F);
		backWire4.setTextureSize(64, 32);
		backWire4.mirror = true;
		setRotation(backWire4, 0F, 0F, 0F);
		
		backWire5 = new ModelRenderer(this, 0, 15);
		backWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		backWire5.setRotationPoint(-1F, 15F, 6F);
		backWire5.setTextureSize(64, 32);
		backWire5.mirror = true;
		setRotation(backWire5, 0F, 0F, 0F);
		
		frontWire1 = new ModelRenderer(this, 18, 0);
		frontWire1.addBox(0F, 0F, -1F, 2, 7, 2);
		frontWire1.setRotationPoint(-1F, 8F, -7F);
		frontWire1.setTextureSize(64, 32);
		frontWire1.mirror = true;
		setRotation(frontWire1, 0F, 0F, 0F);
		
		frontWire2 = new ModelRenderer(this, 0, 0);
		frontWire2.addBox(0F, 0F, -1F, 7, 2, 2);
		frontWire2.setRotationPoint(1F, 15F, -7F);
		frontWire2.setTextureSize(64, 32);
		frontWire2.mirror = true;
		setRotation(frontWire2, 0F, 0F, 0F);
		
		frontWire3 = new ModelRenderer(this, 18, 0);
		frontWire3.addBox(0F, 0F, -1F, 2, 7, 2);
		frontWire3.setRotationPoint(-1F, 17F, -7F);
		frontWire3.setTextureSize(64, 32);
		frontWire3.mirror = true;
		setRotation(frontWire3, 0F, 0F, 0F);
		
		frontWire4 = new ModelRenderer(this, 0, 0);
		frontWire4.addBox(-7F, 0F, -1F, 7, 2, 2);
		frontWire4.setRotationPoint(-1F, 15F, -7F);
		frontWire4.setTextureSize(64, 32);
		frontWire4.mirror = true;
		setRotation(frontWire4, 0F, 0F, 0F);
		
		frontWire5 = new ModelRenderer(this, 0, 15);
		frontWire5.addBox(0F, 0F, 0F, 2, 2, 2);
		frontWire5.setRotationPoint(-1F, 15F, -8F);
		frontWire5.setTextureSize(64, 32);
		frontWire5.mirror = true;
		setRotation(frontWire5, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		rightWire1.render(f5);
		rightWire2.render(f5);
		rightWire3.render(f5);
		rightWire4.render(f5);
		rightWire5.render(f5);
		
		topWire2.render(f5);
		topWire4.render(f5);
		topWire5.render(f5);
		topWire3.render(f5);
		topWire1.render(f5);
		
		bottomWire1.render(f5);
		bottomWire2.render(f5);
		bottomWire3.render(f5);
		bottomWire4.render(f5);
		bottomWire5.render(f5);
		
		leftWire1.render(f5);
		leftWire3.render(f5);
		leftWire2.render(f5);
		leftWire4.render(f5);
		leftWire5.render(f5);
		
		backWire1.render(f5);
		backWire2.render(f5);
		backWire3.render(f5);
		backWire4.render(f5);
		backWire5.render(f5);
		
		frontWire1.render(f5);
		frontWire2.render(f5);
		frontWire3.render(f5);
		frontWire4.render(f5);
		frontWire5.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void renderAllSides(float f){
		
	}
	
	public void renderTopWire(float f, int side){
		switch(side)
		{
		case 1:
			topWire1.render(f);
			break;
		case 2:
			topWire2.render(f);
			break;
		case 3:
			topWire3.render(f);
			break;
		case 4:
			topWire4.render(f);
			break;
		case 5:
			topWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderBackWire(float f, int side){
		switch(side)
		{
		case 1:
			backWire1.render(f);
			break;
		case 2:
			backWire2.render(f);
			break;
		case 3:
			backWire3.render(f);
			break;
		case 4:
			backWire4.render(f);
			break;
		case 5:
			backWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderFrontWire(float f, int side){
		switch(side)
		{
		case 1:
			frontWire1.render(f);
			break;
		case 2:
			frontWire2.render(f);
			break;
		case 3:
			frontWire3.render(f);
			break;
		case 4:
			frontWire4.render(f);
			break;
		case 5:
			frontWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderRightWire(float f, int side){
		switch(side)
		{
		case 1:
			rightWire1.render(f);
			break;
		case 2:
			rightWire2.render(f);
			break;
		case 3:
			rightWire3.render(f);
			break;
		case 4:
			rightWire4.render(f);
			break;
		case 5:
			rightWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderLeftWire(float f, int side){
		switch(side)
		{
		case 1:
			leftWire1.render(f);
			break;
		case 2:
			leftWire2.render(f);
			break;
		case 3:
			leftWire3.render(f);
			break;
		case 4:
			leftWire4.render(f);
			break;
		case 5:
			leftWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderBottomWire(float f, int side){
		switch(side)
		{
		case 1:
			bottomWire1.render(f);
			break;
		case 2:
			bottomWire2.render(f);
			break;
		case 3:
			bottomWire3.render(f);
			break;
		case 4:
			bottomWire4.render(f);
			break;
		case 5:
			bottomWire5.render(f);
			break;
		default:
			break;
		}
	}
	
	public void renderBottomWireAll(){
		for(int i = 1; i < 5; i++){
			renderBottomWire(0.0625F, i);
		}
	}
	
	public void renderLeftWireAll(){
		for(int i = 1; i < 5; i++){
			renderLeftWire(0.0625F, i);
		}
	}
	
	public void renderRightWireAll(){
		for(int i = 1; i < 5; i++){
			renderRightWire(0.0625F, i);
		}
	}
	
	public void renderTopWireAll(){
		for(int i = 1; i < 5; i++){
			renderTopWire(0.0625F, i);
		}
	}
	
	public void renderBackWireAll(){
		for(int i = 1; i < 5; i++){
			renderBackWire(0.0625F, i);
		}
	}
	
	public void renderFrontWireAll(){
		for(int i = 1; i < 5; i++){
			renderFrontWire(0.0625F, i);
		}
	}
}