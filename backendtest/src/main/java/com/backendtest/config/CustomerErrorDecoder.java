package com.backendtest.config;

import org.springframework.stereotype.Component;

import com.backendtest.exception.BadRequestException;
import com.backendtest.exception.InternalServerErrorException;
import com.backendtest.exception.NotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class CustomerErrorDecoder implements ErrorDecoder{

	@Override
    public Exception decode(String methodKey, Response response) {
 
       
        switch (response.status()){
            case 400:
                     return new BadRequestException("Bad request");
            case 404:
                    return new NotFoundException("Product not found") ;
            case 500:
            		return new InternalServerErrorException("Internal server error");
            default:
                return new Exception(response.reason());
        } 
    }

}
