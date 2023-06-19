package com.ebanma.cloud.myannotation;

import com.ebanma.cloud.myannotation.common.AvoidRepeatableCommit;
import org.springframework.stereotype.Service;

/**
 * @author 于秦涛
 * @version $ Id: CommitService, v 0.1 2023/06/19 13:41 98077 Exp $
 */
@Service
public class CommitService {

    @AvoidRepeatableCommit
    public void test(String userId) {
        System.out.println(userId);
    }
}

