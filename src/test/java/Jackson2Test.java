import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Jackson2Test {

    @Test
    public void testMap() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
        //mapper.configOverride(Map.Entry.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.OBJECT));
        mapper.configOverride(Map.Entry.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        Map<String, Object> res = new HashMap<>();
        res.put("username", "pedro");
        res.put("userAge", 24);
        String s = mapper.writeValueAsString(res);
        log.info(s);
    }

    @Test
    public void testCamel() throws JsonProcessingException {
        String str = "userAge";
        String s = StrUtil.toUnderlineCase(str);
        log.info(s);
    }

    @Test
    public void month() {
        Integer month = new Date().getMonth() + 1;
        String monthStr = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);

        int max = 9;
        int min = 0;

        String ranNo1 = String.valueOf((int )(Math.random() * max + min));
        String ranNo2 = String.valueOf((int )(Math.random() * max + min));
        String code = monthStr + ranNo1 + ranNo2;
        System.out.println(code);

    }
}
