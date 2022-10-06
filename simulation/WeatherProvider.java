package simulation;

/*
 * RAIN
 • FOG
 • SUN
 • SNOW
 */

public final class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather;
    private int[][][] weatherMap;
    final static int LATITUDE_MAX = 360;
    final static int LONGITUDE_MAX = 360;
    final static int HEIGHT_MAX = 101;

    private WeatherProvider(){
        this.weather = new String[]{
            "RAIN",
            "FOG",
            "SUN",
            "SNOW"
        };
        this.weatherMap = new int[HEIGHT_MAX][LATITUDE_MAX][LONGITUDE_MAX];
        for ( int height = 0 ; height < HEIGHT_MAX ; ++height ) {
            for ( int y = 0 ; y < LATITUDE_MAX ; ++y ){
                for ( int x = 0 ; x < LONGITUDE_MAX ; ++x ){
                    this.weatherMap[height][y][x] = (int)Math.floor(Math.random() * 4);
                }
            }
        }
    }

    public static WeatherProvider getProvider(){
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        int height = coordinates.getHeight(); 
        int y = coordinates.getLatitude();
        int x = coordinates.getLongitude();
        return this.weather[this.weatherMap[height][y][x]];
    }
}
