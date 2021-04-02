package org.mahefa.annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.transaction.Transactional;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ApplicationScoped
@Transactional
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
