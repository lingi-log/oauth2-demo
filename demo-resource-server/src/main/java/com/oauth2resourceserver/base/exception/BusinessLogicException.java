package com.oauth2resourceserver.base.exception;

import com.oauth2resourceserver.base.response.code.ResponseCodeEnum;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
    /** Response error code */
    @Getter
    ResponseCodeEnum responseCode;
    
    /** Response data with */
    private Object responseData;
    
    public BusinessLogicException() {
        super();
    }
    public BusinessLogicException putData(Object data) {
        this.responseData = data;
        return this;
    }
}

// /**
//  * BusinessLogicException
//  *
//  * @author Daniel Nguyen
//  * @since 2.0.0
//  */
// public class BusinessLogicException extends RuntimeException {

//     /*================================================================================================================
//      *===== PRIVATE PROPERTIES                                                                                   =====
//      *================================================================================================================*/

//     /** Response error code */
//     private ResponseCode responseCode;

//     /** Response data with */
//     private Object responseData;

//     /*================================================================================================================
//      *===== CONSTRUCTOR METHOD                                                                                   =====
//      *================================================================================================================*/

//     /**
//      * Default constructor
//      */
//     public BusinessLogicException() {
//         super();
//     }

//     /**
//      * Default constructor
//      *
//      * @param code Business error code
//      */
//     public BusinessLogicException(ResponseCode code) {
//         super();
//         this.responseCode = code;
//     }

//     /*================================================================================================================
//      *===== PUBLIC METHOD                                                                                       =====
//      *================================================================================================================*/

//     /**
//      * Set value for responseData
//      *
//      * @param data the responseData to set
//      */
//     public BusinessLogicException putData(Object data) {
//         this.responseData = data;
//         return this;
//     }

//     /*================================================================================================================
//      *===== GETTER & SETTER                                                                                      =====
//      *================================================================================================================*/

//     /**
//      * Get value of ResponseCode
//      *
//      * @return the responseCode
//      */
//     public ResponseCode getResponseCode() {
//         return responseCode;
//     }

//     /**
//      * Get value of responseData
//      *
//      * @return the responseData
//      */
//     public Object getResponseData() {
//         return responseData;
//     }
// }

