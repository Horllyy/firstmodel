package My.mapper;

import My.entities.MessageR;
import My.entities.MessageS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import java.util.Collection;

public interface MessageMapper extends BaseMapper<MessageR>, ExampleMapper<MessageR> {


    @Select("select * from sendMessage")
    public Collection<MessageS> getAllMessageS();

    @Select("select messageId from sendMessage where date=#{date}")
    public String getMessageIdByDate(String date);

    @Insert("insert into sendmessage(sender,receiver,message,otherInfo,date) values(#{sender},#{receiver},#{message},#{otherInfo},#{date})")
    public void addMessage(MessageS message);


//    @Update("CREATE TABLE t3(" +
//            "--     id int not null," +
//            "--     name char(20)" +
//            "    receiverId        VARCHAR(20)      NOT NULL PRIMARY KEY," +
//            "    receiver          VARCHAR(20)      NOT NULL," +
//            "    flag              BIGINT(2)           NOT NULL," +
//            "    otherInfo        VARCHAR(255)     ," +
//            "    created_time     datetime(6)      NOT NULL" +
//            ");")
//    public boolean createReceiverTable();
    //往接收消息表中插入数据
     @Insert("insert into receivemessage(messageId,senderId,senderName,receiverId,receiver,message,flag,otherInfo,created_time) values(#{messageId},#{senderId},#{senderName},#{receiverId},#{receiver},#{message},#{flag},#{otherInfo},#{created_time})")
     public void addReceiveMessage(MessageR messageR);

    @Select("select * from receivemessage where receiverId=#{stuId}")
    public Collection<MessageR> getAllMessageR(String stuId);

//    //查询已读消息
//    @Select("select * from receivemessage where receiverId=#{stuId} and flag=1")
//    public Collection<MessageR> getReadMessageR(String stuId);
//
//    //查询未读消息
//    @Select("select * from receivemessage where receiverId=#{stuId}and flag=0")
//    public Collection<MessageR> getNotReadMessageR(String stuId);


}
