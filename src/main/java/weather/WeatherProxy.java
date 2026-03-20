package weather;

import java.util.HashMap;
import java.util.List;

// STEP 5: Proxy implementation for caching
public class WeatherProxy implements IWeatherService {
    private IWeatherService realService;
    private HashMap<String, List<Period>> cache;

    public WeatherProxy(IWeatherService realService) {
        this.realService = realService;
        this.cache = new HashMap<>(); // Initialize the internal cache
    }

    @Override
    public List<Period> getForecast(String region, int gridx, int gridy) {
        // Construct a unique cache key based on the region and grid coordinates
        String key = region + "-" + gridx + "-" + gridy;
        
        // If cached -> return cached data instantly
        if (cache.containsKey(key)) {
            System.out.println("Returning cached data");
            return cache.get(key);
        }
        
        // Else -> call RealWeatherService, store result, return it
        System.out.println("Fetching from API");
        List<Period> result = realService.getForecast(region, gridx, gridy);
        
        cache.put(key, result); // store in hashmap cache
        return result;
    }
}
