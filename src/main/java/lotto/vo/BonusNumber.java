package lotto.vo;


import lotto.contant.LottoConstants;
import lotto.exception.LottoException;

import java.util.Objects;

public final class BonusNumber {
    private final LottoNumber value;

    public BonusNumber(Integer value) throws LottoException {
        validate(value);
        this.value = new LottoNumber(value);
    }

    private void validate(Integer value) throws LottoException {
        if (value < LottoConstants.LOTTO_MIN_NUMBER.getValue()) {
            throw new LottoException("로또 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        if (value > LottoConstants.LOTTO_MAX_NUMBER.getValue()) {
            throw new LottoException("로또 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public LottoNumber value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        BonusNumber other = (BonusNumber) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
