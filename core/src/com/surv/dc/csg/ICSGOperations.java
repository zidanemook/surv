package com.surv.dc.csg;

import com.surv.dc.ChunkNode;
import com.surv.math.Vec4i;
import com.surv.dc.ChunkNode;
import com.surv.dc.entities.CSGOperationInfo;
import com.surv.dc.impl.CPUDensityField;
import com.surv.dc.impl.MeshGenerationContext;

import java.util.Map;

public interface ICSGOperations {
    boolean ApplyCSGOperations(MeshGenerationContext meshGen, CSGOperationInfo opInfo, ChunkNode node, CPUDensityField field);
    void ApplyReduceOperations(ChunkNode node, CPUDensityField field, Map<Vec4i, CPUDensityField> densityFieldCache);
}
