class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Point midPoint(Point point) {
        return new Point((this.x + point.getX())/2, (this.y + point.getY())/2);
    }

    public double angleTo(Point point) {
        return Math.atan2(point.getY() - this.y, point.getX() - this.x);
    }

    public Point moveTo(double angle, double distance) {
        return new Point(this.x + distance*Math.cos(angle), this.y + distance*Math.sin(angle));
    }

    public String toString() {
        return "point (" + String.format("%.3f", this.x) + ", " + String.format("%.3f", this.y) + ")";
    }
    
    public double distanceTo(Point point) {
        double distance = Math.sqrt(Math.pow(this.getX() - point.getX(), 2) + Math.pow(this.getY() - point.getY(), 2));
        return distance;
    }
    
    public boolean equalsTo(Point point) {
	if (this.x == point.getX() && this.y == point.getY()) {
		return true;
	} else {
		return false;
	}
     } 

}

