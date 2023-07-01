package com.surv.object;

public abstract class Component{

    private GameObject parent;

    public void update(){};

    public void input(){};

    public void render(){};

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public com.surv.math.Transform getTransform()
    {
        return getParent().getTransform();
    }
}
