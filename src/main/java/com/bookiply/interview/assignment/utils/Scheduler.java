package com.bookiply.interview.assignment.utils;

import com.bookiply.interview.assignment.services.IHydrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@EnableScheduling
public class Scheduler {

    private final IHydrantService hydrantService;

    @Autowired
    public Scheduler(IHydrantService hydrantService){
        this.hydrantService = hydrantService;
    }

    @Scheduled(fixedRate= 2 *  60 * 1000) // 2 min
    public void scheduleRefreshCache() throws IOException { // Pre-fetch on application start + refresh cache
        hydrantService.getHydrantsOfNewYorkCity();
        System.out.println("Refreshing Cache -- " + new Date());
    }
}
