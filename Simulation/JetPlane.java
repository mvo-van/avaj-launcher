package Simulation;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates){
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
                this.log("- Celui qui dit qu'il l'a fait, je lui offre... un sandwich. - À quoi le sandwich ? - À la fraise.");
                addLatitude = 10;
                addHeight = 2;
                break;
            case"RAIN":
                this.log("Oh, ça m’en bouche un groin…");
                addLatitude = 5;
                break;
            case"FOG":
                this.log("Rho du calme il n’est pas toxique ! D’habitude faut pas me croire mais là il faut !");
                addLatitude = 1;
                break;
            case"SNOW":
                this.log("Les dodos n'ont jamais froid, la fin du monde ne passera pas !");
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
