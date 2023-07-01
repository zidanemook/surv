package com.surv.dc.impl;

import com.surv.math.Vec3i;
import com.surv.dc.OctreeNode;
import com.surv.dc.OctreeNodeType;

public class MdcOctreeNode extends OctreeNode {
    public MdcVertex[] vertices;

    public MdcOctreeNode(Vec3i position, int size, OctreeNodeType type) {
        super(position, size, type);
        this.vertices = new MdcVertex[0];
    }
}
