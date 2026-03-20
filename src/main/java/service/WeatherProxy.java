package service;

import java.util.HashMap;
import java.util.List;
import weather.Period;

// proxy implementation for caching
public class WeatherProxy implements IWeatherService{
    private IWeatherService realService;
    private HashMap<String, List<Period>> cache;
    public WeatherProxy(IWeatherService realService){
        this.realService = realService;
        // init the internal cache
        this.cache = new HashMap<>(); 
    }

    @Override
    public List<Period> getForecast(String region, int gridx, int gridy){
        // construct a unique cache key based on the region and grid coordinates
        String key = region + "-" + gridx + "-" + gridy;
        // if it is cached,then return cached data right away
        if (cache.containsKey(key)){
            System.out.println("Returning cached data: " + key);
            return cache.get(key);
        }
        // else call the real service, store the result, and return it
        System.out.println("Fetching from API: " + key);
        List<Period> result = realService.getForecast(region, gridx, gridy);
        // store in hashmap cache
        cache.put(key, result); 
        return result;
    }
}
