package com.home.account.dao;

import com.home.account.dictionary.Pays;

import java.util.List;

public interface PaysDao {
    boolean addPays(Pays pays);
    boolean deletePays(String  pay_id);
    boolean updatePays(Pays pays);
    List<Pays> getPays();


}
