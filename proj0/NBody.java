public class NBody
{
    
    /** Read radius of the universe from the given data */
    public static double readRadius(String data)
    {
        In in = new In(data);
        int N = in.readInt();
        return in.readDouble();
    }
    
    /** Read planets' datas from the given data */
    public static Planet[] readPlanets(String data)
    {
        In in = new In(data);
        Planet[] planet_array = new Planet[in.readInt()];
        double R = in.readDouble();
        for (int i = 0; i < planet_array.length; i++)
        {
            planet_array[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return planet_array;
    }
    
    /**
     * Read command line arguments: 0th == double T, 1st == double dt , 2nd == String filename
     */
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        StdDraw.setScale(-radius,radius);     
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[5];
        double[] yForces = new double[5];
        double t = 0;
        
        /** Stimulation process */
        while (t < T){
            for (int i = 0; i < planets.length ; i++)
            {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            
            for (int i = 0; i < planets.length ; i++)
            {
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg"); 
            for (int i = 0; i < planets.length ; i++)
           {
               StdDraw.picture(planets[i].xxPos,planets[i].yyPos,"images/"+planets[i].imgFileName);  
           }
            
           StdDraw.show();
           StdDraw.pause(10);
           
           t += dt;
        }
    }
}
