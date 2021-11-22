package uet.oop.bomberman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

 
public class test 
{
   
    public static void main(String[] args) throws IOException {
        try {
            System.out.println(new File("res\\textures\\classic.png").getCanonicalPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}