package com.home.account.service;

import com.home.account.dictionary.Consumptions;

import java.util.List;

public interface ConsumptionsService {
    boolean updateConsumptions(Consumptions consumptions);
    boolean deleteConsumptions(String consumptions_id);
    boolean addConsumptions(Consumptions consumptions);
    List<Consumptions> getConsumptions();
}
