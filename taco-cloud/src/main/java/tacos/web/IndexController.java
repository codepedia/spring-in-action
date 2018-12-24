package tacos.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class IndexController implements ErrorController{
    private final static String PATH = "/error";
    @Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "The resource you are trying to access is not mapped correctly";
    }
   
}


/* Add this class that implements error controller so we get a custom message
 * Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Sun Dec 23 13:42:25 EST 2018
There was an unexpected error (type=Not Found, status=404).
No message available
 * 
 * */
 