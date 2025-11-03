package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    @DisplayName("6개 일치하면 1등이다")
    void firstPlace() {
        LottoRank rank = LottoRank.valueOf(6, false);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼 일치하면 2등이다")
    void secondPlace() {
        LottoRank rank = LottoRank.valueOf(5, true);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(30_000_000L);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼 불일치하면 3등이다")
    void thirdPlace() {
        LottoRank rank = LottoRank.valueOf(5, false);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(1_500_000L);
    }

    @Test
    @DisplayName("4개 일치하면 4등이다")
    void fourthPlace() {
        LottoRank rank = LottoRank.valueOf(4, false);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(50_000L);
    }

    @Test
    @DisplayName("3개 일치하면 5등이다")
    void fifthPlace() {
        LottoRank rank = LottoRank.valueOf(3, false);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(5_000L);
    }

    @Test
    @DisplayName("당첨 조건에 맞지 않으면 NONE이다")
    void none() {
        LottoRank rank = LottoRank.valueOf(2, false);
        assertThat(rank).isEqualTo(LottoRank.NONE);
        assertThat(rank.calculateTotalPrize(1)).isEqualTo(0L);
    }

    @Test
    @DisplayName("등급별 총 당첨금을 올바르게 계산한다")
    void calculateTotalPrize() {
        assertThat(LottoRank.FIRST.calculateTotalPrize(1)).isEqualTo(2_000_000_000L);
        assertThat(LottoRank.SECOND.calculateTotalPrize(2)).isEqualTo(60_000_000L);
        assertThat(LottoRank.THIRD.calculateTotalPrize(3)).isEqualTo(4_500_000L);
        assertThat(LottoRank.FOURTH.calculateTotalPrize(4)).isEqualTo(200_000L);
        assertThat(LottoRank.FIFTH.calculateTotalPrize(5)).isEqualTo(25_000L);
    }
}

