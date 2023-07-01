package com.surv.dc.entities;

import com.surv.math.Vec3f;
import com.surv.model.Vertex;

public class MeshVertex extends Vertex {
    public MeshVertex(){
    }

    public MeshVertex(Vec3f pos, Vec3f norm, Vec3f color)
    {
        super();
        this.setPos(pos);
        this.setNormal(norm);
        this.setColor(color);
    }
}
