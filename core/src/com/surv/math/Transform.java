package com.surv.math;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.surv.utils.Util;

public class Transform {


	private Vec3f translation;
	private Vec3f rotation;
	private Vec3f scaling;

	private PerspectiveCamera camera;
	
	public Transform(PerspectiveCamera camera)
	{
		setTranslation(new Vec3f(0,0,0));
		setRotation(new Vec3f(0,0,0));
		setScaling(new Vec3f(1,1,1));
		this.camera = camera;
	}
	
	public Matrix4f getWorldMatrix()
	{
		Matrix4f translationMatrix = new Matrix4f().Translation(translation);
		Matrix4f rotationMatrix = new Matrix4f().Rotation(rotation);
		Matrix4f scalingMatrix = new Matrix4f().Scaling(scaling);
		
		return translationMatrix.mul(scalingMatrix.mul(rotationMatrix));
	}
	
	public Matrix4f getModelMatrix()
	{
		Matrix4f rotationMatrix = new Matrix4f().Rotation(rotation);
		
		return rotationMatrix;
	}

	public Matrix4f getModelViewProjectionMatrix() {
		Matrix4 mat4 = camera.projection.cpy().mul(camera.view).mul(Util.toMatrix4(getWorldMatrix()));
		return new Matrix4f(
				mat4.val[0], mat4.val[4], mat4.val[8], mat4.val[12],
				mat4.val[1], mat4.val[5], mat4.val[9], mat4.val[13],
				mat4.val[2], mat4.val[6], mat4.val[10], mat4.val[14],
				mat4.val[3], mat4.val[7], mat4.val[11], mat4.val[15]
		);
	}


	//public Matrix4f getViewProjectionMatrix()
	//{
	//	return Camera.getInstance().getViewProjectionMatrix();
	//}

	//public Matrix4f getProjectionMatrix()
	//{
	//	return Camera.getInstance().getProjectionMatrix();
	//}

	public Vec3f getTranslation() {
		return translation;
	}

	public void setTranslation(Vec3f translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vec3f(x, y, z);
	}

	public Vec3f getRotation() {
		return rotation;
	}

	public void setRotation(Vec3f rotation) {
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation = new Vec3f(x,y,z);
	}

	public Vec3f getScaling() {
		return scaling;
	}

	public void setScaling(Vec3f scaling) {
		this.scaling = scaling;
	}
	
	public void setScaling(float x, float y, float z) {
		this.scaling = new Vec3f(x, y, z);
	}
}
