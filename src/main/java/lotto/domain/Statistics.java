package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lotto.constant.Constants.*;

public class Statistics {
    private final Map<LottoRank, Integer> rankCounts;
    private final int purchaseAmount;

    public Statistics(Map<LottoRank, Integer> rankCounts, int purchaseAmount) {
        this.rankCounts = rankCounts;
        this.purchaseAmount = purchaseAmount;
    }

    public static Statistics calculate(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        Map<LottoRank, Integer> rankCounts = initializeRankCounts();

        lottos.forEach(lotto -> {
            LottoRank rank = calculateRank(lotto, winningLotto, bonusNumber);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        });

        int purchaseAmount = lottos.size() * LOTTO_PRICE;

        return new Statistics(rankCounts, purchaseAmount);
    }

    private static Map<LottoRank, Integer> initializeRankCounts() {
        return Stream.of(LottoRank.values())
                .collect(Collectors.toMap(
                        rank -> rank,
                        rank -> 0,
                        (existing, replacement) -> existing,
                        () -> new EnumMap<>(LottoRank.class)));
    }

    private static LottoRank calculateRank(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = lotto.countMatches(winningLotto);
        boolean hasBonus = lotto.contains(bonusNumber);

        return LottoRank.valueOf(matchCount, hasBonus);
    }

    public double calculateReturnRate() {
        long totalPrize = calculateTotalPrize();
        double rate = (double) totalPrize / purchaseAmount * 100;

        return Math.round(rate * 10.0) / 10.0;
    }

    public List<LottoRank> rankOrder() {
        return List.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST);
    }

    public int countRank(LottoRank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    private long calculateTotalPrize() {
        return Stream.of(LottoRank.values())
                .mapToLong(rank -> rank.calculateTotalPrize(rankCounts.get(rank)))
                .sum();
    }
}

