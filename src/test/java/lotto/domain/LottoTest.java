package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.constant.Constants.ERROR_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호가 6개일 때 정상 생성된다")
    void createLottoWithValidNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void createLottoWithInvalidCount() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("로또 번호가 범위를 벗어나면 예외가 발생한다 - 최소값 미만")
    void createLottoWithNumberBelowMin() {
        List<Integer> numbers = Arrays.asList(0, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("로또 번호가 범위를 벗어나면 예외가 발생한다 - 최대값 초과")
    void createLottoWithNumberAboveMax() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    void createLottoWithDuplicateNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("일치하는 번호의 개수를 정확히 계산한다")
    void countMatches() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));
        assertThat(lotto.countMatches(winningLotto)).isEqualTo(3);
    }

    @Test
    @DisplayName("일치하는 번호가 없으면 0을 반환한다")
    void countMatchesWithNoMatch() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
        assertThat(lotto.countMatches(winningLotto)).isEqualTo(0);
    }

    @Test
    @DisplayName("모든 번호가 일치하면 6을 반환한다")
    void countMatchesWithAllMatch() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto.countMatches(winningLotto)).isEqualTo(6);
    }

    @Test
    @DisplayName("특정 번호를 포함하고 있는지 확인한다")
    void contains() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(3)).isTrue();
        assertThat(lotto.contains(7)).isFalse();
    }
}
