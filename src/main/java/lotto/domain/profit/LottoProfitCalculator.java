package lotto.domain.profit;

import lotto.contant.LottoConstants;
import lotto.domain.winning.WinningRanking;

import java.util.EnumMap;

public class LottoProfitCalculator {
    public double calculateProfit(EnumMap<WinningRanking, Integer> winningRankingCountMap, int lottoCount) {
        int purchaseAmount = calculatePurchaseAmount(lottoCount);
        int winningAmount = calculateWinningAmount(winningRankingCountMap);

        double profit = (double) (winningAmount) / purchaseAmount * 100.0;
        return Math.round(profit * 10.0) / 10.0;
    }

    private static int calculatePurchaseAmount(int lottoCount) {
        return lottoCount * LottoConstants.LOTTO_PRICE.getValue();
    }
    private static int calculateWinningAmount(EnumMap<WinningRanking, Integer> winningRankingCountMap) {
        int totalWinningAmount = 0;

        // (1등, 2등, 3등)은 한번만 더한다. (원래 n명이 1, 2, 3등에 당첨 되면 그 금액을 n으로 나눠 가진다.
        // 현재는 사용자가 한명 관점에서 계산하므로, 공통된 사람의 중복여부는 관리하지 않는다.
        if (winningRankingCountMap.getOrDefault(WinningRanking.FIRST, 0) > 0) {
            totalWinningAmount += WinningRanking.FIRST.getWinningAmount();
        }
        if (winningRankingCountMap.getOrDefault(WinningRanking.SECOND, 0) > 0) {
            totalWinningAmount += WinningRanking.SECOND.getWinningAmount();
        }
        if (winningRankingCountMap.getOrDefault(WinningRanking.THIRD, 0) > 0) {
            totalWinningAmount += WinningRanking.THIRD.getWinningAmount();
        }

        // 4등과 5등은 상금을 중복해서 더함 (4, 5등은 n명이 걸려도 모두 같은 정해진 금액을 받는다.)
        totalWinningAmount += WinningRanking.FOURTH.getWinningAmount() * winningRankingCountMap.getOrDefault(WinningRanking.FOURTH, 0);
        totalWinningAmount += WinningRanking.FIFTH.getWinningAmount() * winningRankingCountMap.getOrDefault(WinningRanking.FIFTH, 0);

        return totalWinningAmount;
    }

}