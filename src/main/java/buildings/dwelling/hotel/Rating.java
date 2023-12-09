package buildings.dwelling.hotel;

public enum Rating {
    ONE_STAR(0.25),
    TWO_STAR(0.5),
    THREE_STAR(1.0),
    FOUR_STAR(1.25),
    FIVE_STAR(1.5);

    private Double koeff;

    Rating(Double koeff) {
        this.koeff = koeff;
    }

    public Double getKoeff() {
        return koeff;
    }
}
