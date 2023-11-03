package lotto.view;

public enum OutputMessage {
    REQUEST_PURCHASE_AMOUNT("구입금액을 입력해 주세요.\n"),
    REQUEST_WINNING_NUMBERS("당첨 번호를 입력해 주세요.\n"),
    REQUEST_BONUS_NUMBER("보너스 번호를 입력해 주세요.\n"),

    RESPONSE_PURCHASE_COUNT("%s개를 구매했습니다.\n"),
    RESPONSE_WINNING_NUMBERS("[%s]\n"),
    RESPONSE_WINNING_STATISTICS_HEADER("당첨 통계\n---\n"),
    RESPONSE_WINNING_STATISTICS_BODY("%s"),
    RESPONSE_TOTAL_RETURN("총 수익률은 %s%%입니다.\n");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
