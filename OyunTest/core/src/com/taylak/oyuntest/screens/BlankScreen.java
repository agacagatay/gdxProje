package com.taylak.oyuntest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class BlankScreen implements Screen, InputProcessor {

	protected Stage stage, stageui;
	protected SpriteBatch batch, batchui;
	protected OrthographicCamera cam;
	protected float width;
	protected float height;

	protected CameraController2 controller;
	protected GestureDetector gestureDetector;

	public World world;
	Box2DDebugRenderer debugRenderer;

	public BlankScreen() {
		super();
		nesneleriOrnekle();
	}

	public void nesneleriOrnekle() {
		batch = new SpriteBatch();
		batchui = new SpriteBatch();
		stage = new Stage();
		stageui = new Stage();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(width, height);
		// cam.setToOrtho(false, width/2, height/2);
		// cam.update();

		// debugRenderer = new Box2DDebugRenderer();
		// stage.setViewport(new StretchViewport(width, height));
		InputMultiplexer im = new InputMultiplexer(stageui, stage);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public abstract void render(float delta);

	@Override
	public void resize(int width, int height) {
		width = Gdx.app.getGraphics().getWidth();
		height = Gdx.app.getGraphics().getHeight();

		Gdx.gl.glViewport(0, 0, width, height);

		cam.update();
		batch.setProjectionMatrix(cam.combined);

		stage.setViewport(new StretchViewport(width, height));
		stageui.setViewport(new StretchViewport(width, height));
	}

	@Override
	public abstract void show();

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		stageui.dispose();
		batch.dispose();
		batchui.dispose();
		// debugRenderer.dispose();
	}

	protected void addCameraControl(float x1, float x2, float y1, float y2) {
		controller = new CameraController2();
		controller.setBounds(x1, x2, y1, y2);
		gestureDetector = new GestureDetector(20, 0.5f, 0.8f, 0.15f, controller);

		InputMultiplexer im = new InputMultiplexer(stageui, stage,
				gestureDetector);
		Gdx.input.setInputProcessor(im);

	}

	protected class CameraController2 implements GestureListener {
		public float velX, velY;
		boolean flinging = false;
		float initialScale = 1;

		float boundX = width * cam.zoom / 2, boundX2 = 0, boundY = 0,
				boundY2 = height;

		public CameraController2() {
			// TODO Auto-generated constructor stub
		}

		public void updateBounds() {
			// default bounds
			boundX = width * cam.zoom / 2;
			boundX2 = width - width * cam.zoom / 2;
			boundY = height * cam.zoom / 2;
			boundY2 = height - height * cam.zoom / 2;

		}

		public boolean touchDown(float x, float y, int pointer, int button) {
			flinging = false;
			initialScale = cam.zoom;
			return false;
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			Gdx.app.log("GestureDetectorTest", "tap at " + x + ", " + y
					+ ", count: " + count);
			return false;
		}

		@Override
		public boolean longPress(float x, float y) {
			Gdx.app.log("GestureDetectorTest", "long press at " + x + ", " + y);
			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			Gdx.app.log("GestureDetectorTest", "fling " + velocityX + ", "
					+ velocityY);
			flinging = true;
			velX = cam.zoom * velocityX * 0.5f;
			velY = cam.zoom * velocityY * 0.5f;
			return false;
		}

		@Override
		public boolean pan(float x, float y, float deltaX, float deltaY) {
			// Gdx.app.log("GestureDetectorTest", "pan at " + x + ", " + y);

			cam.position.add(-checkBoundsX(deltaX) * cam.zoom,
					checkBoundsY(deltaY) * cam.zoom, 0);

			return false;
		}

		private float checkBoundsX(float deltaX) {
			if (cam.position.x >= boundX && deltaX < 0) {
				velX = 0;
				cam.position.x = boundX;
				return 0;
			}

			if (cam.position.x <= boundX2 && deltaX > 0) {
				velX = 0;
				cam.position.x = boundX2;
				return 0;
			}

			return deltaX;
		}

		private float checkBoundsY(float deltaY) {

			if (cam.position.y >= boundY && deltaY > 0) {
				velY = 0;
				cam.position.y = boundY;
				return 0;
			}

			if (cam.position.y <= boundY2 && deltaY < 0) {
				velX = 0;
				cam.position.y = boundY2;
				return 0;
			}

			return deltaY;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			Gdx.app.log("GestureDetectorTest", "pan stop at " + x + ", " + y);
			return false;
		}

		@Override
		public boolean zoom(float originalDistance, float currentDistance) {
			float ratio = originalDistance / currentDistance;

			cam.zoom = initialScale * ratio;
			if (cam.zoom < 0.1f)
				cam.zoom = 0.1f;
			if (cam.zoom > 100f)
				cam.zoom = 100f;

			// System.out.println(cam.zoom);
			return false;
		}

		private float dotProduct(Vector2 v1, Vector2 v2) {

			return v1.x * v2.x + v1.y * v2.y;

		}

		public Vector2 getNormalized(Vector2 v) {
			float l = v.len();
			if (l == 0)
				return new Vector2();
			else
				return new Vector2(v.x / l, v.y / l);
		}

		Vector2 tempInV1, tempInV2, tempV1, tempV2;

		@Override
		public boolean pinch(Vector2 initialFirstPointer,
				Vector2 initialSecondPointer, Vector2 firstPointer,
				Vector2 secondPointer) {
			// System.out.println(":" +
			// angle(initialFirstPointer.sub(initialSecondPointer),
			// firstPointer.sub(secondPointer)));
			// Gdx.app.log("Rotate", ":" +
			// angle(initialFirstPointer.sub(initialSecondPointer),
			// firstPointer.sub(secondPointer)));

			// tempV1 = initialFirstPointer.sub(initialSecondPointer);
			// tempV2 = firstPointer.sub(secondPointer);
			//
			// tempInV1 = initialFirstPointer;
			// tempInV2 = initialSecondPointer;
			//
			// //cam.rotate(0.1f);
			// cam.rotate(angle(tempV1, tempV2));
			return false;
		}

		public void update() {

			// updateBounds();

			if (Gdx.input.isKeyPressed(Keys.S))
				cam.zoom += 0.03;
			if (Gdx.input.isKeyPressed(Keys.W))
				cam.zoom -= 0.03;

			if (flinging) {
				velX *= 0.92f;
				velY *= 0.92f;

				cam.position.add(
						-checkBoundsX(velX) * Gdx.graphics.getDeltaTime(),
						checkBoundsY(velY) * Gdx.graphics.getDeltaTime(), 0);

				if (Math.abs(velX) < 0.01f)
					velX = 0;
				if (Math.abs(velY) < 0.01f)
					velY = 0;

			}
		}

		public void setBounds(float x1, float x2, float y1, float y2) {
			boundX = x1;
			boundX2 = x2;
			boundY = y1;
			boundY2 = y2;

		}
	}

}
