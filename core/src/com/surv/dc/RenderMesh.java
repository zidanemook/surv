package com.surv.dc;

import com.surv.math.Vec3i;
import com.surv.renderer.Renderer;
import com.surv.dc.entities.MeshBuffer;

public class RenderMesh {
    public final Vec3i min;
    public final int size;
    public final MeshBuffer meshBuffer;
    private Renderer render;

    public RenderMesh(Vec3i min, int size, MeshBuffer meshBuffer) {
        this.min = min;
        this.size = size;
        this.meshBuffer = meshBuffer;
    }

    public Renderer getRender() {
        return render;
    }

    public void setRender(Renderer render) {
        this.render = render;
    }
}