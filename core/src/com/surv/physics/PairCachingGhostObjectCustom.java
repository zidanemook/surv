package com.surv.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.linearmath.btTransform;
import com.surv.utils.Util;

public class PairCachingGhostObjectCustom extends btPairCachingGhostObject {
    private btTransform transform;

    public PairCachingGhostObjectCustom() {
        super();
        transform = new btTransform();
    }

    public btTransform getTransform() {
        Matrix4 mat4 = new Matrix4();
        this.getWorldTransform(mat4);

        return Util.tobtTransform(mat4);
    }
}
