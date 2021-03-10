package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.ProductDTO;
import io.github.talelin.latticy.service.PmsSpuInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class PmsSpuInfoServiceImplTest {

    @Autowired
    PmsSpuInfoService spuInfoService;

    @Test
    public void getProductDetailById() {
        ProductDTO product = spuInfoService.getProductDetailById(8l);
        System.out.println(product);
    }
}