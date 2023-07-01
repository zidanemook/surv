package com.surv.modules;

import com.surv.math.Vec3f;
import com.surv.math.Vec3i;
import com.surv.shaders.Shader;
import com.surv.utils.ResourceLoader;
import org.lwjgl.opengl.GL43;

import static org.lwjgl.opengl.GL42.glMemoryBarrier;
import static org.lwjgl.opengl.GL43.glDispatchCompute;

public class CalculateMaterialComputeShader extends Shader {
	private static CalculateMaterialComputeShader instance = null;

	public static CalculateMaterialComputeShader getInstance() {
	    if(instance == null) {
	    	instance = new CalculateMaterialComputeShader();
	    }
	      return instance;
	}

	protected CalculateMaterialComputeShader() {
		super();
		addComputeShader(ResourceLoader.loadShader("shaders/calculateMaterialComputeShader.comp"));
		compileShader();
		addUniform("offcet");
		addUniform("simpleScale");
	}

	public void updateSimpleScaleUniforms(int simpleScale) {
		setUniformi("simpleScale", simpleScale);
	}

	public void updateOffcetUniforms(Vec3i offcet) {
		setUniform("offcet", offcet);
	}

	public void dispatch(int num_groups_x, int num_groups_y, int num_groups_z){
		glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
		//glMemoryBarrier(GL43.GL_SHADER_STORAGE_BUFFER);
	}
}