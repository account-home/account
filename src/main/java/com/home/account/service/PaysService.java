package com.home.account.service;

import com.home.account.dictionary.Pays;

import java.util.List;

public interface PaysService {
    boolean addPays(Pays pays);
    boolean deletePays(String pay_id);
    boolean updatePays(Pays pays);
    List<Pays> getPays();

    
}
