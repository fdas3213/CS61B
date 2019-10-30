public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int NPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int NPlanets = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[NPlanets];
        for(int i=0;i<NPlanets;i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String s = in.readString();
            bodies[i] = new Body(xPos, yPos, vX, vY, m, s);
        }
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0, "images/starfield.jpg");

        for (Body b:bodies){
            b.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double time=0; time<T; time+=dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i< bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
