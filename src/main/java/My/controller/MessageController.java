package My.controller;

import My.entities.MessageR;
import My.entities.MessageS;
import My.entities.Role;
import My.entities.Student;
import My.mapper.LoginMapper;
import My.mapper.MessageMapper;
import My.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class MessageController {

    @Autowired
    StudentMapper studentMapper;

    @Resource
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    LoginMapper loginMapper;

    LinkedList<MessageS> messageS = new LinkedList<MessageS>();


    @GetMapping("/sendPage1")
    public String sendPage1(Model model,HttpSession session){

        String receivers="";
        MessageS message=(MessageS) session.getAttribute("message");
        System.out.println("1111message="+message);

        Collection<Student> stus=studentMapper.getAll();
        model.addAttribute("stus",stus);
            if (message != null && message.getReceiver() != null) {
                if (session.getAttribute("receiver2")==null||session.getAttribute("receiver2").equals("- -")) {
                    System.out.println("11111111111");
                    if (messageS.isEmpty()){
                        messageS.addFirst(message);
                        session.removeAttribute("receiver2");
                    }
                    else {
                        System.out.println("messages="+messageS);

                        messageS.addLast(message);
                        session.removeAttribute("receiver2");
//
//                        for (MessageS amessage : messageS
//                        ) {
//                            System.out.println("222222222");
//                            System.out.println(message);
//                            if (amessage.getReceiver().equals(message.getReceiver())) {
//                                System.out.println("重复了 不添加");
//                            } else {
//                                //避免重复
//
//                                messageS.addLast(message);
//                                session.removeAttribute("receiver2");
//                            }
//                        }
                    }

                }
                else {
                    System.out.println("要删除的人是："+session.getAttribute("receiver2"));
                    System.out.println(message);



                    Iterator<MessageS> messageSIterator=messageS.iterator();
                    while (messageSIterator.hasNext()){
                        MessageS messageS=messageSIterator.next();
                        if (messageS.getReceiver().equals(message.getReceiver())){
                            messageSIterator.remove();
                            System.out.println("删除了："+message.getReceiver());
                        }
                    }



//                 下面这样是会报错滴。添加 删除的时候要按上面的遍历器来
//                    for (MessageS amessage : messageS
//                    ) {
//                        if (amessage.getReceiver().equals(amessage.getReceiver()))
//                            System.out.println(messageS.remove(amessage));
//
//                    }
                    session.removeAttribute("receiver2");
//                    System.out.println(messageS.remove(message));








                }
                for (MessageS amessage : messageS
                ) {
                    receivers += amessage.getReceiver();
                    receivers += ";";
                }
                model.addAttribute("message", message.getMessage());
            }

            model.addAttribute("noOne","- -");
        model.addAttribute("messageS",messageS);
        model.addAttribute("receivers",receivers);
        System.out.println("receivers="+receivers);

//        if (session.getAttribute("message")==null){
//            System.out.println("请输入收件人吧！");
//        }
//        else{
//            message=(MessageS) session.getAttribute("message");
//        }
//        System.out.println("message="+message);
//        System.out.println("receiverList="+message.getOtherInfo());
//        if (message.getReceiver()==null) {
//            Collection<Student> stus = studentMapper.getAll();
//            System.out.println(stus);
//            model.addAttribute("stus", stus);
//            model.addAttribute("receiverList",message.getOtherInfo()+message.getReceiver());
//
//        }
//        else {
//            Collection<Student> stus = studentMapper.getAll();
//            System.out.println(stus);
//            model.addAttribute("stus", stus);
//            System.out.println("最后一次添加receiver后的receiverList是："+message.getOtherInfo()+message.getReceiver());
//            model.addAttribute("receiverList",message.getOtherInfo()+message.getReceiver());
//            model.addAttribute("message",message.getMessage());
////            session.setAttribute("receiverList",message.getOtherInfo()+message.getReceiver());
////            session.setAttribute("message",message.getMessage());
//
//            System.out.println("最后一次添加receiver后的message是："+message);
//        }

        System.out.println("到达发送消息模块啦！");
//        return "redirect:/sendPage1";
        return "message/sendPage1";
    }

    @PostMapping("/sendSucceed")
    public String sendSucceed(String submit,String receiver2,MessageS message,HttpSession session,String receivers){

//        System.out.println("submit="+submit);
//        System.out.println("来自sendSucceed页面：message="+message);
        if(submit.equals("addSender")){
            session.setAttribute("message",message);
            return "redirect:/sendPage1";
        }
        else if (submit.equals("reduceSender")){
            message.setReceiver(receiver2);
            session.setAttribute("message",message);
            session.setAttribute("receiver2",receiver2);
            System.out.println(receiver2);
            return "redirect:/sendPage1";
        }
        else if(submit.equals("checkSent")){
            System.out.println("准备从从sendPage1编辑页面跳转至查看已发页面");


            return "redirect:/checkSent";
        }
        else {
            System.out.println("2222message="+message);
            message.setMessage(message.getMessage());//从sendPage1传过来的结构体
            message.setReceiver(receivers);//从sendPage1传过来的参数

            Role role=(Role)session.getAttribute("role");
            System.out.println("role="+role.getRole()+"role的StuId="+role.getStuId());
            message.setSender(role.getStuId());
            //sender和receiver略有问题

            Date date=new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            message.setDate(sdf.format(date));
            System.out.println("3333message="+message);

            messageMapper.addMessage(message);
            System.out.println("新消息已插入数据库");


            List<String> receiverList=stringToList(receivers);
            for (String re:receiverList
            ) {
                MessageR messageR=MessageR.builder().messageId(messageMapper.getMessageIdByDate(message.getDate())).
                        receiverId(studentMapper.getStuIdByName(re)).
                        senderId(role.getStuId()).
                        senderName(loginMapper.getLoginName(role.getStuId())).
                        receiver(re).
                        message(message.getMessage()).
                        flag(0).
                        createdTime(sdf.format(new Date())).
                        otherInfo("暂无").
                        build();
                messageMapper.addReceiveMessage(messageR);
                System.out.println("插入新的接收信息数据："+messageR.toString());
            }
            System.out.println("接收信息数据库已更新");

            return "message/sendSucceed";
        }

        //1.日期的格式把分秒加上去   2.先不求完美 把接下来的功能一页一页做完先 下一页：查看已发

    }


    @GetMapping("/checkSent")
    public String checkSent(Model model){
        System.out.println("到达查看已发界面");
        Collection<MessageS> messageSlist=messageMapper.getAllMessageS();
        model.addAttribute("messageSlist",messageSlist);


        //下面就是   1.页面格式的优化（时间啥的 ） 2.按键再合理安排一下（check消息或许不需要按键）  3.在适当位置加个查看已发栏可以直接check消息

        //明天的修改 1.sender和receiver的取值有问题 check一下哪一环没传好   2.时间戳后半部分HH:MM:SS在数据库里也要存进去(日后可以在主界面搞个最新消息 最新发送什么的)
                  //     3.信息message的中文显示再check一下有没有问题 是不是显示空间太小导致无法显示
        //以上都解决了 都是小问题 传参之间略略有些脱节罢了 以后越写越要养成习惯 熟悉自己的代码书写大小习惯 改起来不会麻烦。

        //周末：继续先往下做 收件箱页
        //       2019/11/25 22：44 收件箱走起

        return "message/checkSent";
    }


    @GetMapping("/receivePage1")
    public String receivePage1(HttpSession session,Model model){
        System.out.println("到达收件箱啦！");
        Role role=(Role) session.getAttribute("role");
        Collection<MessageR> messageRList=messageMapper.getAllMessageR(role.getStuId());
        log.info("messageRList= = = = = = ={}",messageRList );
        model.addAttribute("messageRList",messageRList);

        //刚才修改了不少内容 稍微测试一下消息接受写入功能再进行下一步
        // 测试完毕 基本ok 下一步 已读和未读界面

        //2019/11/26 22:17 以上基本ok  这周结束最后一步：点击按钮查看信息详情 并在此功能中实现未读转已读的update 可返回此界面
        //日后就是完善功能 精修界面风格了。

        return "message/receivePage1";

    }


    @PostMapping("/checkReceived")
    public String checkReceived(MessageR amessageR,HttpSession session){
        System.out.println("到达查看收件详情界面啦 此界面未读会转化为已读噢");
        log.info("收到的messageR是：{}"+amessageR);
        session.setAttribute("messageR",amessageR);



        //ok 最后一件事是想办法把主键id传过来 不管是用粗暴的链接参数法 还是hidden的参数
        //传过来以后把可以把数据库的flag更新掉就好 这个功能就结束了 就剩精修了。

        //2019/11/27 15:00 传好了。用input标签 + type="hidden"属性 完美
        //15：25 flag更新功能ok 还可以搞个返回收件箱按钮。然后就剩开始每天一点精修吧 新功能是没了。班级管理部分估计还可以稍微搞一下 其他也没啥 就界面部分比较费事了




        if (amessageR.getFlag()==0){
            amessageR=MessageR.builder().messageId(amessageR.getMessageId()).receiverId(amessageR.getReceiverId()).flag(1).build();
            messageMapper.updateByPrimaryKeySelective(amessageR);
            log.info("flag更新完毕：{}"+amessageR);
        }
        else{
            log.info("消息读取完毕：{}"+amessageR);
        }
        return "message/checkReceived";

    }


    public List<String> stringToList(String str){
        ArrayList<String> list=new ArrayList<>();
        String [] spString = str.split(";");
        for (String s:spString
        ) {
            list.add(s);
            System.out.println(list);
        }
        return list;
    }
}
