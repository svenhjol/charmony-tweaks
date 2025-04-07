package svenhjol.charmony.tweaks.common.features.trade_improvements;

import svenhjol.charmony.api.WandererTradeProvider;
import svenhjol.charmony.core.Api;
import svenhjol.charmony.core.base.Setup;
import svenhjol.charmony.core.common.CommonRegistry;
import svenhjol.charmony.core.common.GenericTrades;

public class Providers extends Setup<TradeImprovements> {
    public Providers(TradeImprovements feature) {
        super(feature);
    }

    @Override
    public Runnable boot() {
        return () -> {
            Api.registerProvider(this);

            if (feature().charmonyModItems()) {
                var registry = CommonRegistry.forFeature(feature());

                Api.consume(WandererTradeProvider.class,
                    provider -> {
                        provider.getWandererTrades().forEach(
                            trade -> registry.wandererTrade(
                                () -> new GenericTrades.ItemsForEmeralds(trade.getItem(),
                                    trade.getCost(),
                                    trade.getCount(),
                                    0,
                                    1),
                                false));

                        provider.getRareWandererTrades().forEach(
                            trade -> registry.wandererTrade(
                                () -> new GenericTrades.ItemsForEmeralds(trade.getItem(),
                                    trade.getCost(),
                                    trade.getCount(),
                                    0,
                                    1),
                                true));
                    });
            }
        };
    }
}
