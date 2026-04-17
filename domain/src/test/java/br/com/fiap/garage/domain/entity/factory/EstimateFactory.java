package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Estimate;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EstimateFactory {

    private final Estimate.EstimateBuilder<?, ?> builder;

    public static EstimateFactory createEstimate() {
        return new EstimateFactory(Estimate.builder());
    }

    public Estimate.EstimateBuilder<?, ?> withAllFields() {
        return builder;
    }
}