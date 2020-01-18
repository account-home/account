package com.home.account.dao;

import com.home.account.dictionary.Consumptions;

import java.util.List;

public interface ConsumptionsDao {
    boolean updateConsumptions(Consumptions consumptions);
    boolean deleteConsumptions(String  consumptions_id);
    boolean addConsumptions(Consumptions consumptions);
    List<Consumptions> getConsumptions();
}
