package My.entities;

import java.util.Date;

public class MessageS {

    private Integer messageId;
    private String sender;
    private String receiver;
    private String message;
    private String otherInfo;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public MessageS(Integer messageId, String sender, String receiver, String message, String otherInfo, String date) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.otherInfo = otherInfo;
        this.date = date;
    }

    public MessageS() {
    }

    @Override
    public String toString() {
        return "MessageS{" +
                "messageId=" + messageId +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
