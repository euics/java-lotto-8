package lotto.constant;

public class Constants {
    public static final int LOTTO_PRICE = 1000;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final String NUMBER_DELIMITER = ",";
    public static final String ERROR_PREFIX = "[ERROR]";
    
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.";
    public static final String WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    public static final String STATISTICS_TITLE = "당첨 통계";
    public static final String STATISTICS_DIVIDER = "---";
    public static final String RANK_FORMAT = "%d개 일치 (%,d원) - %d개";
    public static final String RANK_WITH_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    public static final String RETURN_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
    
    public static final String ERROR_PURCHASE_AMOUNT_NOT_NUMBER = " 구입 금액은 숫자로 입력해 주세요.";
    public static final String ERROR_PURCHASE_AMOUNT_UNIT = " 구입 금액은 1,000원 단위로 입력해 주세요.";
    public static final String ERROR_PURCHASE_AMOUNT_MINIMUM = " 구입 금액은 최소 1,000원 이상이어야 합니다.";
    public static final String ERROR_WINNING_NUMBERS_NOT_NUMBER = " 로또 번호는 숫자로 입력해 주세요.";
    public static final String ERROR_LOTTO_NUMBER_COUNT = " 로또 번호는 6개여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_RANGE = " 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_DUPLICATE = " 로또 번호에 중복된 숫자가 있습니다.";
    public static final String ERROR_BONUS_NUMBER_NOT_NUMBER = " 보너스 번호는 숫자로 입력해 주세요.";
    public static final String ERROR_BONUS_NUMBER_RANGE = " 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String ERROR_BONUS_NUMBER_DUPLICATE = " 보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    
    private Constants() {
    }
}

