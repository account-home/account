package com.home.account.serviceimp;

import com.home.account.dao.DetailDao;
import com.home.account.entity.User;
import com.home.account.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class DetailServiceImp implements DetailService {

    @Autowired
    private DetailDao detailDao;

    @Override
    public boolean addDetail(User user) {
        detailDao.addDetail(user);
        return false;
    }

    @Override
    public boolean updateDetail(User user) {
        detailDao.updateDetail(user);
        return false;
    }

}
