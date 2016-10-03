#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test;

import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.asset.AssetUtil;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * A war builder dedicated to build a war archive dedicated to integration tests.
 * @author Miguel Moquillon
 */
public class WarBuilder {

  private static final ArchivePath PATH_WEB_INF = ArchivePaths.create("WEB-INF");
  private static final ArchivePath PATH_CLASSES = ArchivePaths.create(PATH_WEB_INF, "classes");

  private WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");

  /**
   * Constructs an instance of a war builder for the specified integration test class.
   * @param test the test class for which a war will be built. Any resources located in the same
   * package of the test will be loaded into the war.
   * @param <T> the type of the test.
   * @return a builder of the war archive.
   */
  public static <T> WarBuilder forITest(final Class<T> test) {
    return new WarBuilder(test);
  }

  /**
   * Constructs a war builder for the specified test class. It will load all the resources in the
   * same packages of the specified test class.
   * @param test the class of the test for which a war archive will be build.
   */
  protected  <T> WarBuilder(final Class<T> test) {
    String resourcePath = test.getPackage().getName().replaceAll("\\.", "/");
    war.addAsResource(resourcePath)
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  /**
   * Enables the support of JPA for the integration tests. This will load all the resources required
   * for running the integration tests implying a database.
   * @return itself.
   */
  public WarBuilder withJPA() {
    war.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
        .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    return this;
  }

  /**
   * Builds the final Web archive in which are deployed the integration tests and all its
   * dependencies.
   */
  public final WebArchive build() {
    return war;
  }
}