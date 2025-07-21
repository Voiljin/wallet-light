package com.wallet.walletapi.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wallet.walletapi.domain.DomainEvent;

public abstract class AggregateRoot extends BaseEntity {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
