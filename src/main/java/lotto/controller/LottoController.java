package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Statistics;
import lotto.io.InputHandler;
import lotto.io.OutputHandler;

import java.util.List;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public LottoController() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    public void run() {
        outputHandler.printPurchaseAmountMessage();
        int purchaseAmount = inputHandler.readPurchaseAmount();
        List<Lotto> lottos = Lotto.generateLottos(purchaseAmount);
        outputHandler.printPurchaseCount(lottos.size());
        outputHandler.printLottos(lottos);

        outputHandler.printWinningNumbersMessage();
        Lotto winningLotto = inputHandler.readWinningNumbers();
        System.out.println();
        outputHandler.printBonusNumberMessage();
        int bonusNumber = inputHandler.readBonusNumber(winningLotto);
        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);
        outputHandler.printStatistics(statistics);
    }
}
