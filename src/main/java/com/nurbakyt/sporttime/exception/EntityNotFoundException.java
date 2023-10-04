package com.nurbakyt.sporttime.exception;

import com.nurbakyt.sporttime.dto.MemberDto;
import com.nurbakyt.sporttime.service.MemberServiceImpl;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super();
    }


}
