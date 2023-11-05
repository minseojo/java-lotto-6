package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.exception.LottoException;
import lotto.exception.LottoStoreException;

import java.util.List;

import static lotto.constant.LottoConstants.LOTTO_PRICE;
import static lotto.constant.LottoConstants.MAX_LOTTO_PRICE_PER_USER;

public class LottoPurchaseService {
    private final LottoAutoGenerateService lottoAutoGenerateService;
    private final LottoManualGenerateService lottoManualGeneratorService;

    private static final LottoPurchaseService instance = new LottoPurchaseService();

    private LottoPurchaseService() {
        this.lottoAutoGenerateService = LottoAutoGenerateService.getInstance();
        this.lottoManualGeneratorService = LottoManualGenerateService.getInstance();
    }

    public static LottoPurchaseService getInstance() {
        return instance;
    }

    public Lottos purchaseAutoLottos(int userMoney) throws LottoException {
        validate(userMoney);
        int lottoCount = userMoney / LOTTO_PRICE.getValue();
        return lottoAutoGenerateService.generate(lottoCount);
    }

    public Lotto purchaseManualLotto(List<String> list) throws LottoException {
        return lottoManualGeneratorService.generate(list);
    }

    private void validate(int money) throws LottoStoreException {
        if (!isValidMoney(money)) {
            throw new LottoStoreException(LottoStoreException.ErrorMessage.LOTTO_MIN_MONEY.getMessage());
        }
        if (isExceedMaxLottoAmountPerUser(money)) {
            throw new LottoStoreException(LottoStoreException.ErrorMessage.EXCEED_MAX_LOTTO_AMOUNT.getMessage());
        }
        if (!isDivisibleByLottoPrice(money)) {
            throw new LottoStoreException(LottoStoreException.ErrorMessage.NOT_DIVISIBLE.getMessage());
        }
    }

    private boolean isValidMoney(int money) {
        return money >= LOTTO_PRICE.getValue();
    }

    private boolean isDivisibleByLottoPrice(int money) {
        return (money % LOTTO_PRICE.getValue()) == 0;
    }

    private boolean isExceedMaxLottoAmountPerUser(int money) {
        return money > MAX_LOTTO_PRICE_PER_USER.getValue();
    }
}
