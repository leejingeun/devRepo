/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 14.         First Draft.
 *
 *******************************************************************************/

package kr.co.sitglobal.oklms.commbiz.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class SendMail {
    Properties props = null;
    Session sess = null;
    InternetAddress fromAddr = null;
    int recType = 0;

    // InternetAddress[] toAddr = null;
    InternetAddress toAddr = null;
    String subject = null;
    int msgType = 0;
    String text = null;
    String textEnc;
    String contEnc;
    public SendMail(String smtpHost) throws Exception {

        boolean debug = false;

        props = new Properties();
        props.put("mail.smtp.host", smtpHost);

        sess = Session.getDefaultInstance(props, null);
        sess.setDebug(debug);
    }

    String filePath;

    public void setEncoding(String textEnc, String contEnc) {
        this.textEnc = textEnc;
        this.contEnc = contEnc;
    }

    public void setFromAddr(String strFrom) throws Exception {
        this.fromAddr = new InternetAddress(strFrom);
    }

    public void setRecType(int recType) {
        this.recType = recType;
    }

    // 받는 사람 셋팅
    public void setToAddr(String arrTo) throws Exception {
        this.toAddr = new InternetAddress(arrTo);
    }

    // 제목셋팅
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // 메일 내용 셋팅
    public void setText(String text) {
        this.text = text;
    }
    
    public void setText(Object text) {
        this.text = (String)text;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    // 첨부파일경로셋팅
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void send() throws Exception {
        MimeMessage mMessage = new MimeMessage(sess);
        mMessage.setFrom(fromAddr);
 
        if (recType == 0) {
            // mMessage.setRecipients(Message.RecipientType.TO, toAddr);
            mMessage.setRecipient(Message.RecipientType.TO, toAddr);
        } else if (recType == 1) {
            // mMessage.setRecipients(Message.RecipientType.CC, toAddr);
            mMessage.setRecipient(Message.RecipientType.CC, toAddr);
        } else if (recType == 2) {
            // mMessage.setRecipients(Message.RecipientType.BCC, toAddr);
            mMessage.setRecipient(Message.RecipientType.BCC, toAddr);
        }

        mMessage.setSubject(subject);
        
        /**
         * for file attach (첨부파일 처리)
         */
        if (!"".equals(filePath)) {
            // Create the message part(새로운 몸체 생성)
            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart attachPart = new MimeBodyPart();
            //두번째 메세지 몸체 추가
            MimeMultipart multipart = new MimeMultipart();
            
            // Fill the message
            messageBodyPart.setText(text);
            // Part two is attachment
            filePath = fileSize(filePath); // 파일 사이즈를 구한다.
            //파일 객체 생성
            DataSource source = new FileDataSource(filePath);
            //메세지 몸체에 파일 객체 첨부
            attachPart.setDataHandler(new DataHandler(source));
            // 파일 이름 설정(한글처리)
            attachPart.setFileName(MimeUtility.encodeText(source.getName(), "euc-kr", "B"));
            
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachPart);

            // Put parts in message
            mMessage.setContent(multipart);
        } else {
            if (msgType == 0) {
                mMessage.setText(text, textEnc);
            } else if (msgType == 1) {
                mMessage.setContent(text, "text/html;charset=" + contEnc);
            }
        }
        
        Transport.send(mMessage);
    }

    private String fileSize(String filename1) {
        if (filename1.length() > (1024 * 1024 * 2.5)) {
            filename1 = "";
        }
        return filename1;
    }

}