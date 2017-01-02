package controller;

import model.Model;
import view.View;

/**
 * Class Controller
 *
 */

public class Controller implements ViewObserver {
	
	private Model m;
	private View v;
	
	/**
	 * Constructor
	 */
	public Controller(){
		
	}

	@Override
	public void rollDice() {
		int value = m.getRandomNumber();
		v.setDiceValue(value);

	}

	@Override
	public void instruction() {

	}

	@Override
	public void quit() {
		m.reset();

	}

	@Override
	public void resetGame() {
		m.reset();

	}

	@Override
	public void play() {
		m.newGame();

	}

}
