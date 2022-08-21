package Simulation;

public class Baloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates){
        super(name, coordinates, "Baloon");
    }
    public void updateConditions(){
        String weather;
        weather = this.weatherTower.getWeather(this.coordinates);

        int longitude = super.coordinates.getLongitude();
        int latitude = super.coordinates.getLatitude();
        int height = super.coordinates.getHeight();
        int addLongitude = 0;
        int addLatitude = 0;
        int addHeight = 0;
        switch(weather){
            case "SUN":
                this.log("SUN Baloon");
                addLatitude = 10;
                addHeight = 2;
                break;
            case"RAIN":
                this.log("RAIN Baloon");
                addLatitude = 5;
                break;
            case"FOG":
                this.log("FOG Baloon");
                addLatitude = 3;
                break;
            case"SNOW":
                this.log("SNOW Baloon");
                addHeight = -15;
                break;
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
