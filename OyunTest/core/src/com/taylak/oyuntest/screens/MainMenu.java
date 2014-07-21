package com.taylak.oyuntest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.taylak.oyuntest.Assets;

public class MainMenu extends BlankScreen {

	
	
	
	World world;
	Box2DDebugRenderer debugRenderer;
	
	static final float BOX_STEP = 1/60f;  
	static final int BOX_VELOCITY_ITERATIONS = 6;  
	static final int BOX_POSITION_ITERATIONS = 2;  
	static final float WORLD_TO_BOX = 0.02f;  
	static final float BOX_WORLD_TO = 50f; 
	private boolean showDebug = false;
	
	protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	protected float BUTTON_WIDTH = Gdx.app.getGraphics().getWidth()/7;
	protected float BUTTON_HEIGHT = Gdx.app.getGraphics().getHeight()/8;
	protected TextButton debugButton;
	
	public MainMenu() {
		super();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//addCameraControl(0, 0, width, height);
		
	 debugButton = new TextButton("Debug", skin);
	 debugButton.setBounds(width - BUTTON_WIDTH, height - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
	 debugButton.addListener(new ClickListener())
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

		renderText();
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
}
