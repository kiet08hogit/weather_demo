package util;

/* Utility class for weather recommendations */
import java.util.HashMap;
import java.util.Map;

public class RecommendationUtil {

    private static class CityActivities {
        String rain;
        String snow;
        String sunny;
        String cozy;
        String defaultAct;

        CityActivities(String rain, String snow, String sunny, String cozy, String defaultAct) {
            this.rain = rain;
            this.snow = snow;
            this.sunny = sunny;
            this.cozy = cozy;
            this.defaultAct = defaultAct;
        }
    }

    private static final Map<String, CityActivities> CITY_MAP = new HashMap<>();

    static {
        // Chicago
        CITY_MAP.put("chicago", new CityActivities(
            "Weather's kinda mid, perfect excuse for a hotpot date no cap",
            "Winter arc activated, grab the fits we're going ice skating fr",
            "Main character energy at Lake Michigan rn, time to touch grass and run",
            "Weather is eating, perfect cozy vibes for a Gathers Boba run",
            "SCE bowling is the only right answer rn, let's get it"
        ));
        
        // Portland
        CITY_MAP.put("portland", new CityActivities(
            "Rain check, let's grab a lobster roll indoors",
            "Snow day, perfect time for visiting a lighthouse covered in snow",
            "Main character energy at Old Port, let's explore",
            "Cozy vibes, perfect for a local Portland cafe run",
            "Good day to walk the Portland trails"
        ));

        // Las Vegas
        CITY_MAP.put("las vegas", new CityActivities(
            "Wait it's raining in Vegas? Let's hit the indoor casinos",
            "Snow in Vegas? That's wild, stay inside and watch a show",
            "Vegas heat is no joke, pool party time",
            "Perfect weather to walk the Vegas Strip fr fr",
            "Let's hit up Fremont Street"
        ));

        // Boston
        CITY_MAP.put("boston", new CityActivities(
            "Rainy day, time to hide in the Boston Public Library",
            "Snow day in Boston, perfect for some clam chowder",
            "Excellent weather for walking the Freedom Trail",
            "Cozy vibes, let's get a cannoli in the North End",
            "Let's explore Boston Common fr"
        ));

        // New York
        CITY_MAP.put("new york", new CityActivities(
            "Bodega run in the rain fr fr",
            "Central park snowball fight, winter arc is here",
            "Main character energy running in Central Park",
            "Cozy coffee in Brooklyn, immaculate vibes",
            "Times Square is too crowded, let's explore SoHo"
        ));

        // Los Angeles
        CITY_MAP.put("los angeles", new CityActivities(
            "LA people can't drive in rain, stay inside no cap",
            "Snow in LA? The matrix is broken",
            "Perfect beach day in Santa Monica, vibes are eating",
            "Grabbing an Erewhon smoothie, it's a movie",
            "Hiking the Hollywood sign, go crazy"
        ));

        // Houston
        CITY_MAP.put("houston", new CityActivities(
            "Rain delay, let's grab some BBQ indoors",
            "Snow in Houston? We are not built for this",
            "Too hot, hiding in the Galleria",
            "Vibes are right for Discovery Green",
            "Let's hit up a Rockets game"
        ));

        // Phoenix
        CITY_MAP.put("phoenix", new CityActivities(
            "Monsoon season is wild, stay inside",
            "Snow in Phoenix? Go buy a lottery ticket",
            "It's literally an oven outside, AC goes hard",
            "Perfect for a desert hike before it gets too hot",
            "Let's grab tacos in downtown"
        ));

        // Philadelphia
        CITY_MAP.put("philadelphia", new CityActivities(
            "Grabbing a cheesesteak and escaping the rain",
            "Snow day, perfect excuse to stay in",
            "Running up the Rocky steps fr fr",
            "Cozy vibes in Rittenhouse Square",
            "Exploring South Street, immaculate vibes"
        ));

        // San Antonio
        CITY_MAP.put("san antonio", new CityActivities(
            "Riverwalk is flooded, stay inside",
            "Snow in Texas? Taking pics for the gram",
            "Perfect day for the Alamo, remember it fr fr",
            "Breakfast tacos are calling my name",
            "Chilling at the Pearl district"
        ));

        // San Diego
        CITY_MAP.put("san diego", new CityActivities(
            "Rain in SD? That's rare, watching movies",
            "Snow? Not happening, but keeping it chill",
            "Surfing at La Jolla, immaculate vibes",
            "Cozy weather for Balboa Park",
            "Gaslamp Quarter is the only right answer"
        ));

        // Dallas
        CITY_MAP.put("dallas", new CityActivities(
            "Dodging the Dallas rain in a cozy cafe",
            "Snowstorm? Best stay off the roads",
            "Perfect weather to explore Deep Ellum",
            "Grabbing some Tex-Mex, vibes are eating",
            "Let's check out the Reunion Tower"
        ));

        // San Jose
        CITY_MAP.put("san jose", new CityActivities(
            "Rainy vibes, perfect for coding in a cafe",
            "Snow? Yeah right, but nice hoodie weather",
            "Hiking Mission Peak today, taking the pics",
            "Boba run in Santana Row",
            "Silicon Valley vibes, let's explore downtown"
        ));

        // Austin
        CITY_MAP.put("austin", new CityActivities(
            "Avoiding the ATX traffic in the rain",
            "Snow day? UT is definitely canceled",
            "Paddleboarding on Lady Bird Lake fr",
            "Cozy vibes, perfect for grabbing some BBQ",
            "Live music on 6th Street is the only answer"
        ));

        // Jacksonville
        CITY_MAP.put("jacksonville", new CityActivities(
            "Florida rain hitting different, stay inside",
            "Snow in FL? No shot, but staying cozy",
            "Beach day at Jax Beach",
            "Perfect weather for the Riverside Arts Market",
            "Exploring downtown Jax"
        ));

        // San Francisco & SF
        CITY_MAP.put("sf", new CityActivities(
            "Karl the Fog brought rain, grabbing clam chowder",
            "Snow in SF? Stay warm in a cafe",
            "Golden Gate Bridge walk, taking the aesthetic pics",
            "Cable car ride, immaculate vibes",
            "Exploring Pier 39, let's go"
        ));
        CITY_MAP.put("san francisco", CITY_MAP.get("sf"));

        // Columbus
        CITY_MAP.put("columbus", new CityActivities(
            "Rainy day in Ohio, perfect for staying in",
            "Snow day! Sledding at Scioto Audubon",
            "Running the Olentangy Trail",
            "Cozy vibes in the Short North",
            "Checking out the German Village"
        ));

        // Fort Worth
        CITY_MAP.put("fort worth", new CityActivities(
            "Stockyards in the rain? Pass, going to a museum",
            "Snow day in Texas, stay off the roads",
            "Watching the daily cattle drive fr fr",
            "Cozy vibes, grabbing some steak",
            "Sundance Square is the vibe"
        ));

        // Indianapolis
        CITY_MAP.put("indianapolis", new CityActivities(
            "Rainy day, hiding out in a cafe",
            "Snow day! Perfect for staying cozy",
            "Walking the Canal Walk, immaculate vibes",
            "Grabbing food in Mass Ave",
            "Exploring downtown Indy"
        ));

        // Charlotte
        CITY_MAP.put("charlotte", new CityActivities(
            "Rain check, hitting up the Epicentre indoors",
            "Snow! Time to stay in and be cozy",
            "Perfect day for Freedom Park",
            "Cozy vibes in NoDa",
            "Let's explore Uptown Charlotte"
        ));

        // Seattle
        CITY_MAP.put("seattle", new CityActivities(
            "Just another rainy day, grabbing Starbucks",
            "Snow in Seattle? The city is shut down",
            "Pike Place Market vibes are immaculate today",
            "Cozy weather for the Space Needle",
            "Ferry ride across the sound"
        ));

        // Denver
        CITY_MAP.put("denver", new CityActivities(
            "Rain in the Mile High city, cozying up",
            "Massive snow? Perfect for hitting the slopes",
            "Hiking in the Rockies, main character vibes",
            "Grabbing coffee in LoDo",
            "Exploring Red Rocks Amphitheatre"
        ));

        // Washington DC / DC
        CITY_MAP.put("dc", new CityActivities(
            "Rainy day, perfect for Smithsonian museums",
            "Snow day! Sledding on Capitol Hill",
            "Walking the National Mall, aesthetic af",
            "Cozy vibes in Georgetown",
            "Exploring the monuments fr fr"
        ));
        CITY_MAP.put("washington dc", CITY_MAP.get("dc"));

        // Miami
        CITY_MAP.put("miami", new CityActivities(
            "Miami rain storm, time to sleep in",
            "Snow in Miami? Matrix is broken",
            "South Beach vibes, absolute movie",
            "Cozy weather, let's grab Cuban coffee",
            "Exploring Wynwood Walls"
        ));

        // Atlanta
        CITY_MAP.put("atlanta", new CityActivities(
            "Rainy day in the A, escaping to the Aquarium",
            "Snowpocalypse in Atlanta, stay inside!",
            "Walking the BeltLine, immaculate vibes",
            "Cozy vibes, grabbing some Waffle House",
            "Exploring Ponce City Market"
        ));
    }

    public static String getClothing(int temp, String weather) {
        String w = weather != null ? weather.toLowerCase() : "";
        if (w.contains("rain") || w.contains("showers")) {
            return "Rain jacket and waterproof shoes";
        } else if (w.contains("snow")) {
            return "Heavy winter coat, gloves, and boots";
        } else if (temp < 40) {
            return "Warm coat, scarf, and beanie";
        } else if (temp < 60) {
            return "Light jacket or sweater and long pants";
        } else if (temp < 75) {
            return "T-shirt and light pants";
        } else {
            return "Shorts, t-shirt, and sunglasses";
        }
    }

    // Get activity recommendation based on location, temperature and weather
    public static String getActivity(String location, int temp, String weather) {
        String w = weather != null ? weather.toLowerCase() : "";
        String loc = location != null ? location.toLowerCase() : "";

        CityActivities activities = null;
        for (String key : CITY_MAP.keySet()) {
            if (loc.contains(key)) {
                activities = CITY_MAP.get(key);
                break;
            }
        }

        if (activities != null) {
            if (w.contains("rain") || w.contains("showers") || w.contains("storm")) {
                return activities.rain;
            } else if (w.contains("snow") || w.contains("snow showers")) {
                return activities.snow;
            } else if (w.contains("sunny") || w.contains("clear") || w.contains("mostly sunny") || w.contains("partly sunny")) {
                return activities.sunny;
            } else if (temp >= 50 && temp <= 70 && !w.contains("rain") && !w.contains("snow")) {
                return activities.cozy;
            } else {
                return activities.defaultAct;
            }
        }

        // Default recommendations for other locations
        if (w.contains("rain") || w.contains("showers") || w.contains("storm")) {
            return "Perfect day for an indoor museum or movie";
        } else if (w.contains("snow")) {
            return "Build a snowman or drink hot cocoa inside";
        } else if (temp < 40) {
            return "Visit a cozy cafe or read a book indoors";
        } else if (temp > 85) {
            return "Great day for the pool or staying in the AC";
        } else if (w.contains("sunny") || w.contains("clear")) {
            return "Excellent weather for a nice walk or run!";
        } else {
            return "Good day for a casual outdoor stroll";
        }
    }
}
