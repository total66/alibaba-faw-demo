package com.ebanma.cloud.myannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 于秦涛
 * @version $ Id: CommitController, v 0.1 2023/06/19 8:55 98077 Exp $
 */
@RestController
@RequestMapping("/commit")
public class CommitController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/test/{userId}")
    public void test(@PathVariable String userId){
        commitService.test(userId);
    }

}

