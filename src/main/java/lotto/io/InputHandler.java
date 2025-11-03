package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.constant.Constants.*;

public class InputHandler {
    public int readPurchaseAmount() {
        while (true) {
            try {
                String input = Console.readLine();
                int amount = Integer.parseInt(input);
                validatePurchaseAmount(amount);

                return amount;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_PREFIX + ERROR_PURCHASE_AMOUNT_NOT_NUMBER);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Lotto readWinningNumbers() {
        while (true) {
            try {
                String input = Console.readLine();
                List<Integer> numbers = Arrays.stream(input.split(NUMBER_DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                return new Lotto(numbers);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_PREFIX + ERROR_WINNING_NUMBERS_NOT_NUMBER);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String input = Console.readLine();
                int bonusNumber = Integer.parseInt(input);
                validateBonusNumber(bonusNumber, winningLotto);

                return bonusNumber;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_PREFIX + ERROR_BONUS_NUMBER_NOT_NUMBER);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validatePurchaseAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_PURCHASE_AMOUNT_UNIT);
        }
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_PURCHASE_AMOUNT_MINIMUM);
        }
    }

    private void validateBonusNumber(int bonusNumber, Lotto winningLotto) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_BONUS_NUMBER_RANGE);
        }
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
