package refactorizando.blog.mybatis.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  class Util {

    private Util(){}

    public  static <T> Boolean isNull(T object){
        return object==null;
    }

    public static <T>ResponseEntity<T> createResponseEntity(T body, HttpStatus status){
        return new ResponseEntity<>(body,status);
    }

}
