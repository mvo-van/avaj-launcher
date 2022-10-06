package simulation;

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
            case "SUN" -> {
                this.log("Mais si c'est pas un animal qui l'a tué, c'est p'tet un végétal !...");
                addLatitude = 10;
                addHeight = 2;
            }
            case"RAIN" -> {
                this.log("Nous vous devons une reconnaissance éternelle !");
                addLatitude = 5;
            }
            case"FOG" -> {
                this.log("- Tu vois Bob, Ted va au travail à pied. - Tu parles, il a aucun mérite, il a qu’à enjamber le quartier !");
                addLatitude = 3;
            }
            case"SNOW" -> {
                this.log("Eh les gars, vous allez vous marrer... J'ai avalé un glaçon !");
                addHeight = -15;
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
