
/**
 * Planet class
 */
public class Planet
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;
    /**
     * Constructor for objects of class Planet
     */
    public Planet(double xP,double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    /** 
     *  Constructor for objects of class Planet that takes in a Planet p and returns an identical Planet
     */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    
    /**
     * Calculate the distance between two planets
     */
    public double calcDistance(Planet p)
    {
        return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos));
    }
    
    /** Calculate force exterted by p to given the planet */
    public double calcForceExertedBy(Planet p)
    {
        return (G*mass*p.mass)/(calcDistance(p)*calcDistance(p));
    }
    
    /** Calculate force exterted by p in x direction to given the planet */
    public double calcForceExertedByX(Planet p)
    {
        return (calcForceExertedBy(p)*(p.xxPos - xxPos))/(calcDistance(p));
    }
    
    /** Calculate force exterted by p in y direction to given the planet */
    public double calcForceExertedByY(Planet p)
    {
        return (calcForceExertedBy(p)*(p.yyPos - yyPos))/(calcDistance(p));
    }
    
    /** Calculate net force exterted by p in x direction to given the planet */
    public double calcNetForceExertedByX(Planet[] p_list)
    {
        double net_force_x = 0;
        for ( Planet p: p_list ){
            if (equals(p)){
                continue;
            }
            net_force_x += calcForceExertedByX(p);
        }
        return net_force_x;
    }
    
    /** Calculate net force exterted by p in y direction to given the planet */
    public double calcNetForceExertedByY(Planet[] p_list)
    {
        double net_force_y = 0;
        for ( Planet p: p_list ){
            if (equals(p)){
                continue;
            }
            net_force_y += calcForceExertedByY(p);
        }
        return net_force_y;
    }
    
    /** Update new position, velocity of the given object after dt interval */
    public void update(double dt,double Fx, double Fy)
    {
        double a_x = Fx/mass;
        double a_y = Fy/mass;
        xxVel = xxVel + dt*a_x;
        yyVel = yyVel + dt*a_y;
        xxPos = xxPos + dt*xxVel;
        yyPos = yyPos + dt*yyVel;
    }
}
