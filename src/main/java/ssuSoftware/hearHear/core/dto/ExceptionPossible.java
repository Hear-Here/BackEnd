package ssuSoftware.hearHear.core.dto;

import ssuSoftware.hearHear.error.handler.code.ErrorCode;

public interface ExceptionPossible {

    ErrorCode getErrorCode();

    static ExceptionPossible exception(ErrorCode errorCode){
        return  () -> errorCode;
    }
}
