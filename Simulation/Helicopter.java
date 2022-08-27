package Simulation;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates){
        super(name, coordinates, "Helicopter");
    }
    public void updateConditions(){
        String weather;
        weather = this.weatherTower.getWeather(super.coordinates);
        int longitude = super.coordinates.getLongitude();
        int latitude = super.coordinates.getLatitude();
        int height = super.coordinates.getHeight();
        int addLongitude = 0;
        int addLatitude = 0;
        int addHeight = 0;
        switch (weather) {
            case "SUN" -> {
                this.log("T'as baisé ma femme, j'ai baisé ma femme, allez, on est quitte.");
                addLatitude = 10;
                addHeight = 2;
            }
            case "RAIN" -> {
                this.log("J’appelle pas ça voler, j’appelle ça tomber avec panache.");
                addLatitude = 5;
            }
            case "FOG" -> {
                this.log("Pourvu qu’elle y soit, pourvu qu’elle y soit ! Oh, elle y soit !");
                addLatitude = 1;
            }
            case "SNOW" -> {
                this.log("T'es mal placé dans la chaîne alimentaire pour faire ta grande gueule !");
                addHeight = -12;
            }
        }
        if(height + addHeight < 1 ){
            this.weatherTower.unregister(this);
            return;
        }
        else if(height + addHeight > 100){
            height = 50;
            addHeight = 50;
        }
        super.coordinates = new Coordinates((longitude + addLongitude) % 360, (latitude + addLatitude) % 360, height + addHeight);
    }

    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
    }
}
