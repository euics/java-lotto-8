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
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼 일치하면 2등이다")
    void secondPlace() {
        LottoRank rank = LottoRank.valueOf(5, true);
        assertThat(rank).isEqualTo(LottoRank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000L);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼 불일치하면 3등이다")
    void thirdPlace() {
        LottoRank rank = LottoRank.valueOf(5, false);
        assertThat(rank).isEqualTo(LottoRank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000L);
    }

    @Test
    @DisplayName("4개 일치하면 4등이다")
    void fourthPlace() {
        LottoRank rank = LottoRank.valueOf(4, false);
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000L);
    }

    @Test
    @DisplayName("3개 일치하면 5등이다")
    void fifthPlace() {
        LottoRank rank = LottoRank.valueOf(3, false);
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000L);
    }

    @Test
    @DisplayName("당첨 조건에 맞지 않으면 NONE이다")
    void none() {
        LottoRank rank = LottoRank.valueOf(2, false);
        assertThat(rank).isEqualTo(LottoRank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0L);
    }

    @Test
    @DisplayName("매치 개수를 올바르게 반환한다")
    void getMatchCount() {
        assertThat(LottoRank.FIRST.getMatchCount()).isEqualTo(6);
        assertThat(LottoRank.SECOND.getMatchCount()).isEqualTo(5);
        assertThat(LottoRank.THIRD.getMatchCount()).isEqualTo(5);
        assertThat(LottoRank.FOURTH.getMatchCount()).isEqualTo(4);
        assertThat(LottoRank.FIFTH.getMatchCount()).isEqualTo(3);
    }
}

