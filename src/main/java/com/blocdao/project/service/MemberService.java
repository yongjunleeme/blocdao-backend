package com.blocdao.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class MemberService {

    @Transactional
    public String signup() {
        return "";
    }
}
