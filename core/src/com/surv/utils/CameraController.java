package com.surv.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraController extends InputAdapter {
    public PerspectiveCamera camera;
    public float speed;
    public float rotationSpeed;

    private int previousX;
    private int previousY;

    public CameraController(PerspectiveCamera camera, float speed, float rotationSpeed) {
        this.camera = camera;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;
    }

    public void update() {
        Vector3 direction = new Vector3();
        if (Gdx.input.isKeyPressed(Keys.W)) {
            direction.set(camera.direction).nor().scl(speed);
            camera.position.add(direction);
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            direction.set(camera.direction).nor().scl(-speed);
            camera.position.add(direction);
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            direction.set(camera.direction).crs(camera.up).nor().scl(-speed);
            camera.position.add(direction);
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            direction.set(camera.direction).crs(camera.up).nor().scl(speed);
            camera.position.add(direction);
        } else if (Gdx.input.isKeyPressed(Keys.Q)) {
            camera.position.add(0, -speed, 0);
        } else if (Gdx.input.isKeyPressed(Keys.E)) {
            camera.position.add(0, speed, 0);
        }
        camera.update();
    }


    @Override
    public boolean scrolled(float amountX, float amountY) {
        speed -= amountY;
        if(speed < 0.f)
            speed = 0.f;
        else if(speed > 100.f)
            speed = 100.f;
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        previousX = screenX;
        previousY = screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float deltaX = (screenX - previousX) * rotationSpeed;
            float deltaY = (screenY - previousY) * rotationSpeed;

            Vector3 right = new Vector3();
            right.set(camera.direction).crs(camera.up).nor();

            camera.direction.rotate(camera.up, deltaX);
            camera.direction.rotate(right, deltaY);

            previousX = screenX;
            previousY = screenY;
        }
        return true;
    }


    public void attach() {
        Gdx.input.setInputProcessor(this);
    }

    public void detach() {
        Gdx.input.setInputProcessor(null);
    }
}
