package com.surv.modules;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.surv.buffers.MeshVBO;
import com.surv.configs.CCW;
import com.surv.model.Mesh;
import com.surv.renderer.RenderInfo;
import com.surv.renderer.Renderer;
import com.surv.object.GameObject;
import com.surv.utils.Constants;
import com.surv.utils.objloader.OBJLoader;

public class Skydome extends GameObject{
	
	public Skydome(PerspectiveCamera camera)
	{
		super(camera);
		getTransform().setScaling(Constants.ZFAR*0.5f, Constants.ZFAR*0.5f, Constants.ZFAR*0.5f);
		Mesh mesh = new OBJLoader().load("./res/models/dome", "dome.obj", null)[0].getMesh();
		MeshVBO meshBuffer = new MeshVBO();
		meshBuffer.addData(mesh);
		Renderer renderer = new Renderer(meshBuffer);
		renderer.setRenderInfo(new RenderInfo(new CCW(), AtmosphereShader.getInstance()));
		addComponent(Constants.RENDERER_COMPONENT, renderer);
	}
}
