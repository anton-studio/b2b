package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.PmsAttrDO;
import io.github.talelin.latticy.service.PmsAttrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PmsAttrServiceImplTest {

    @Autowired
    PmsAttrService pmsAttrService;

    @Test
    public void findByAttrGroupId() {
//        List<PmsAttrDO> attrDOS = pmsAttrService.findByAttrGroupId(1L);
//        System.out.println(attrDOS);
    }
}