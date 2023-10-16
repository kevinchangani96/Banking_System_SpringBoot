package com.bank.Banking.Application.Services;


import com.bank.Banking.Application.Model.TransactionHistory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTransactionHistoryEmail(String recipientEmail, List<TransactionHistory> transactionHistoryList) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject("Transaction History");


            // Create an HTML table for the transaction history
            StringBuilder transactionHistoryHtml = new StringBuilder();
            transactionHistoryHtml.append("<html><head><style>");
            transactionHistoryHtml.append("body { font-family: Arial, sans-serif; }");
            transactionHistoryHtml.append("h2 { text-align: center; }");
            transactionHistoryHtml.append("table { border-collapse: collapse; width: 100%; border: 1px solid #ddd; }");
            transactionHistoryHtml.append("th, td { text-align: left; padding: 8px; }");
            transactionHistoryHtml.append("th { background-color: #f2f2f2; }");
            transactionHistoryHtml.append("tr:nth-child(even) { background-color: #f2f2f2; }");
            transactionHistoryHtml.append("</style></head><body>");
            transactionHistoryHtml.append("<h2>Transaction History</h2>");
            transactionHistoryHtml.append("<table>");
            transactionHistoryHtml.append("<tr><th>Date</th><th>Amount</th><th>Description</th></tr>");

            for (TransactionHistory history : transactionHistoryList) {
                transactionHistoryHtml.append("<tr>");
                transactionHistoryHtml.append("<td>").append(history.getTime()).append("</td>");
                transactionHistoryHtml.append("<td>").append(history.getAmount()).append("</td>");
                transactionHistoryHtml.append("<td>").append(history.getStatus()).append("</td>");
                transactionHistoryHtml.append("</tr>");
            }

            transactionHistoryHtml.append("</table></body></html>");

            // Set the HTML content as the email body
            helper.setText(transactionHistoryHtml.toString(), true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }


}