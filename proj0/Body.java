import java.lang.Math;

public class Body{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	private final static double GRAV_CONSTANT = 6.67 * Math.pow(10,-11);

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		return Math.sqrt(square(dx) + square(dy));
	}

	private static double square(double x){
		return x*x;
	}

	public double calcForceExertedBy(Body b){
		double dist_square = square(calcDistance(b));
		return this.mass * b.mass * GRAV_CONSTANT / dist_square;
	}

	public double calcForceExertedByX(Body b){
		double dx = b.xxPos - this.xxPos;
		if (dx < 0) {
			dx *= -1;
		}
		return calcForceExertedBy(b)*dx / calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
		double dy = b.yyPos - this.yyPos;
		if (dy < 0) {
			dy *= -1;
		}
		return calcForceExertedBy(b)*dy / calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double netX = 0;
		for (Body b : allBodys){
			if (b.equals(this)){
				continue;
			}
			double force = calcForceExertedByX(b);
			netX += force;
		}
		return netX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double netY = 0;
		for (Body b : allBodys){
			if (b.equals(this)){
				continue;
			}
			double force = calcForceExertedByY(b);
			netY += force;
		}
		return netY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}
