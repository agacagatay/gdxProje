package com.taylak.oyuntest;

import com.badlogic.gdx.Game;
import com.taylak.oyuntest.screens.MainMenu;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.Load();
		setScreen(new MainMenu());
	}

}
