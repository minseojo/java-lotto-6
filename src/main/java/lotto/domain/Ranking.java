package lotto.domain;

public enum Ranking {
    FIFTH(3, 5_000L),
    FOURTH(4, 50_000L),
    THIRD(5, 1_500_000L),
    SECOND(5, true, 30_000_000L),
    FIRST(6, 2_000_000_000L);

    private final int matchedNumberCount;
    private final boolean needsBonusNumber;
    private final long winningAmount;

    Ranking(int matchedNumberCount, long winningAmount) {
        this(matchedNumberCount, false, winningAmount);
    }

    Ranking(int matchedNumberCount, boolean needsBonusNumber, long winningAmount) {
        this.matchedNumberCount = matchedNumberCount;
        this.needsBonusNumber = needsBonusNumber;
        this.winningAmount = winningAmount;
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount;
    }

    public boolean getNeedsBonusNumber() {
        return needsBonusNumber;
    }

    public long getWinningAmount() {
        return winningAmount;
    }
}
