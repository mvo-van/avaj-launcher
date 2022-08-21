package Simulation;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates){
        super(name, coordinates, "JetPlane");
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
        switch(weather){
            case "SUN":
                this.log("SUN JetPlane");
                addLatitude = 10;
                addHeight = 2;
                break;
            case"RAIN":
                this.log("RAIN JetPlane");
                addLatitude = 5;
                break;
            case"FOG":
                this.log("FOG JetPlane");
                addLatitude = 1;
                break;
            case"SNOW":
                this.log("SNOG JetPlane");
                addHeight = -7;
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
