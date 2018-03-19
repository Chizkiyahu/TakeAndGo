package model.beckend;

/**
 * Created by chezkiaho on 19.3.2018.
 */

public interface Backend {
    boolean checkUserPass (String username, String Password);
    boolean checkUserFree(String username);
}
