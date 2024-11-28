package es.cheste;

class NEO {
    private final String name;
    private final double positionNEO;
    private final double velocityNEO;

    public NEO(String name, double positionNEO, double velocityNEO) {
        this.name = name;
        this.positionNEO = positionNEO;
        this.velocityNEO = velocityNEO;
    }

    public String getName() {
        return name;
    }

    public double getPositionNEO() {
        return positionNEO;
    }

    public double getVelocityNEO() {
        return velocityNEO;
    }
}
