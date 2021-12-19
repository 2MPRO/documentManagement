package com.example.documentmanagement.util;

public class Server {
    public static String localhost="192.168.1.118";
    public static String LinkToLogin = "http://" + localhost + "/phpcodeDocumentMNG/login.php" ;
    public static String LinkshowApprovedReceive = "http://" + localhost + "/phpcodeDocumentMNG/getreceiveapproved.php" ;
    public static String LinkshowApprovedSend = "http://" + localhost + "/phpcodeDocumentMNG/getpprovedsend.php" ;
    public static String LinkshowReceiveWait = "http://" + localhost + "/phpcodeDocumentMNG/getreceiveawait.php" ;
    public static String LinkshowSendWait = "http://" + localhost + "/phpcodeDocumentMNG/getsendwait.php" ;

    public static String LinkShowAllRom = "http://" + localhost + "/phpcodeDocumentMNG/getallroom.php" ;
    public static String LinkinsertDoccument = "http://" + localhost + "/phpcodeDocumentMNG/insertDoccument.php" ;



}
