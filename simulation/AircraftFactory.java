package simulation;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        switch (type) {
            case "Helicopter":
                return new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "JetPlane":
                return new JetPlane(name, new Coordinates(longitude, latitude, height));
            case "Baloon":
                return new Baloon(name, new Coordinates(longitude, latitude, height));
            default :
                return null;
        }
    }
}
