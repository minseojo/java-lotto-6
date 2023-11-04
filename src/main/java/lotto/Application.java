package lotto;

import lotto.controller.LottoController;
import lotto.domain.generator.LottoAutoGenerator;
import lotto.domain.profit.LottoProfitCalculator;
import lotto.domain.winning.WinningRankingCalculator;
import lotto.domain.generator.LottoManualGenerator;
import lotto.domain.store.LottoStore;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator();
        LottoStore lottoStore = new LottoStore(lottoAutoGenerator, lottoManualGenerator);
        WinningRankingCalculator winningRankingCalculator = new WinningRankingCalculator();
        LottoProfitCalculator lottoProfitCalculator = new LottoProfitCalculator();

        LottoController lottoController = new LottoController(inputView, outputView, lottoStore, winningRankingCalculator, lottoProfitCalculator);
        lottoController.run();
    }
}
