package org.javabahia.rh.repository;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 
import java.lang.annotation.*;
import javax.interceptor.InterceptorBinding;
 
@InterceptorBinding
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Leitura {

}
