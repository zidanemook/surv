package com.surv.dc.entities;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.surv.buffers.VBO;
import com.surv.math.Transform;
import com.surv.math.Vec3f;

public class ModelEntity {
    private VBO vbo;
    private Transform transform;

    public ModelEntity(VBO vbo, PerspectiveCamera camera) {
        this.vbo = vbo;
        transform = new Transform(camera);
    }

    public void setTranslation(Vec3f translation) {
        transform.setTranslation(translation);
    }

    public void setRotation(Vec3f rotation) {
        transform.setRotation(rotation);
    }

    public void setScaling(Vec3f scaling) {
        transform.setScaling(scaling);
    }

    public VBO getVbo() {
        return vbo;
    }

    public Transform getTransform() {
        return transform;
    }
}
