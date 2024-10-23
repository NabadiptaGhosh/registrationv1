package com.api.exception;

public class ResourceNotFound extends  RuntimeException{

    // Generate a constructor
    public ResourceNotFound(String msg){
        super(msg); // "super" keyword call the constructor of parent class(RuntimeException)
        // into the super keyword which message we will give, then this message will show in our console when Exception will be occured.
    }
}
