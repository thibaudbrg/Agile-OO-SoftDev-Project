public enum ShipType {
    CARRIER(5, "assets/carrier.png"),
    BATTLESHIP(4, "assets/battleship.png"),
    CRUISER(3, "assets/cruiser.png"),
    SUBMARINE(3, "assets/submarine.png"),
    DESTROYER(2, "assets/destroyer.png");

    public final Integer length;
    public final String image;

    ShipType(Integer length, String image) {
        this.length = length;
        this.image = image;
    }
}
