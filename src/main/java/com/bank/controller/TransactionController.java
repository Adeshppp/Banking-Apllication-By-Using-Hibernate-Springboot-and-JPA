//package com.bank.controller;
//
//
//import com.bank.entity.Transaction;
//import com.bank.service.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/transactions")
//public class TransactionController {
//
//    @Autowired
//    TransactionService transactionService;
//
//
//    @GetMapping("/get")
//    public ResponseEntity<List<Transaction>> getTransactions(@RequestBody Long userId, @RequestBody String accountType ){
//        List<Transaction> transactions = transactionService.getTransactionByUserId(userId, accountType);
//        return ResponseEntity.ok(transactions);
//    }
//
//
//}
////=====================================================================================================
