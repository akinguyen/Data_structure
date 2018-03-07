package Flik;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Write a description of class FlikTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlikTest
{
   @Test
   public void isSameNumberTest(){
      Integer a = 127;
      Integer b = 128;
      Integer c = 127;
      assertEquals(false,Flik.isSameNumber(a,b));
      assertEquals(true,Flik.isSameNumber(a,c));
      assertEquals(false,Flik.isSameNumber(b,c));
    }
}
