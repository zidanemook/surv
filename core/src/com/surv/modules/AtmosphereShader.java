package com.surv.modules;

import com.surv.object.GameObject;
import com.surv.shaders.Shader;
import com.surv.utils.ResourceLoader;

public class AtmosphereShader extends Shader{

	private static AtmosphereShader instance = null;
	
	public static AtmosphereShader getInstance() 
	{
		if(instance == null) 
		{
			instance = new AtmosphereShader();
		}
		return instance;
	}
		
	protected AtmosphereShader()
	{
		super();

		addVertexShader(ResourceLoader.loadShader("shaders/atmosphere_VS.glsl"));
		addFragmentShader(ResourceLoader.loadShader("shaders/atmosphere_FS.glsl"));
		compileShader();
			
		addUniform("modelViewProjectionMatrix");
		addUniform("worldMatrix");
	}
		
	public void updateUniforms(GameObject object)
	{
		setUniform("modelViewProjectionMatrix", object.getTransform().getModelViewProjectionMatrix());
		setUniform("worldMatrix", object.getTransform().getWorldMatrix());
	}
}
