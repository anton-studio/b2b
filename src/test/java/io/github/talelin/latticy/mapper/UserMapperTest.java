package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.GroupDO;
import io.github.talelin.latticy.model.ImsContractDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.model.UserGroupDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.swing.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private ImsContractMapper contractMapper;


    @Test
    public void selectCountByUsername() {
        String email = "13129982604@qq.com";
        String username = "pedro-test";
        UserDO userDO = new UserDO();
        userDO.setEmail(email);
        userDO.setUsername(username);
        userMapper.insert(userDO);
        int count = userMapper.selectCountByUsername(username);
        assertTrue(count > 0);
    }

    @Test
    public void selectCountById() {
        String email = "13129982604@qq.com";
        String username = "pedro-test";
        UserDO userDO = new UserDO();
        userDO.setEmail(email);
        userDO.setUsername(username);
        userMapper.insert(userDO);
        int count = userMapper.selectCountById(userDO.getId());
        assertTrue(count > 0);
    }

    @Test
    public void selectPageByGroupId() {
        String email = "13129982604@qq.com";
        String username = "pedro-test";
        UserDO userDO = new UserDO();
        userDO.setEmail(email);
        userDO.setUsername(username);
        userMapper.insert(userDO);

        GroupDO group = GroupDO.builder().name("group").info("零零落落").build();
        groupMapper.insert(group);

        userGroupMapper.insert(new UserGroupDO(userDO.getId(), group.getId()));

        Page page = new Page(0, 10);
        IPage<UserDO> iPage = userMapper.selectPageByGroupId(page, group.getId(), 99999L);
        assertTrue(iPage.getTotal() > 0);
        boolean anyMatch = iPage.getRecords().stream().anyMatch(it -> it.getUsername().equals(username));
        assertTrue(anyMatch);
    }

    @Test
    public void dynamicQuery() {
        Map<String, Object> map = new HashMap<>();
//        map.put("PI_NO", "US21AIRST0483");
//        map.put("owned_by", 1);
//        map.put("payment_status", new String[]{"支付预付款"});
        map.put("payment_method", new String[]{"PayPal"});
//        List<ImsContractDO> imsContractDOS = contractMapper.selectWithFilter(map);
//        System.out.println(imsContractDOS);
    }
}