package com.example.documentmanagement.util;

public class Server {
    public static String localhost="192.168.1.118";
    public static String LinkToLogin = "http://" + localhost + "/phpcodeDocumentMNG/login.php" ;
    public static String LinkshowApprovedReceive = "http://" + localhost + "/phpcodeDocumentMNG/getreceiveapproved.php" ;
    public static String LinkshowApprovedSend = "http://" + localhost + "/phpcodeDocumentMNG/getpprovedsend.php" ;
    public static String LinkshowReceiveWait = "http://" + localhost + "/phpcodeDocumentMNG/getreceiveawait.php" ;
    public static String LinkshowSendWait = "http://" + localhost + "/phpcodeDocumentMNG/getsendwait.php" ;

    public static String LinkShowAllRom = "http://" + localhost + "/phpcodeDocumentMNG/getallroom.php" ;
    public static String LinkShowAllLevel = "http://" + localhost + "/phpcodeDocumentMNG/getallLevel.php" ;
    public static String LinkShowAllDocType = "http://" + localhost + "/phpcodeDocumentMNG/getAllDocType.php" ;
    public static String LinkinsertDoccument = "http://" + localhost + "/phpcodeDocumentMNG/insertDoccument.php" ;
    public static String LinkToRecall= "http://" + localhost + "/phpcodeDocumentMNG/recall.php" ;
    public static String LinkToConfirm= "http://" + localhost + "/phpcodeDocumentMNG/updateconfirm.php" ;
    public static String LinkToDeleteDoc= "http://" + localhost + "/phpcodeDocumentMNG/deleteDoc.php" ;


    public static String LinkgetListAccount = "http://" + localhost + "/phpcodeDocumentMNG/getListAccount.php" ;
    public static String LinkInsertAccount = "http://" + localhost + "/phpcodeDocumentMNG/insertAccount.php" ;


}
