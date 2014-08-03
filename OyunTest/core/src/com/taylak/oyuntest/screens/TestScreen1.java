package com.taylak.oyuntest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TestScreen1 implements Screen {
	// private SpriteBatch batch;
	// private Texture texture;
	// private Sprite sprite;

	public class MyActor extends Actor {
		Texture texture = new Texture(Gdx.files.internal("data/teapot.png"));
		float actorX = 0, actorY = 0;
		public boolean started = false;

		@Override
		public void draw(Batch batch, float alpha) {
			setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
			batch.draw(texture, actorX, actorY);
			addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					((MyActor) event.getTarget()).started = true;
					return true;
				}

			});
		}

		int artis = 5;

		@Override
		public void act(float delta) {
			// TODO Auto-generated method stub
			// super.act(delta);

			if (started) {

				if (actorX + texture.getWidth() > Gdx.graphics.getWidth()) {
					artis = -artis;
				}
				if (actorX < 0) {
					artis = -artis;
				}
				actorX += artis;
			}

		}

	}

	private Stage stage;

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
	 	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	 
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));

		MyActor myActor = new MyActor();
		myActor.setTouchable(Touchable.enabled);
		stage.addActor(myActor);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		stage.dispose();
	}

}
