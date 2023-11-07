package lotto.service;

import lotto.constant.LottoConstants;
import lotto.domain.LottoWinningRanking;

import java.util.EnumMap;
import java.util.Map;

public class LottoProfitService {
    private static final double PERCENTAGE = 100.0;
    private static final double ROUNDING_FACTOR = 10.0;

    private static final LottoProfitService instance = new LottoProfitService();

    private LottoProfitService() {}

    public static LottoProfitService getInstance() {
        return instance;
    }

    public double calculateProfit(EnumMap<LottoWinningRanking, Integer> winningRankingCountMap, int lottoCount) {
        int purchaseAmount = calculatePurchaseAmount(lottoCount);
        long totalWinningAmount = calculateWinningAmount(winningRankingCountMap);

        double profit = (double) (totalWinningAmount) / purchaseAmount * PERCENTAGE;
        return Math.round(profit * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private int calculatePurchaseAmount(int lottoCount) {
        return lottoCount * LottoConstants.LOTTO_PRICE.getValue();
    }

    private long calculateWinningAmount(EnumMap<LottoWinningRanking, Integer> winningRankingCountMap) {
        long totalWinningAmount = 0;

        for (Map.Entry<LottoWinningRanking, Integer> value : winningRankingCountMap.entrySet()) {
            if (value.getValue() > 0) {
                totalWinningAmount += calculateWinningAmountForRanking(value.getKey(), value.getValue());
            }
        }

        return totalWinningAmount;
    }

    private long calculateWinningAmountForRanking(LottoWinningRanking ranking, int count) {
        if (ranking.getWinningAmount() >= LottoWinningRanking.THIRD.getWinningAmount()) {
            return ranking.getWinningAmount();
        } else if (ranking.getWinningAmount() <= LottoWinningRanking.FOURTH.getWinningAmount()) {
            return ranking.getWinningAmount() * count;
        }

        return 0;
    }

}
