public class Game {
	private State currentState;

	public Game(State state) {
		this.currentState = state;
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public void changeCurrentState(State state) {
		this.currentState = state;
	}
}