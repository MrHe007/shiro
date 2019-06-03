import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author bigguy_hc
 * @create 2019-06-02 22:39
 */
public class TestShiro {

    @Test
    public void testMd5(){

        String name = "admin";
        String salt = "admin";

        Md5Hash md5Hash = new Md5Hash(name, salt, 3);

        System.out.println(md5Hash);


    }

}
