#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A provider of beans managed by CDI. Such a provider is dedicated to be used by non managed
 * beans like for example JPA entities so that they can access the beans managed by CDI in order
 * to delegate some of their business operations.
 */
public class final BeanProvider {

  @SuppressWarnings("unchecked")
  public static <T> T getByName(String beanName) throws IllegalStateException {
    BeanManager beanManager = CDI.current().getBeanManager();
    Bean<T> bean = beanManager.resolve((Set) beanManager.getBeans(name));
    if (bean == null) {
      throw new IllegalStateException("Cannot find an instance of name " + name);
    }
    CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
    Type type = bean.getTypes().stream().findFirst().get();

    return (T) beanManager.getReference(bean, type, ctx);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBeanByType(final Class<T> type, Annotation... qualifiers)
      throws IllegalStateException {
    BeanManager beanManager = CDI.current().getBeanManager();
    Bean<T> bean = beanManager.resolve((Set) beanManager.getBeans(type, qualifiers));
    if (bean == null) {
      throw new IllegalStateException("Cannot find an instance of type " + type.getName());
    }
    CreationalContext<T> ctx = beanManager.createCreationalContext(bean);

    return (T) beanManager.getReference(bean, type, ctx);
  }

  @SuppressWarnings("unchecked")
  public static <T> Set<T> getAllBeansByType(final Class<T> type, Annotation... qualifiers) {
    BeanManager beanManager = CDI.current().getBeanManager();
    Set<T> refs = beanManager.getBeans(type, qualifiers).stream().map(bean -> {
          CreationalContext ctx = beanManager.createCreationalContext(bean);
          return (T) beanManager.getReference(bean, type, ctx);
        })
        .collect(Collectors.toSet());
    return refs;
  }
}