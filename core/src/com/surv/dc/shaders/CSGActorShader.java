package com.surv.dc.shaders;

import com.surv.math.Transform;
import com.surv.object.GameObject;
import com.surv.shaders.Shader;
import com.surv.utils.ResourceLoader;
import com.surv.dc.entities.ModelEntity;

public class CSGActorShader extends Shader {
    private static CSGActorShader instance = null;

    public static CSGActorShader getInstance() {
        if(instance == null) {
            instance = new CSGActorShader();
        }
        return instance;
    }

    private CSGActorShader(){
        super();
        addVertexShader(ResourceLoader.loadShader("shaders/csgAction.glsl"));
        addFragmentShader(ResourceLoader.loadShader("shaders/csgAction.frag"));
        compileShader();

        addUniform("worldMatrix");
        //addUniform("worldMatrix");
    }

    public void updateUniforms(GameObject object) {
        //setUniform("modelViewProjectionMatrix", object.getTransform().getModelViewProjectionMatrix());
    }

    public void updateTransform(ModelEntity modelEntity) {
        setUniform("worldMatrix", modelEntity.getTransform().getModelViewProjectionMatrix());
        //setUniform("worldMatrix", transform.getWorldMatrix());
    }
}