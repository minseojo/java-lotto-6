package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.winning.LottoWinningRanking;
import lotto.domain.winning.LottoWinningSet;
import lotto.vo.LottoNumber;

import java.util.EnumMap;
public class LottoWinningRankingCountService {
    public EnumMap<LottoWinningRanking, Integer> countWinningRankings(Lottos userLottos, LottoWinningSet winningLotto) {
        EnumMap<LottoWinningRanking, Integer> rankingCountMap = new EnumMap<>(LottoWinningRanking.class);

        for (Lotto lotto : userLottos.lottos()) {
            int matchedNumberCount = countMatchedNumbers(lotto, winningLotto);
            boolean needsBonusNumber = hasBonusNumber(lotto, winningLotto);

            LottoWinningRanking ranking = calculateRanking(matchedNumberCount, needsBonusNumber);
            rankingCountMap.put(ranking, rankingCountMap.getOrDefault(ranking, 0) + 1);
        }

        return rankingCountMap;
    }

    private int countMatchedNumbers(Lotto lotto, LottoWinningSet winningLotto) {
        int matchedNumberCount = 0;
        for (LottoNumber number : winningLotto.getLotto().getNumbers()) {
            if (lotto.getNumbers().contains(number)) {
                matchedNumberCount++;
            }
        }
        return matchedNumberCount;
    }

    private boolean hasBonusNumber(Lotto lotto, LottoWinningSet lottoWinningSet) {
        return lotto.getNumbers().contains(lottoWinningSet.getBonusNumber());
    }

    private LottoWinningRanking calculateRanking(int matchedNumberCount, boolean needsBonusNumber) {
        // for 문으로 2, 3등에 대한 조건을 만들면 되지만, 코드 이해는 이 방법이 더 쉬워보인다.
        if (matchedNumberCount == LottoWinningRanking.FIRST.getMatchedNumberCount()) {
            return LottoWinningRanking.FIRST;
        }
        if (matchedNumberCount == LottoWinningRanking.SECOND.getMatchedNumberCount() &&
                needsBonusNumber == LottoWinningRanking.SECOND.getNeedsBonusNumber()) {
            return LottoWinningRanking.SECOND;
        }
        if (matchedNumberCount == LottoWinningRanking.THIRD.getMatchedNumberCount() &&
                needsBonusNumber == LottoWinningRanking.THIRD.getNeedsBonusNumber()) {
            return LottoWinningRanking.THIRD;
        }
        if (matchedNumberCount == LottoWinningRanking.FOURTH.getMatchedNumberCount()) {
            return LottoWinningRanking.FOURTH;
        }
        if (matchedNumberCount == LottoWinningRanking.FIFTH.getMatchedNumberCount()) {
            return LottoWinningRanking.FIFTH;
        }

        return LottoWinningRanking.NONE;
    }
}
