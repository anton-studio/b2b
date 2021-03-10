package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.service.PmsProductAttrValueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
//@Rollback
public class PmsProductAttrValueServiceImplTest {

    @Autowired
    PmsProductAttrValueService productAttrValueService;

    @Test
    public void deleteAttrBySpuId() {
        productAttrValueService.deleteAttrBySpuId(1l);
        System.out.println("111");
    }
}