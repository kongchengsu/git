package com.sukongcheng.suboot.controller;

import com.sukongcheng.suboot.entry.User;
import com.sukongcheng.suboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.Wrapper;

@RestController()
@RequestMapping("/mapperAPI")
public class MapperAPI {
    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/all")
    public String testOne() {

//        userMapper.selectCount(userWrapper);
//        userMapper.delete();
//        userMapper.deleteBatchIds();
//        userMapper.deleteById();
//        userMapper.deleteByMap();
//        userMapper.selectById();
//        userMapper.selectBatchIds();
//        userMapper.selectByMap();
//        userMapper.selectList();
//        userMapper.selectMaps();
//        userMapper.selectMapsPage();
//        userMapper.selectObjs();
//        userMapper.selectOne();
//        userMapper.selectPage();
//        userMapper.insert();
//        userMapper.update();
//        userMapper.updateById();

        return userMapper.selectCount(null)+"";
    }
}
