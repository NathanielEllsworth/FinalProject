package com.ironyard.controller.mvc;

import com.ironyard.data.Account;
import com.ironyard.data.TheUser;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * This is where the User can add, delete and create their Transaction History
 *
 * Created by nathanielellsworth on 11/4/16.
 */

@Controller
@RequestMapping(path = "/mvc/secure/user")
public class MvcTheUserController {



    @Autowired
    TheUserRepository userRepository = null;
    @Autowired
    AccountRepository accountRepository = null;


    /**
     *
     * @param id the one Transaction requested by the Http Servlet
     * @param request the one transaction be deleted
     * @return to the home page with the transaction deleted and the web page updated
     */
    @RequestMapping(value = "account/savings/delete", method = RequestMethod.GET)
    public String deleteTransaction(@RequestParam("id") Long id, HttpServletRequest request){
        accountRepository.delete(accountRepository.findOne(id));
        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }

    /**
     *
     * @param binder simple date format from web request to java object
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     *
     * @param date the transaction took place
     * @param type of transaction it is (Transfer, Credit, .etc)
     * @param description of the transactions purpose and the direction the funds are moving
     * @param debit the account (-) if the funds are going out
     * @param credit the account (+) if the funds are coming in
     * @param term of the Treasury Bull (how long the treasury bill will be held for)
     * @param tBillReturn The return the specified Treasury Bill will yield
     * @param bankReturn The return the bank will yield over the same amount of time
     * @param returnIncrease The Difference between the Treasury Bill yield and the Bank yield
     * @param availableBalance that can be used to buy Treasury Bills
     * @param request the Http Servlet to get the user in the sessions data
     * @return the session back to the home page
     */
    @RequestMapping(value = "account/savings/add", method = RequestMethod.POST)
    public String addTransaction(@RequestParam("date") Date date,
                                 @RequestParam("type") String type,
                                 @RequestParam("description") String description,
                                 @RequestParam("debit") String debit,
                                 @RequestParam("credit") String credit,
                                 @RequestParam("term") String term,
                                 @RequestParam("tBillReturn") String tBillReturn,
                                 @RequestParam("bankReturn") String bankReturn,
                                 @RequestParam("returnIncrease") String returnIncrease,
                                 //@RequestParam("postedBalance") String postedBalance,
                                 @RequestParam("availableBalance") String availableBalance,HttpServletRequest request){


        TheUser user = (TheUser)request.getSession().getAttribute("user");


        Account tmpA = new  Account();
        tmpA.setUserId(user.getId());
        // set properties
        tmpA.setDate(date);
        tmpA.setType(type);
        tmpA.setDescription(description);
        tmpA.setDebit(debit);
        tmpA.setCredit(credit);
        tmpA.setTerm(term);
        tmpA.settBillRate(tBillReturn);
        tmpA.setBankRate(bankReturn);
        tmpA.setRateDifference(returnIncrease);
        //tmpA.setPostedBalance(postedBalance);
        tmpA.setAvailableBalance(availableBalance);
        accountRepository.save(tmpA);


        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }
}
