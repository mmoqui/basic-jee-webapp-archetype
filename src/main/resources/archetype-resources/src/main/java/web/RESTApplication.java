#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * The REST compliant web resources are available from the {@code api} URI path.
 * @author Miguel Moquillon
 */
@ApplicationPath("/api")
public class RESTApplication extends Application {}
