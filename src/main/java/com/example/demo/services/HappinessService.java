package com.example.demo.services;

import com.example.demo.dao.HappinessDao;
import com.example.demo.domain.Happiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nicole
 */
@Service
public class HappinessService {
    @Autowired
    private HappinessDao happinessDao;

    public List<Happiness> selectService(String city){
        return happinessDao.findHappinessByCity(city);
    }

    @Transactional
    public void insertService(){
        happinessDao.insertHappiness("西安", 9421);
        //int a = 1 / 0; //模拟故障
        happinessDao.insertHappiness("长安", 1294);
        happinessDao.insertHappiness("浙江", 1298);
    }
}
