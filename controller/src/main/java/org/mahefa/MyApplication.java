package org.mahefa;

import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.ApplicationScoped;

/**
 * Hello world!
 *
 */
//@QuarkusMain
@ApplicationScoped
public class MyApplication
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
