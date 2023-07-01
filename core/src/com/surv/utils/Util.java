package com.surv.utils;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.linearmath.btTransform;
import com.surv.math.*;
import com.surv.model.Vertex;
import com.surv.utils.objloader.Face;
import com.surv.utils.objloader.SmoothingGroup;

import javax.vecmath.Vector3f;
import java.util.ArrayList;

public class Util {

	public static Matrix4f toMatrix4f(Matrix4 mat4){
		float[] values = new float[16];
		values = mat4.getValues();

		return new Matrix4f(
				values[0],  values[1],  values[2],  values[3],
				values[4],  values[5],  values[6],  values[7],
				values[8],  values[9],  values[10], values[11],
				values[12], values[13], values[14], values[15]
		);
	}

	public static btTransform tobtTransform(Matrix4 matrix){
		btTransform transform = new btTransform();
		transform.setFromOpenGLMatrix(matrix.val);
		return transform;
	}

	public static Matrix4 toMatrix4(btTransform transform){

		float[] openGLMatrix = new float[16];
		transform.getOpenGLMatrix(openGLMatrix);
		return new Matrix4(openGLMatrix);
	}

	public static Matrix4 toMatrix4(Matrix4f matrix4f){
		Matrix4 matrix4 = new Matrix4();
		matrix4.val[0] = matrix4f.m[0][0];
		matrix4.val[1] = matrix4f.m[0][1];
		matrix4.val[2] = matrix4f.m[0][2];
		matrix4.val[3] = matrix4f.m[0][3];
		matrix4.val[4] = matrix4f.m[1][0];
		matrix4.val[5] = matrix4f.m[1][1];
		matrix4.val[6] = matrix4f.m[1][2];
		matrix4.val[7] = matrix4f.m[1][3];
		matrix4.val[8] = matrix4f.m[2][0];
		matrix4.val[9] = matrix4f.m[2][1];
		matrix4.val[10] = matrix4f.m[2][2];
		matrix4.val[11] = matrix4f.m[2][3];
		matrix4.val[12] = matrix4f.m[3][0];
		matrix4.val[13] = matrix4f.m[3][1];
		matrix4.val[14] = matrix4f.m[3][2];
		matrix4.val[15] = matrix4f.m[3][3];
		return matrix4;
	}


	public static Vec3f toVec3f(Vector3 vector3){
		return new Vec3f(vector3.x, vector3.y, vector3.z);
	}
	public static Vector3 toVector3(Vector3f vector3f){
		return new Vector3(vector3f.getX(), vector3f.getY(), vector3f.getZ());
	}
	public static String [] removeEmptyStrings(String[] data)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++)
			if(!data[i].equals(""))
				result.add(data[i]);
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] data)
	{
		int[] result = new int[data.length];
		
		for(int i=0; i < data.length; i++)
			result[i] = data[i].intValue();
		
		return result;
	}
	
	public static void generateNormalsCW(Vertex[] vertices, int[] indices)
	{
	    for ( int i = 0; i < indices.length; i += 3 )
	    {
	    	Vec3f v0 = vertices[indices[i    ]].getPos();
	    	Vec3f v1 = vertices[indices[i + 1]].getPos();
	    	Vec3f v2 = vertices[indices[i + 2]].getPos();
	        
	    	Vec3f normal = v1.sub(v0).cross(v2.sub(v0)).normalize();
	        
	        vertices[indices[i	  ]].setNormal(vertices[indices[i    ]].getNormal().add(normal));
	        vertices[indices[i + 1]].setNormal(vertices[indices[i + 1]].getNormal().add(normal));
	        vertices[indices[i + 2]].setNormal(vertices[indices[i + 2]].getNormal().add(normal));
	    }

	    for ( int i = 0; i < vertices.length; ++i )
	    {	
	    	vertices[i].setNormal(vertices[i].getNormal().normalize());
	    }       
	}
	
	public static void generateNormalsCCW(Vertex[] vertices, int[] indices)
	{
	    for ( int i = 0; i < indices.length; i += 3 )
	    {
	    	Vec3f v0 = vertices[indices[i    ]].getPos();
	    	Vec3f v1 = vertices[indices[i + 1]].getPos();
	    	Vec3f v2 = vertices[indices[i + 2]].getPos();
	        
	    	Vec3f normal = v2.sub(v0).cross(v1.sub(v0)).normalize();
	        
	        vertices[indices[i	  ]].setNormal(vertices[indices[i    ]].getNormal().add(normal));
	        vertices[indices[i + 1]].setNormal(vertices[indices[i + 1]].getNormal().add(normal));
	        vertices[indices[i + 2]].setNormal(vertices[indices[i + 2]].getNormal().add(normal));
	    }

	    for ( int i = 0; i < vertices.length; ++i )
	    {	
	    	vertices[i].setNormal(vertices[i].getNormal().normalize());
	    }       
	}
	
	public static void generateNormalsCW(ArrayList<Vertex> vertices, ArrayList<Integer> indices)
	{
	    for ( int i = 0; i < indices.size(); i += 3 )
	    {
	    	Vec3f v0 = vertices.get(indices.get(i)).getPos();
	    	Vec3f v1 = vertices.get(indices.get(i+1)).getPos();
	    	Vec3f v2 = vertices.get(indices.get(i+2)).getPos();
	        
	    	Vec3f normal = v1.sub(v0).cross(v2.sub(v0)).normalize();
	        
	        vertices.get(indices.get(i)).setNormal(vertices.get(indices.get(i)).getNormal().add(normal));
	        vertices.get(indices.get(i+1)).setNormal(vertices.get(indices.get(i+1)).getNormal().add(normal));
	        vertices.get(indices.get(i+2)).setNormal(vertices.get(indices.get(i+2)).getNormal().add(normal));
	    }

	    for ( int i = 0; i < vertices.size(); ++i )
	    {	
	    	vertices.get(i).setNormal(vertices.get(i).getNormal().normalize());
	    }       
	}
	
	public static void generateNormalsCW(SmoothingGroup smoothingGroup)
	{
	    for (Face face : smoothingGroup.getFaces())
	    {
	    	Vec3f v0 = smoothingGroup.getVertices().get(face.getIndices()[0]).getPos();
	    	Vec3f v1 = smoothingGroup.getVertices().get(face.getIndices()[1]).getPos();
	    	Vec3f v2 = smoothingGroup.getVertices().get(face.getIndices()[2]).getPos();
	        
	    	Vec3f normal = v1.sub(v0).cross(v2.sub(v0)).normalize();
	        
	    	smoothingGroup.getVertices().get(face.getIndices()[0]).setNormal(
	    			smoothingGroup.getVertices().get(face.getIndices()[0]).getNormal().add(normal));
	    	smoothingGroup.getVertices().get(face.getIndices()[1]).setNormal(
	    			smoothingGroup.getVertices().get(face.getIndices()[1]).getNormal().add(normal));
	    	smoothingGroup.getVertices().get(face.getIndices()[2]).setNormal(
	    			smoothingGroup.getVertices().get(face.getIndices()[2]).getNormal().add(normal));
	    }

	    for (Vertex vertex : smoothingGroup.getVertices())
	    {	
	    	vertex.setNormal(vertex.getNormal().normalize());
	    }       
	}
	
	public static void generateNormalsCCW(SmoothingGroup smoothingGroup)
	{
		  for (Face face : smoothingGroup.getFaces())
		    {
		    	Vec3f v0 = smoothingGroup.getVertices().get(face.getIndices()[0]).getPos();
		    	Vec3f v1 = smoothingGroup.getVertices().get(face.getIndices()[1]).getPos();
		    	Vec3f v2 = smoothingGroup.getVertices().get(face.getIndices()[2]).getPos();
		        
		    	Vec3f normal = v2.sub(v0).cross(v1.sub(v0)).normalize();
		        
		    	smoothingGroup.getVertices().get(face.getIndices()[0]).setNormal(
		    			smoothingGroup.getVertices().get(face.getIndices()[0]).getNormal().add(normal));
		    	smoothingGroup.getVertices().get(face.getIndices()[1]).setNormal(
		    			smoothingGroup.getVertices().get(face.getIndices()[1]).getNormal().add(normal));
		    	smoothingGroup.getVertices().get(face.getIndices()[2]).setNormal(
		    			smoothingGroup.getVertices().get(face.getIndices()[2]).getNormal().add(normal));
		    }

		    for (Vertex vertex : smoothingGroup.getVertices())
		    {	
		    	vertex.setNormal(vertex.getNormal().normalize());
		    }     
	}
	
	public static Quaternion normalizePlane(Quaternion plane)
	{
		float mag;
		mag = (float) Math.sqrt(plane.getX() * plane.getX() + plane.getY() * plane.getY() + plane.getZ() * plane.getZ());
		plane.setX(plane.getX()/mag);
		plane.setY(plane.getY()/mag);
		plane.setZ(plane.getZ()/mag);
		plane.setW(plane.getW()/mag);
	
		return plane;
	}

	public static Vec4f normalizePlane(Vec4f plane)
	{
		float mag;
		mag = (float) Math.sqrt(plane.getX() * plane.getX() + plane.getY() * plane.getY() + plane.getZ() * plane.getZ());
		plane.setX(plane.getX()/mag);
		plane.setY(plane.getY()/mag);
		plane.setZ(plane.getZ()/mag);
		plane.setW(plane.getW()/mag);

		return plane;
	}
	
	public static Vec2f[] texCoordsFromFontMap(char x)
	{
		float x_ = (x%16)/16.0f;
		float y_ = (x/16)/16.0f;
		Vec2f[] texCoords = new Vec2f[4];
		texCoords[0] = new Vec2f(x_, y_ + 1.0f/16.0f);
		texCoords[1] = new Vec2f(x_, y_);
		texCoords[2] = new Vec2f(x_ + 1.0f/16.0f, y_ + 1.0f/16.0f);
		texCoords[3] = new Vec2f(x_ + 1.0f/16.0f, y_);
		
		return texCoords;
	}
}
