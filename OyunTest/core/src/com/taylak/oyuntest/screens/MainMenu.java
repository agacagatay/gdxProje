package com.taylak.oyuntest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import com.taylak.oyuntest.Assets;

public class MainMenu extends BlankScreen implements GestureListener {

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.02f;
	static final float BOX_WORLD_TO = 50f;
	private boolean showDebug = true;

	protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	protected float BUTTON_WIDTH = Gdx.app.getGraphics().getWidth() / 5;
	protected float BUTTON_HEIGHT = Gdx.app.getGraphics().getHeight() / 10;
	protected TextButton debugButton;

	public MainMenu() {
		super();

	}

	@Override
	public void show() {

		// TODO Auto-generated method stub
		// addCameraControl(0, 0, width, height);

		debugRenderer = new Box2DDebugRenderer();

		debugButton = new TextButton("Debug", skin);
		debugButton.setBounds(width - BUTTON_WIDTH, height - BUTTON_HEIGHT * 2,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		// debugButton.setPosition(width - BUTTON_WIDTH, height -
		// BUTTON_HEIGHT);

		debugButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				showDebug = !showDebug;
			}

		});

		stageui.addActor(debugButton);
		stageui.getViewport().update((int) width, (int) height);

		InputMultiplexer im = new InputMultiplexer(stageui, stage,
				new GestureDetector(this));
		Gdx.input.setInputProcessor(im);
		Gdx.input.setCatchBackKey(true);

		world = new World(new Vector2(0, -9.81f), true);

		// Shape shape = Box2DFactory.createCircleShape(0.5f);
		// FixtureDef fixtureDef = Box2DFactory.createFixture(shape, 2.5f,
		// 0.25f, 0.8f, false);
		// Box2DFactory.createBody(world, BodyType.DynamicBody, fixtureDef, new
		// Vector2(7, 5));
		//
		// /* Create the ramp */
		// Vector2[] rampVertices = new Vector2[] { new Vector2(-2.5f, -1), new
		// Vector2(2.5f, 1) };
		// shape = Box2DFactory.createChainShape(rampVertices);
		// fixtureDef = Box2DFactory.createFixture(shape, 0, 0.3f, 0.3f, false);
		// Box2DFactory.createBody(world, BodyType.StaticBody, fixtureDef, new
		// Vector2(6.5f, 0));
		//
		// /* Create the walls */
		// Box2DFactory.createWalls(world, camera.viewportWidth,
		// camera.viewportHeight, 1f);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0.6f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		stageui.act(delta);
		stageui.draw();
		if (showDebug)
			debugRenderer.render(world, cam.combined);

		renderText();
		world.step(delta, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
	}

	protected void renderText() {

		batchui.begin();
		Assets.font24.setColor(1, 1, 1, 1f);
		Assets.font24.drawMultiLine(batchui, "FPS: "
				+ Gdx.app.getGraphics().getFramesPerSecond(), 0, 24, width,
				HAlignment.LEFT);
		Assets.font24.drawMultiLine(batchui, String.valueOf(width) + " - "
				+ String.valueOf(height), 0, 48, width, HAlignment.LEFT);

		batchui.end();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		world.dispose();
		debugRenderer.dispose();
	}
}
