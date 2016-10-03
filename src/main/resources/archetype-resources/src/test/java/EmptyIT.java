#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.test.WarBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Empty integration test.
 * @author Miguel Moquillon
 */
@RunWith(Arquillian.class)
public class EmptyIT {

  @Deployment
  public static WebArchive createDeployment() {
    return WarBuilder.forITest(EmptyIT.class).build();
  }

  @Test
  public void emptyTest() {
    assertThat(true, is(true));
  }
}
