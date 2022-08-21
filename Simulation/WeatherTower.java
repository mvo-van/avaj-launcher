package Simulation;

public class WeatherTower extends Tower{
    public String getWeather(Coordinates coordinates){
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    // TODO: changer la visibilite
    public void changeWeather(){
        this.conditionsChanged();
    }
}
