package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public class ResumeController extends InteractorController {

	public ResumeController(Game game, State state) {
        super(game, state);
	}

	public void next() {
        this.state.next();
	}

	public void reset() {
		this.state.reset();
		this.game.reset();
	}

    @Override
	public void accept(InteractorControllersVisitor controllersVisitor) {
		assert controllersVisitor != null;
		controllersVisitor.visit(this);
	}

}
