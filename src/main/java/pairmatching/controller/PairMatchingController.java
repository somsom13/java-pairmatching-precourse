package pairmatching.controller;

import pairmatching.domain.FunctionMenu;
import pairmatching.view.InputView;

public class PairMatchingController {
    private final InputView inputView;

    public PairMatchingController() {
        this.inputView = new InputView();
    }

    public void start() {
        doFunction(selectMenu());
    }

    private FunctionMenu selectMenu() {
        return FunctionMenu.findRequestedMenu(inputView.chooseFunctionMenu());
    }

    private void doFunction(FunctionMenu menu) {

    }
}
