package com.taylak.oyuntest;

import com.badlogic.gdx.Game;
import com.taylak.oyuntest.screens.MainMenu;
import com.taylak.oyuntest.screens.TestScreen1;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.Load();
		setScreen(new TestScreen1());
	}

}
