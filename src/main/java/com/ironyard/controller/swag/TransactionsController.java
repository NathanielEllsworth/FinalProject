//package com.ironyard.controller.swag;
//
//import com.ironyard.data.Transactions;
//import com.ironyard.repositories.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * Created by nathanielellsworth on 11/14/16.
// */
//@RestController
//@RequestMapping(path = "/rest/transactions")
//public class TransactionsController {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private TransactionRepository transRepo;
//
//
//
//    @RequestMapping(value = "list", method = RequestMethod.GET)
//    public Iterable<Transactions> listAll(@RequestParam("page") Integer page,
//                                          @RequestParam("size") Integer size,
//                                          @RequestParam(value = "sortby", required = false) String sortby,
//                                          @RequestParam(value = "dir", required = false)Sort.Direction direction){
//
//        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):", page, size, sortby, direction));
//
//
//        //Sorting Default
//        if(sortby == null){
//            sortby = "title";
//        }
//
//        //Auto sort direction default
//        if(direction == null){
//            direction = Sort.Direction.DESC;
//        }
//        Sort s = new Sort(direction, sortby);
//        PageRequest pr = new PageRequest(page, size, s);
//        Iterable<Transactions> found = transRepo.findAll(pr);
//        log.debug(String.format("End ListAll: %s", found));
//        return found;
//    }
//
//
//
//    @ExceptionHandler(value = Throwable.class)
//    public String nfeHandler(Throwable e){
//        log.error("Error in Transactions Controller", e);
//        return "Something went wrong (tc)";
//    }
//
//}
