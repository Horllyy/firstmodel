package My;

import tk.mybatis.spring.annotation.MapperScan;
//用public interface MessageMapper extends BaseMapper<MessageR>, ExampleMapper<MessageR> 的话 就要用tk.mybatis.spring.annotation.MapperScan;jar包噢 而且不影响原来自己写的mapper语句
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "My.mapper")
@SpringBootApplication
public class FirstmodelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstmodelApplication.class, args);
    }

}
