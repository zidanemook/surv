package com.surv.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody.btRigidBodyConstructionInfo;
import com.badlogic.gdx.physics.bullet.linearmath.btTransform;


public class RigidBodyCustom extends btRigidBody {
    private Matrix4 transform;

    public RigidBodyCustom(btRigidBodyConstructionInfo constructionInfo) {
        super(constructionInfo);
        transform = new Matrix4();
    }

//    public btTransform getWorldTransform() {
//        getWorldTransform(transform);
//        return new btTransform(transform);
//    }
}
