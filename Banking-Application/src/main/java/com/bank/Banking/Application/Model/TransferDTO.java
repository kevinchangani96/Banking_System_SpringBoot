package com.bank.Banking.Application.Model;

import lombok.Data;

@Data
public class TransferDTO {
    private String SenderName;
    private  double senderAcBalance;
    private  String ReciverName;
    private  double reciverAcBalance;

}
