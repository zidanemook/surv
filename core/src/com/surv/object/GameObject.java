package com.surv.object;

import java.util.HashMap;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.surv.math.Transform;

public class GameObject{
    protected boolean drawWireframe = false;
    protected boolean refreshMesh = true;
    private HashMap<String, Component> components;
    private Transform transform;

    public GameObject(PerspectiveCamera camera)
    {
        components = new HashMap<>();
        transform = new Transform(camera);
    }

    public void addComponent(String string, Component component)
    {
        component.setParent(this);
        components.put(string, component);
    }

    public void update()
    {
        for (String key : components.keySet()) {
            components.get(key).update();
        }
    }

    public void input()
    {
        for (String key : components.keySet()) {
            components.get(key).input();
        }
    }

    public void render()
    {
        for (String key : components.keySet()) {
            components.get(key).render();
        }
    }

    public HashMap<String, Component> getComponents() {
        return components;
    }

    public Component getComponent(String component)
    {
        return this.components.get(component);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    protected void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
