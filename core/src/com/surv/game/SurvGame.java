package com.surv.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.UBJsonReader;
import com.surv.dc.ChunkOctreeWrapper;
import com.surv.dc.utils.Frustum;
import com.surv.utils.CameraController;
import com.surv.utils.Util;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;


public class SurvGame extends ApplicationAdapter {

	public PerspectiveCamera cam;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	public Environment environment;

	public CameraController cameraController;
	private ChunkOctreeWrapper chunkOctreeWrapper;

	@Override
	public void create () {

		com.surv.configs.Default.init();

		modelBatch = new ModelBatch();
		cam = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(44.94776f,-4.30505f,-1229.6176f);
		cam.direction.set(-0.05808757f,-0.20107773f,0.9778515f).nor();
		cam.near = 0.1f;
		cam.far = 6000f;
		cam.update();

		cameraController = new CameraController(cam, 20f, 0.5f);
		Gdx.input.setInputProcessor(cameraController);

		Matrix4 viewProjectionMatrix = new Matrix4();
		viewProjectionMatrix.set(cam.combined);

		Frustum.getFrustum().calculateFrustum(Util.toMatrix4f(viewProjectionMatrix));

		Bullet.init();
		chunkOctreeWrapper = new ChunkOctreeWrapper(cam);

		ObjLoader loader = new ObjLoader();
		model = loader.loadModel(Gdx.files.internal("tree/trees9.obj"));
		instance = new ModelInstance(model);


		// Create an environment for lighting.
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));


	}

	@Override
	public void render () {

		cameraController.update();

		Frustum fr = Frustum.getFrustum();
		fr.calculateFrustum(Util.toMatrix4f(cam.combined));

		chunkOctreeWrapper.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instance, environment);
		chunkOctreeWrapper.render();
		modelBatch.end();


	}
	
	@Override
	public void dispose () {
		modelBatch.dispose();
		model.dispose();
		chunkOctreeWrapper.cleanUp();
	}
}
