import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by linking on 16-7-13.
 */
public class TestTemp {
    public static void main(String argv[]){

        System.out.println("Time: " + new Date().getTime());
        File file = new File("../../newFile");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
