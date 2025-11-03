package lotto.io;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Statistics;

import java.util.List;

import static lotto.constant.Constants.*;

public class OutputHandler {
    public void printPurchaseAmountMessage() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(String.format(PURCHASE_COUNT_FORMAT, count));
    }

    public void printLottos(List<Lotto> lottos) {
        lottos.forEach(lotto -> System.out.println(lotto.format()));
        System.out.println();
    }

    public void printWinningNumbersMessage() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
    }

    public void printBonusNumberMessage() {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    public void printStatistics(Statistics statistics) {
        System.out.println();
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_DIVIDER);
        statistics.forEachRank(this::printRank);
        System.out.println(String.format(RETURN_RATE_FORMAT, statistics.calculateReturnRate()));
    }

    private void printRank(LottoRank rank, int count) {
        rank.format(RANK_FORMAT, RANK_WITH_BONUS_FORMAT, count, System.out::println);
    }
}
