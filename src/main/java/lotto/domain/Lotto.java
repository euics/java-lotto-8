package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.constant.Constants.*;
import static lotto.constant.Constants.ERROR_LOTTO_NUMBER_DUPLICATE;
import static lotto.constant.Constants.ERROR_LOTTO_NUMBER_RANGE;
import static lotto.constant.Constants.ERROR_PREFIX;
import static lotto.constant.Constants.LOTTO_NUMBER_COUNT;
import static lotto.constant.Constants.LOTTO_PRICE;
import static lotto.constant.Constants.MAX_NUMBER;
import static lotto.constant.Constants.MIN_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_LOTTO_NUMBER_COUNT);
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean hasInvalidRange = numbers.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);

        if (hasInvalidRange) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_LOTTO_NUMBER_RANGE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_LOTTO_NUMBER_DUPLICATE);
        }
    }

    public int countMatches(lotto.domain.Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public String format() {
        return numbers.toString();
    }

    public static List<Lotto> generateLottos(int purchaseAmount) {
        int count = purchaseAmount / LOTTO_PRICE;

        return IntStream.range(0, count)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList());
    }

    private static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT);

        return new Lotto(numbers.stream()
                .sorted()
                .collect(Collectors.toList()));
    }
}
