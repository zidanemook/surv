package com.surv.dc;

import com.surv.math.Vec3i;

public class PointerBasedOctreeNode extends OctreeNode{
    public com.surv.dc.OctreeDrawInfo drawInfo;

    public PointerBasedOctreeNode(Vec3i min, int size, OctreeNodeType type) {
        super(min, size, type);
        drawInfo = new com.surv.dc.OctreeDrawInfo();
    }
}
