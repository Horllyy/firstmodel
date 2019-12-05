package My.entities;


import lombok.*;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema = "`mybatiss`", name = "receiveMessage")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageR {
    @Id
    @Column(name = "messageId")
    private String messageId;

    @Column(name = "senderId")
    private String senderId;

    @Column(name = "senderName")
    private String senderName;

    @Column(name = "receiverId")
    private String receiverId;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "message")
    private String message;

    @Column(name = "flag")
    private int flag;

    @Column(name = "otherInfo")
    private String otherInfo;

    @Column(name = "created_time")
    private String createdTime;


}
