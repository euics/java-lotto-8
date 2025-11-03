package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.constant.Constants.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

class StatisticsTest {
    @Test
    @DisplayName("통계를 올바르게 계산한다 - 1등 당첨")
    void calculateStatisticsFirst() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        Map<LottoRank, Integer> counts = new HashMap<>();
        statistics.forEachRank(counts::put);

        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(0);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(0);
        assertThat(counts.get(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(0);
    }

    @Test
    @DisplayName("통계를 올바르게 계산한다 - 2등 당첨")
    void calculateStatisticsSecond() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        Map<LottoRank, Integer> counts = new HashMap<>();
        statistics.forEachRank(counts::put);

        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(0);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨이 없으면 모든 등급이 0개이다")
    void calculateStatisticsWithNoWin() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15)),
                new Lotto(Arrays.asList(20, 21, 22, 23, 24, 25))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        Map<LottoRank, Integer> counts = new HashMap<>();
        statistics.forEachRank(counts::put);

        assertThat(counts.get(LottoRank.FIRST)).isEqualTo(0);
        assertThat(counts.get(LottoRank.SECOND)).isEqualTo(0);
        assertThat(counts.get(LottoRank.THIRD)).isEqualTo(0);
        assertThat(counts.get(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익률을 올바르게 계산한다")
    void calculateReturnRate() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        int purchaseAmount = 2 * LOTTO_PRICE;
        long expectedPrize = LottoRank.FIRST.calculateTotalPrize(1);
        double expectedRate = (double) expectedPrize / purchaseAmount * 100;

        assertThat(statistics.calculateReturnRate()).isEqualTo(Math.round(expectedRate * 10.0) / 10.0);
    }

    @Test
    @DisplayName("5등만 당첨되면 수익률이 올바르게 계산된다")
    void calculateReturnRateFifth() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        Map<LottoRank, Integer> counts = new HashMap<>();
        statistics.forEachRank(counts::put);

        assertThat(counts.get(LottoRank.FIFTH)).isEqualTo(1);
        long totalPrize = LottoRank.FIFTH.calculateTotalPrize(1);
        double expectedRate = Math.round((double) totalPrize / LOTTO_PRICE * 100 * 10.0) / 10.0;

        assertThat(statistics.calculateReturnRate()).isEqualTo(expectedRate);
    }

    @Test
    @DisplayName("수익률이 0%일 때도 올바르게 계산한다")
    void calculateReturnRateZero() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15))
        );
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Statistics statistics = Statistics.calculate(lottos, winningLotto, bonusNumber);

        assertThat(statistics.calculateReturnRate()).isEqualTo(0.0);
    }
}

